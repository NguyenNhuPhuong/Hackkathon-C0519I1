package com.store.eating.service;

import com.store.eating.model.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface FoodService {
//    Iterable<Food> findAll();

    Page<Food> findAll(Pageable pageable);

    Page<Food> findAllByNameContaining(String name, Pageable pageable);

    Food findById(Long id);

    void save(Food store);

    void delete(Long id);
}
