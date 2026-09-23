package com.alexandregomez.dividaapi.service;

import com.alexandregomez.dividaapi.dto.DividaDTO;
import com.alexandregomez.dividaapi.entity.DividaEntity;
import com.alexandregomez.dividaapi.exception.DividaNotFoundException;
import com.alexandregomez.dividaapi.repository.DividaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class DividaSeviceTest {

    @Mock
    private DividaRepository dividaRepository;

    @InjectMocks
    private DividaSevice dividaSevice;

    @Test
    void deveLancarExcecaoQuandoDividaNaoExistir() {

        Long id = 999L;

        when(dividaRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                DividaNotFoundException.class,
                () -> dividaSevice.findById(id)
        );
    }

    @Test
    void deveRetornarDividaQuandoIdExistir() {

        Long id = 1L;

        DividaEntity entity = new DividaEntity();
        entity.setId(id);
        entity.setCpfDevedor("12345678900");
        entity.setValorPego(new BigDecimal("1000.00"));
        entity.setValorComJuros(new BigDecimal("1200.00"));
        entity.setValorComDesconto(new BigDecimal("1100.00"));

        when(dividaRepository.findById(id))
                .thenReturn(Optional.of(entity));

        DividaDTO resultado = dividaSevice.findById(id);

        assertEquals(id, resultado.getId());
        assertEquals("12345678900", resultado.getCpfDevedor());
        assertEquals(new BigDecimal("1000.00"), resultado.getValorPego());
    }

    @Test
    void deveLancarExcecaoAoAtualizarDividaInexistente() {

        Long id = 999L;

        DividaDTO dividaDTO = new DividaDTO();
        dividaDTO.setCpfDevedor("12345678900");
        dividaDTO.setValorPego(new BigDecimal("1000.00"));
        dividaDTO.setValorComJuros(new BigDecimal("1200.00"));
        dividaDTO.setValorComDesconto(new BigDecimal("1100.00"));

        when(dividaRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                DividaNotFoundException.class,
                () -> dividaSevice.update(id, dividaDTO)
        );
    }

    @Test
    void deveAtualizarDividaQuandoIdExistir() {

        Long id = 1L;

        DividaEntity entity = new DividaEntity();
        entity.setId(id);
        entity.setCpfDevedor("12345678900");
        entity.setValorPego(new BigDecimal("1000.00"));
        entity.setValorComJuros(new BigDecimal("1200.00"));
        entity.setValorComDesconto(new BigDecimal("1100.00"));

        DividaDTO dividaDTO = new DividaDTO();
        dividaDTO.setCpfDevedor("98765432100");
        dividaDTO.setValorPego(new BigDecimal("2000.00"));
        dividaDTO.setValorComJuros(new BigDecimal("2300.00"));
        dividaDTO.setValorComDesconto(new BigDecimal("2100.00"));

        when(dividaRepository.findById(id))
                .thenReturn(Optional.of(entity));

        when(dividaRepository.save(entity))
                .thenReturn(entity);

        DividaDTO resultado = dividaSevice.update(id, dividaDTO);

        assertEquals(id, resultado.getId());
        assertEquals("98765432100", resultado.getCpfDevedor());
        assertEquals(new BigDecimal("2000.00"), resultado.getValorPego());
        assertEquals(new BigDecimal("2300.00"), resultado.getValorComJuros());
        assertEquals(new BigDecimal("2100.00"), resultado.getValorComDesconto());
    }

    @Test
    void deveLancarExcecaoAoExcluirDividaInexistente() {

        Long id = 999L;

        when(dividaRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                DividaNotFoundException.class,
                () -> dividaSevice.delete(id)
        );
    }

    @Test
    void deveExcluirDividaQuandoIdExistir() {

        Long id = 1L;

        DividaEntity entity = new DividaEntity();
        entity.setId(id);
        entity.setCpfDevedor("12345678900");
        entity.setValorPego(new BigDecimal("1000.00"));
        entity.setValorComJuros(new BigDecimal("1200.00"));
        entity.setValorComDesconto(new BigDecimal("1100.00"));

        when(dividaRepository.findById(id))
                .thenReturn(Optional.of(entity));

        dividaSevice.delete(id);

        verify(dividaRepository).delete(entity);
    }

    @Test
    void deveSalvarDividaComSucesso() {

        DividaDTO dividaDTO = new DividaDTO();
        dividaDTO.setCpfDevedor("12345678900");
        dividaDTO.setValorPego(new BigDecimal("1000.00"));
        dividaDTO.setValorComJuros(new BigDecimal("1200.00"));
        dividaDTO.setValorComDesconto(new BigDecimal("1100.00"));

        DividaEntity entitySalva = new DividaEntity();
        entitySalva.setId(1L);
        entitySalva.setCpfDevedor("12345678900");
        entitySalva.setValorPego(new BigDecimal("1000.00"));
        entitySalva.setValorComJuros(new BigDecimal("1200.00"));
        entitySalva.setValorComDesconto(new BigDecimal("1100.00"));

        when(dividaRepository.save(any(DividaEntity.class)))
                .thenReturn(entitySalva);

        DividaDTO resultado = dividaSevice.save(dividaDTO);

        assertEquals(1L, resultado.getId());
        assertEquals("12345678900", resultado.getCpfDevedor());
        assertEquals(new BigDecimal("1000.00"), resultado.getValorPego());
        assertEquals(new BigDecimal("1200.00"), resultado.getValorComJuros());
        assertEquals(new BigDecimal("1100.00"), resultado.getValorComDesconto());
    }
}
