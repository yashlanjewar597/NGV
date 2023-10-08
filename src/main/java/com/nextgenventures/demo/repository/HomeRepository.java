package com.nextgenventures.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nextgenventures.demo.models.home;


@Repository
public interface HomeRepository extends MongoRepository<home, String>{
    @Query("{" +
        "'schedule': {" +
        "'$elemMatch': {" +
        "'day': ?0, " +
        "'delivery.start': {$lte: ?1}, " +
        "'delivery.end': {$gte: ?1}" +
        "}" +
        "}" +
        "}")
    List<home> findOpenRestaurantsForDelivery(int dayOfWeek, Date DateTimeNow);

    @Query(value = "{" +
            "'geojson': {" +
            "$nearSphere: {" +
            "$geometry: {" +
            "type: 'Point', " +
            "coordinates: [ ?1, ?0 ]" +
            "}, " +
            "$maxDistance: ?2" +
            "}" +
            "}" +
            "}",
            sort = "{ 'geojson': 1 }")
    Page<home> findByLocationNear(double latitude, double longitude, double distance, Pageable pageable);


    @Query("{ " +
            " 'geojson.coordinates': { $nearSphere: { $geometry: { type: 'Point', coordinates: [ ?0, ?1 ] }, $maxDistance: ?2 } }, " +
            " 'schedule': { " +
            "   $elemMatch: { " +
            "       day: ?3, " +
            "       'delivery.start': { $lte: ?4 }, " +
            "       'delivery.end': { $gte: ?4 } " +
            "   } " +
            " } " +
            "}")
    Page<home> findHomesNearLocationAndOpenForDelivery(double longitude, double latitude, double maxDistance, int day, Date currentTime, Pageable pageable);
    
   
    
}