package com.nextgenventures.demo.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//@Document(collection="DishCollection")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class response {
    
    private ObjectId restaurant_id;
    private String restaurantName;
    private double restaurantAvgRating;
    private List<review> restaurantReviews;
    private String dishName;
    private String dishPrice;
    private List<review> dishReviews;
    private String dish_image_link;
    


    
}