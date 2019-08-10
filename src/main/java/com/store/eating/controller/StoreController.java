package com.store.eating.controller;


import com.store.eating.model.Food;
import com.store.eating.model.Store;
import com.store.eating.service.FoodService;
import com.store.eating.service.StoreSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StoreController {

    @Autowired
    private StoreSerivce storeSerivce;

    @Autowired
    private FoodService foodService;

    @ModelAttribute("food")
    public Iterable<Food> foods() {
        return foodService.findAll();
    }

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
    public ModelAndView save(@ModelAttribute("store") Store store) {
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
    public ModelAndView update(@ModelAttribute Store store) {
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
