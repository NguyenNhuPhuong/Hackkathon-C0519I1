package com.store.eating.controller;


import com.store.eating.model.Food;
import com.store.eating.model.Store;
import com.store.eating.service.FoodService;
import com.store.eating.service.StoreSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
public class StoreController {

    @Autowired
    private StoreSerivce storeSerivce;

    @Autowired
    private FoodService foodService;


    @GetMapping("store")
    public ModelAndView list() {
        Iterable<Store> stores = storeSerivce.findAll();
        ModelAndView modelAndView = new ModelAndView("store/index");
        modelAndView.addObject("stores", stores);
        return modelAndView;
    }

    @GetMapping("store-create")
    public ModelAndView showcreate() {
        ModelAndView modelAndView = new ModelAndView("store/create");
        modelAndView.addObject("store", new Store());
        return modelAndView;
    }

    @PostMapping("store-create")
    public ModelAndView save(@Validated @ModelAttribute("store") Store store, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("store/create");
            return modelAndView;
        }
        storeSerivce.save(store);
        ModelAndView modelAndView = new ModelAndView("store/create");
        modelAndView.addObject("store", new Store());
        modelAndView.addObject("message", "Save successfully");
        return modelAndView;
    }


    @GetMapping("store-edit/{id}")
    public ModelAndView showedit(@PathVariable Long id) {
        Store store = storeSerivce.findById(id);
        if (store != null) {
            ModelAndView modelAndView = new ModelAndView("store/edit");
            modelAndView.addObject("store", store);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error");
            return modelAndView;
        }
    }

    @PostMapping("store-edit")
    public ModelAndView update(@Validated @ModelAttribute Store store, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("store/edit");
            return modelAndView;
        }
        storeSerivce.save(store);
        ModelAndView modelAndView = new ModelAndView("store/edit");
        modelAndView.addObject("store", store);
        modelAndView.addObject("message", "Update Successfully");
        return modelAndView;
    }


    @GetMapping("store-delete/{id}")
    public ModelAndView showdelete(@PathVariable Long id) {
        Store store = storeSerivce.findById(id);
        ModelAndView modelAndView = new ModelAndView("store/delete");
        modelAndView.addObject("store", store);
        return modelAndView;
    }

    @PostMapping("store-delete")
    public String delete(@ModelAttribute("store") Store store) {
        storeSerivce.delete(store.getId());
        return "redirect:store";
    }
}
