package com.store.eating.repository;


import com.store.eating.model.Store;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface StoreRepository extends PagingAndSortingRepository<Store, Long> {


}
