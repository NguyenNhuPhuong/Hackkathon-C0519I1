package com.store.eating.service;

import com.store.eating.model.Food;

public interface FoodService {
    Iterable<Food> findAll();

    Food findById(Long id);

    void save(Food store);

    void delete(Long id);
}
