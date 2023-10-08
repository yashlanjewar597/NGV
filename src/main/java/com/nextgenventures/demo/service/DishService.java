package com.nextgenventures.demo.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextgenventures.demo.models.dish;
import com.nextgenventures.demo.repository.DishRepository;

@Service
public class DishService{


    @Autowired
    private DishRepository dishRepository;

    // public List<dish> getAllDishes(ObjectId res_Id){
    //     return dishRepository.findDishByRestaurantId(res_Id);
    // }
}

