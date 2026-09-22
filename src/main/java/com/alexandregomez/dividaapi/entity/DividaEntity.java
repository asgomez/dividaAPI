package com.alexandregomez.dividaapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class DividaEntity {
    @Id
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
