package com.alexandregomez.dividaapi.controller;

import com.alexandregomez.dividaapi.service.DividaSevice;
import com.alexandregomez.dividaapi.dto.DividaDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dividas")
public class DividaController {

    private DividaSevice dividaService;

    public DividaController(DividaSevice dividaBusiness){
        this.dividaService = dividaBusiness;
    }

    @PostMapping
    public DividaDTO post(@Valid @RequestBody DividaDTO dividaDTO){

        return dividaService.save(dividaDTO);
    }

    @GetMapping("/{id}")
    public DividaDTO post(@PathVariable Long id){
        return dividaService.findById(id);
    }

    @GetMapping()
    public List<DividaDTO> get(){
        return dividaService.findAll();
    }

    @PutMapping("/{id}")
    public DividaDTO put(
            @PathVariable Long id,
            @RequestBody DividaDTO dividaDTO){
        return dividaService.update(id, dividaDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        dividaService.delete(id);
    }

    @GetMapping("/health")
    public Map<String, Object> healthCheck() {
        return Map.of(
                "status", "OK",
                "timestamp", LocalDateTime.now()
        );
    }
}