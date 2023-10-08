package com.nextgenventures.demo.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection="DishCollection")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class dish {
    
    @Id
    private ObjectId _id;

    private ObjectId restaurant_id;

    private String dish_name;

    private String price;

    private String dish_desc;

    private String dish_image_link;

    private List<review> dish_reviews;

    private boolean best_seller;

    
}
