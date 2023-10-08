package com.nextgenventures.demo.service;

import java.util.List;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;


import com.nextgenventures.demo.TimeComparison;
import com.nextgenventures.demo.models.home;
import com.nextgenventures.demo.models.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nextgenventures.demo.models.schedule;
import com.nextgenventures.demo.models.time;
import com.nextgenventures.demo.repository.HomeRepository;

@Service
public class HomeService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public HomeService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Autowired
    private HomeRepository homeRepository;



    // ---------------------------For Getting First Five restaurents from database------------------//

    public List<home> getHotel(String customerId, int pageNumber){

        Pageable pageable = PageRequest.of(pageNumber,50);
        return homeRepository.findAll(pageable).getContent();
    }


    //------------------------------To get first five nearest hotels from lattitude -------------->

    public List<home> getNearestHotel(double Latitude, double Longitude, double Radius){
    
        Pageable pageable = PageRequest.of(0,110);
        return homeRepository.findByLocationNear(Latitude,Longitude, Radius,pageable).getContent();
    }

   
    public List<home> filterByTimeOnly() {

        ZonedDateTime utcTime = ZonedDateTime.now(ZoneId.of("UTC"));

        int customDay = utcTime.getDayOfWeek().getValue() - 1;

        Date dateTimeNow = Date.from(utcTime.toInstant());
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTimeNow);

       
        int newYear = 2023;
        int newMonth = 0; // 0-based month (January is 0)
        int newDay = 1;
        
        calendar.set(Calendar.YEAR, newYear);
        calendar.set(Calendar.MONTH, newMonth);
        calendar.set(Calendar.DAY_OF_MONTH, newDay);

        
        Date updatedDate = calendar.getTime();

         // Print the java.util.Date object
        System.out.println("Date and Time: " + dateTimeNow +"Day" + customDay);
        System.out.println("Date and Time: " + updatedDate + "Day" + customDay);

        return homeRepository.findOpenRestaurantsForDelivery(customDay,updatedDate);


    }


    public List<home> finalQuery(double Latitude, double Longitude, double Radius, int pageNumber){

        ZonedDateTime utcTime = ZonedDateTime.now(ZoneId.of("UTC"));

        int customDay = utcTime.getDayOfWeek().getValue() - 1;

        Date dateTimeNow = Date.from(utcTime.toInstant());
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTimeNow);

       
        int newYear = 2023;
        int newMonth = 0; // 0-based month (January is 0)
        int newDay = 1;
        
        calendar.set(Calendar.YEAR, newYear);
        calendar.set(Calendar.MONTH, newMonth);
        calendar.set(Calendar.DAY_OF_MONTH, newDay);

      
        Date updatedDate = calendar.getTime();

        Pageable pageable = PageRequest.of(pageNumber,110);

        return homeRepository.findHomesNearLocationAndOpenForDelivery(Longitude, Latitude,Radius, customDay,updatedDate, pageable).getContent();
    }

//---------------------------------------Fetching Appropriate Dish data------------------------------//
    public List<home> finalDishQuery(double Latitude, double Longitude, double Radius, int pageNumber){

        ZonedDateTime utcTime = ZonedDateTime.now(ZoneId.of("UTC"));

        int customDay = utcTime.getDayOfWeek().getValue() - 1;

        Date dateTimeNow = Date.from(utcTime.toInstant());
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTimeNow);

       
        int newYear = 2023;
        int newMonth = 0; // 0-based month (January is 0)
        int newDay = 1;
        
        calendar.set(Calendar.YEAR, newYear);
        calendar.set(Calendar.MONTH, newMonth);
        calendar.set(Calendar.DAY_OF_MONTH, newDay);

      
        Date updatedDate = calendar.getTime();

        Pageable pageable = PageRequest.of(pageNumber,45);

        List<home> restaurants = homeRepository.findHomesNearLocationAndOpenForDelivery(Longitude, Latitude,Radius, customDay,updatedDate, pageable).getContent();
        return homeRepository.findHomesNearLocationAndOpenForDelivery(Longitude, Latitude,Radius, customDay,updatedDate, pageable).getContent();
    }

//--------------------------------------------------NewQ--------------------------------------------------//

    public List<response> NewQ(double Latitude, double Longitude, double Radius, int pageNumber){



        ZonedDateTime utcTime = ZonedDateTime.now(ZoneId.of("UTC"));

        int customDay = utcTime.getDayOfWeek().getValue() - 1;

        Date dateTimeNow = Date.from(utcTime.toInstant());
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTimeNow);

       
        int newYear = 2023;
        int newMonth = 0; // 0-based month (January is 0)
        int newDay = 1;
        
        calendar.set(Calendar.YEAR, newYear);
        calendar.set(Calendar.MONTH, newMonth);
        calendar.set(Calendar.DAY_OF_MONTH, newDay);

      
        Date updatedDate = calendar.getTime();


        Aggregation aggregation = Aggregation.newAggregation(

        

        Aggregation.geoNear(NearQuery.near(new GeoJsonPoint(Longitude, Latitude))
        .maxDistance(Radius)
        .spherical(true)
        .distanceMultiplier(6371), "distance"),

        

        //Aggregation.limit(1),
    // Match restaurants within 5 km range and open for delivery
        Aggregation.match(Criteria.where("schedule").elemMatch(
        Criteria.where("day").is(customDay)
            .and("delivery.start").lte(updatedDate)
            .and("delivery.end").gte(updatedDate)
    )),       
        
    Aggregation.skip(pageNumber * 5 ),  // 5 is Page Size
    Aggregation.limit(5),                            
    
    // Add a $geoNear stage after $match to perform the geospatial query
    
    
    // Lookup dishes for each restaurant
    Aggregation.lookup("DishCollection", "_id", "restaurant_id", "dishes"),
    
    Aggregation.unwind("dishes"),
    // Project to shape the final result
    Aggregation.project()
        .and("_id").as("restaurant_id")
        .and("restaurant_name").as("restaurantName")
        .and("avg_rating").as("restaurantAvgRating")
        .and("customer_reviews").as("restaurantReviews")
        .and("dishes.dish_name").as("dishName")
        .and("dishes.price").as("dishPrice")
        .and("dishes.dish_reviews").as("dishReviews")
        .and("dishes.dish_image_link").as("dish_image_link")
);

AggregationResults<response> results = mongoTemplate.aggregate(aggregation, "RestCollection", response.class);
return results.getMappedResults();
    }

}

