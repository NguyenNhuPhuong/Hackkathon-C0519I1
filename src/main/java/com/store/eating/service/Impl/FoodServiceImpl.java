package com.store.eating.service.Impl;

import com.store.eating.model.Food;
import com.store.eating.repository.FoodRepository;
import com.store.eating.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;


    @Override
    public Page<Food> findAll(Pageable pageable) {
        return foodRepository.findAll(pageable);
    }

    @Override
    public Page<Food> findAllByNameContaining(String name, Pageable pageable) {
        return foodRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public Food findById(Long id) {
        return foodRepository.findOne(id);
    }

    @Override
    public void save(Food food) {
        foodRepository.save(food);
    }

    @Override
    public void delete(Long id) {
        foodRepository.delete(id);
    }
}
