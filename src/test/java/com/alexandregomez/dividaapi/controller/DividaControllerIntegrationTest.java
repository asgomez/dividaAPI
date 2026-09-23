package com.alexandregomez.dividaapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DividaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveResponderAoHealthCheck() throws Exception {

        mockMvc.perform(get("/dividas/health"))
                .andExpect(status().isOk());
    }

    @Test
    void deveExecutarCrudCompleto() throws Exception {

        String jsonCriacao = """
            {
                "cpfDevedor": "12345678900",
                "valorPego": 1000.00,
                "valorComJuros": 1200.00,
                "valorComDesconto": 1100.00
            }
            """;

        String resposta = mockMvc.perform(post("/dividas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCriacao))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long id = Long.valueOf(
                resposta.replaceAll(".*\"id\":(\\d+).*", "$1")
        );

        mockMvc.perform(get("/dividas/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.cpfDevedor").value("12345678900"));

        String jsonAtualizacao = """
            {
                "cpfDevedor": "98765432100",
                "valorPego": 2000.00,
                "valorComJuros": 2300.00,
                "valorComDesconto": 2100.00
            }
            """;

        mockMvc.perform(put("/dividas/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonAtualizacao))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.cpfDevedor").value("98765432100"));

        mockMvc.perform(delete("/dividas/" + id))
                .andExpect(status().isOk());

        mockMvc.perform(get("/dividas/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveListarDividas() throws Exception {

        mockMvc.perform(get("/dividas"))
                .andExpect(status().isOk());
    }
}