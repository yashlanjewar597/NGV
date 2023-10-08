package com.nextgenventures.demo.models;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection = "RestCollection")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class home {

    @Id
    private ObjectId _id;

    private String restaurant_name;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint geojson;

    private String geohash;

    private String source;

    private String address;

    private String phone;

    private double avg_rating;

    private int no_of_ratings;

    private List<review> customer_reviews;

    private String restaurant_image;

    private String general_review;

    private List<schedule> schedule;

    private String cuisine;

}

    

