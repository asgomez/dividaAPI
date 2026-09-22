package com.alexandregomez.dividaapi.controller;

import com.alexandregomez.dividaapi.business.DividaBusiness;
import com.alexandregomez.dividaapi.dto.DividaDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/dividas")
public class DividaController {

    private DividaBusiness dividaBusiness;

    public DividaController(DividaBusiness dividaBusiness){
        this.dividaBusiness = dividaBusiness;
    }

    @PostMapping
    public DividaDTO post(DividaDTO dividaDTO){
        return dividaBusiness.create(dividaDTO);
    }
}
