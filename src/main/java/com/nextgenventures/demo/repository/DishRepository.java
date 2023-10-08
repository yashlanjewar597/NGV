package com.nextgenventures.demo.repository;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nextgenventures.demo.models.dish;


@Repository
public interface DishRepository extends MongoRepository<dish, ObjectId> {
    //List<dish> findDishByRestaurantId(ObjectId res_Id);
}
