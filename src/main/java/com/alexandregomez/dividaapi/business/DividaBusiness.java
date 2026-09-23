package com.alexandregomez.dividaapi.business;

import com.alexandregomez.dividaapi.dto.DividaDTO;
import com.alexandregomez.dividaapi.repository.DividaRepository;
import com.alexandregomez.dividaapi.util.DividaConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DividaBusiness {

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
        return DividaConverter.convert(dividaRepository.findById(id).get());
    }

    public List<DividaDTO> findAll() {
        return DividaConverter.convert(dividaRepository.findAll());
    }

    public void delete(Long id) {
        dividaRepository.delete(dividaRepository.findById(id).get());
    }
}