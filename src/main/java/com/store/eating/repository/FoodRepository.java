package com.store.eating.repository;

import com.store.eating.model.Food;
import com.store.eating.model.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FoodRepository extends PagingAndSortingRepository<Food, Long> {
    Page<Food> findAllByNameContaining(String name, Pageable pageable);

}
