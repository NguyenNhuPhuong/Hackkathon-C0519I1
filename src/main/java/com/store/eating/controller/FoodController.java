package com.store.eating.controller;


import com.store.eating.model.Food;
import com.store.eating.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FoodController  {

    @Autowired
    private FoodService foodService;



    @GetMapping("food")
    public ModelAndView list (){
        Iterable<Food> foods = foodService.findAll();
        ModelAndView modelAndView = new ModelAndView("food/index");
        modelAndView.addObject("foods",foods);
        return modelAndView;
    }

    @GetMapping("food-create")
    public ModelAndView showcreate() {
        ModelAndView modelAndView = new ModelAndView("food/create");
        modelAndView.addObject("food", new Food());
        return modelAndView;
    }

    @PostMapping("food-create")
    public ModelAndView save(@ModelAttribute("food") Food food) {
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
    public ModelAndView update(@ModelAttribute Food food){
        foodService.save(food);
        ModelAndView modelAndView = new ModelAndView("food/edit");
        modelAndView.addObject("food",food);
        modelAndView.addObject("message","Update Successfully");
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
