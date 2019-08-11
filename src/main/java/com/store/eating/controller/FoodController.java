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
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private StoreSerivce storeSerivce;

    @ModelAttribute("store")
    public Iterable<Store> stores() {
        return storeSerivce.findAll();
    }


    @GetMapping("/food")
    public ModelAndView list(@RequestParam("s") Optional<String> s ,@PageableDefault(size = 2) Pageable pageable) {
        Page<Food> foods;
        if (s.isPresent()){
            foods = foodService.findAllByNameContaining(s.get(),pageable);
        }else{
            foods = foodService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("food/index");
        modelAndView.addObject("foods", foods);
        return modelAndView;
    }

    @GetMapping("food-create")
    public ModelAndView showcreate() {
        ModelAndView modelAndView = new ModelAndView("food/create");
        modelAndView.addObject("food", new Food());
        return modelAndView;
    }

    @PostMapping("food-create")
    public ModelAndView save(@Validated @ModelAttribute("food") Food food, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("food/create");
            return modelAndView;
        }
        foodService.save(food);
        ModelAndView modelAndView = new ModelAndView("food/create");
        modelAndView.addObject("food", new Food());
        modelAndView.addObject("message", "Save successfully");
        return modelAndView;
    }


    @GetMapping("food-edit/{id}")
    public ModelAndView showedit(@PathVariable Long id) {
        Food food = foodService.findById(id);
        if (food != null) {
            ModelAndView modelAndView = new ModelAndView("food/edit");
            modelAndView.addObject("food", food);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error");
            return modelAndView;
        }
    }

    @PostMapping("food-edit")
    public ModelAndView update(@Validated @ModelAttribute Food food, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("food/edit");
            return modelAndView;
        }
        foodService.save(food);
        ModelAndView modelAndView = new ModelAndView("food/edit");
        modelAndView.addObject("food", food);
        modelAndView.addObject("message", "Update Successfully");
        return modelAndView;
    }


    @GetMapping("food-delete/{id}")
    public ModelAndView showdelete(@PathVariable Long id) {
        Food food = foodService.findById(id);
        ModelAndView modelAndView = new ModelAndView("food/delete");
        modelAndView.addObject("food", food);
        return modelAndView;
    }

    @PostMapping("food-delete")
    public String delete(@ModelAttribute("city") Food food) {
        foodService.delete(food.getId());
        return "redirect:food";
    }
}
