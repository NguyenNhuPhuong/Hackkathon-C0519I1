package com.store.eating.service;

import com.store.eating.model.Store;



public interface StoreSerivce {

    Iterable<Store> findAll();

    Store findById(Long id);

    void save(Store store);

    void delete(Long id);
}
