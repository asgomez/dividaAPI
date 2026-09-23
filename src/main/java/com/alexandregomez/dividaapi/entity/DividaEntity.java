package com.alexandregomez.dividaapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class DividaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String cpfDevedor;

    @Column
    private BigDecimal valorPego;

    @Column
    private BigDecimal valorComJuros;

    @Column
    private BigDecimal valorComDesconto;
}
