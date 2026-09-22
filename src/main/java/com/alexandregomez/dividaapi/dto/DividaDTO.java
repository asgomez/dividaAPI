package com.alexandregomez.dividaapi.dto;

import lombok.Data;

import javax.print.DocFlavor;
import java.math.BigDecimal;

@Data
public class DividaDTO {

    private Long id;
    private String cpfDevedor;
    private BigDecimal valorPego;
    private BigDecimal valorComJuros;
    private BigDecimal valorComDesconto;

}
