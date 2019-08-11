package com.store.eating.service.Impl;

import com.store.eating.model.Store;
import com.store.eating.repository.StoreRepository;
import com.store.eating.service.StoreSerivce;
import org.springframework.beans.factory.annotation.Autowired;



public class StoreSericeImpl implements StoreSerivce {

    @Autowired
    private StoreRepository storeRepository;


    @Override
    public Iterable<Store> findAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store findById(Long id) {
        return storeRepository.findOne(id);
    }

    @Override
    public void save(Store store) {
        storeRepository.save(store);
    }

    @Override
    public void delete(Long id) {
        storeRepository.delete(id);
    }
}
