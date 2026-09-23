package com.alexandregomez.dividaapi.controller;

import com.alexandregomez.dividaapi.business.DividaBusiness;
import com.alexandregomez.dividaapi.dto.DividaDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dividas")
public class DividaController {

    private DividaBusiness dividaBusiness;

    public DividaController(DividaBusiness dividaBusiness){
        this.dividaBusiness = dividaBusiness;
    }

    @PostMapping
    public DividaDTO post(@RequestBody DividaDTO dividaDTO){
        return dividaBusiness.save(dividaDTO);
    }

    @GetMapping("/{id}")
    public DividaDTO post(@PathVariable Long id){
        return dividaBusiness.findById(id);
    }

    @GetMapping()
    public List<DividaDTO> get(){
        return dividaBusiness.findAll();
    }

    @PutMapping
    public DividaDTO put(@RequestBody DividaDTO dividaDTO){
        return dividaBusiness.save(dividaDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        dividaBusiness.delete(id);
    }

    @GetMapping("/health")
    public String healthCheck(){
        return "no ar";
    }
}