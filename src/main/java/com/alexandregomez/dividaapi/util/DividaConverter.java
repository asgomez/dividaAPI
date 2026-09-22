package com.alexandregomez.dividaapi.util;

import com.alexandregomez.dividaapi.dto.DividaDTO;
import com.alexandregomez.dividaapi.entity.DividaEntity;

public class DividaConverter {
    public static DividaEntity convert(DividaDTO dividaDTO) {
        DividaEntity dividaEntity = new DividaEntity();

        dividaEntity.setCpfDevedor(dividaDTO.getCpfDevedor());
        dividaEntity.setId(dividaDTO.getId());
        dividaEntity.setValorComDesconto(dividaDTO.getValorComDesconto());
        dividaEntity.setValorComJuros(dividaDTO.getValorComJuros());
        dividaEntity.setValorPego(dividaDTO.getValorPego());
    }
}
