package com.nextgenventures.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeospatialIndex;

import jakarta.annotation.PostConstruct;

@Configuration
public class MongoConfig {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoConfig(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // @PostConstruct
    // public void createGeoSpatialIndex() {
    //     mongoTemplate.indexOps("RestCollection").ensureIndex(
    //             //new GeospatialIndex("geojson").typed(GeoSpatialIndexType.GEO_2DSPHERE));
    // }
}

