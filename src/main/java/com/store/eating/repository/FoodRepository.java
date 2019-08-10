package com.store.eating.repository;

import com.store.eating.model.Food;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FoodRepository extends PagingAndSortingRepository<Food, Long> {
}
