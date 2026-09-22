package com.alexandregomez.dividaapi.business;

import com.alexandregomez.dividaapi.dto.DividaDTO;
import com.alexandregomez.dividaapi.repository.DividaRepository;
import com.alexandregomez.dividaapi.util.DividaConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DividaBusiness {

    @Autowired
    private DividaRepository dividaRepository;

    public DividaDTO create(DividaDTO dividaDTO) {

        dividaRepository.save(
                DividaConverter.convert(dividaDTO)
        );
    }
}
