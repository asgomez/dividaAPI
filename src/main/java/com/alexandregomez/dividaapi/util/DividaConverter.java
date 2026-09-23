package com.alexandregomez.dividaapi.util;

import com.alexandregomez.dividaapi.dto.DividaDTO;
import com.alexandregomez.dividaapi.entity.DividaEntity;

import java.util.List;

public class DividaConverter {
    public static DividaEntity convert(DividaDTO dividaDTO) {
        DividaEntity dividaEntity = new DividaEntity();

        dividaEntity.setCpfDevedor(dividaDTO.getCpfDevedor());
        dividaEntity.setId(dividaDTO.getId());
        dividaEntity.setValorComDesconto(dividaDTO.getValorComDesconto());
        dividaEntity.setValorComJuros(dividaDTO.getValorComJuros());
        dividaEntity.setValorPego(dividaDTO.getValorPego());

        return dividaEntity;
    }

    public static DividaDTO convert(DividaEntity dividaEntity) {
        DividaDTO dividaDTO = new DividaDTO();

        dividaDTO.setCpfDevedor(dividaEntity.getCpfDevedor());
        dividaDTO.setId(dividaEntity.getId());
        dividaDTO.setValorComDesconto(dividaEntity.getValorComDesconto());
        dividaDTO.setValorComJuros(dividaEntity.getValorComJuros());
        dividaDTO.setValorPego(dividaEntity.getValorPego());

        return dividaDTO;
    }

    public static List<DividaDTO> convert(List<DividaEntity> dividaEntityList) {
        return dividaEntityList.stream().map(d -> convert(d)).toList();
    }
}