package com.alexandregomez.dividaapi.service;

import com.alexandregomez.dividaapi.dto.DividaDTO;
import com.alexandregomez.dividaapi.entity.DividaEntity;
import com.alexandregomez.dividaapi.exception.DividaNotFoundException;
import com.alexandregomez.dividaapi.repository.DividaRepository;
import com.alexandregomez.dividaapi.util.DividaConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DividaSevice {

    @Autowired
    private DividaRepository dividaRepository;

    public DividaDTO save(DividaDTO dividaDTO) {
        return DividaConverter.convert(
                dividaRepository.save(
                        DividaConverter.convert(dividaDTO)
                )
        );
    }

    public DividaDTO findById(Long id) {
        DividaEntity dividaEntity = dividaRepository.findById(id)
                .orElseThrow(() -> new DividaNotFoundException(id));

        return DividaConverter.convert(dividaEntity);
    }

    public List<DividaDTO> findAll() {

        return DividaConverter.convert(dividaRepository.findAll());
    }

    public void delete(Long id) {
        DividaEntity divida = dividaRepository.findById(id)
                .orElseThrow(() -> new DividaNotFoundException(id));

        dividaRepository.delete(divida);
    }

    public DividaDTO update(Long id, DividaDTO dividaDTO) {

        DividaEntity divida = dividaRepository.findById(id)
                .orElseThrow(() -> new DividaNotFoundException(id));

        divida.setCpfDevedor(dividaDTO.getCpfDevedor());
        divida.setValorPego(dividaDTO.getValorPego());
        divida.setValorComJuros(dividaDTO.getValorComJuros());
        divida.setValorComDesconto(dividaDTO.getValorComDesconto());

        return DividaConverter.convert(
                dividaRepository.save(divida)
        );
    }
}