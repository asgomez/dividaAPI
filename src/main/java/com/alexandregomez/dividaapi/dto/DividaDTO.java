package com.alexandregomez.dividaapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class DividaDTO {

    private Long id;

    @NotBlank(message = "CPF do devedor deve ser informado")
    private String cpfDevedor;

    @DecimalMin(value = "0.0", message = "Valor pego não pode ser negativo")
    private BigDecimal valorPego;

    @DecimalMin(value = "0.0", message = "Valor pego não pode ser negativo")
    private BigDecimal valorComJuros;

    @DecimalMin(value = "0.0", message = "Valor pego não pode ser negativo")
    private BigDecimal valorComDesconto;

}
