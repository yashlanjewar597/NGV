package com.nextgenventures.demo.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate.Param;
import com.nextgenventures.demo.models.dish;
import com.nextgenventures.demo.service.DishService;

@RestController

@RequestMapping("/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    //@GetMapping("/all")
    //public 
    // List<dish> getDishes(@RequestParam("RestaurantId") ObjectId res_Id){
    //     return dishService.getAllDishes(res_Id);
    // }



}
