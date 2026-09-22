package com.alexandregomez.dividaapi.repository;

import com.alexandregomez.dividaapi.entity.DividaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DividaRepository extends JpaRepository<DividaEntity, Long> {
}
