package com.nextgenventures.demo.controller;

import java.util.List;
import com.nextgenventures.demo.models.home;
import com.nextgenventures.demo.models.response;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.mongodb.core.geo.GeoJsonModule;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.nextgenventures.demo.service.HomeService;



@RestController
@RequestMapping("/home")

public class HomeController {


    @Autowired
    private HomeService HomeService;

    private boolean checkToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            // Perform token validation here if necessary
            return true; // Token is valid
        }
        return false; // Token is invalid
    }

    @GetMapping("/user")
    public Object getData(@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                          @RequestParam("customerId") String customerId, @RequestParam("pageNumber") int pageNumber) {
        if (!checkToken(authorizationHeader)) {
            return "Unauthorized";
        }
        return HomeService.getHotel(customerId, pageNumber);
    }

    @GetMapping("/nearRest")
    public Object getNearbyrestaurant(@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                                      @RequestParam("Latitude") double Latitude, @RequestParam("Longitude") double Longitude,
                                      @RequestParam("Radius") double Radius, @RequestParam("pageNumber") int pageNumber) {
        if (!checkToken(authorizationHeader)) {
            return "Unauthorized";
        }
        return HomeService.NewQ(Latitude, Longitude, Radius, pageNumber);
    }

    @GetMapping("/user/current")
    public Object getNearbyHotel(@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                                 @RequestParam("customerId") String customerId, @RequestParam("pageNumber") int pageNumber,
                                 @RequestParam("Latitude") double Latitude, @RequestParam("Longitude") double Longitude,
                                 @RequestParam("Radius") double Radius) {
        if (!checkToken(authorizationHeader)) {
            return "Unauthorized";
        }
        return HomeService.finalQuery(Latitude, Longitude, Radius, pageNumber);
    }

    @GetMapping("/user/current/test")
    public Object getTest(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        if (!checkToken(authorizationHeader)) {
            return "Unauthorized";
        }
        return "hello testing";
    }

    @GetMapping("/data")
    public Object getRestra(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        if (!checkToken(authorizationHeader)) {
            return "Unauthorized";
        }
        return HomeService.filterByTimeOnly();
    }

    @GetMapping("/testToken")
    public Object testToken(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        if (!checkToken(authorizationHeader)) {
            return "Unauthorized";
        }
        // Log the token to the console
        System.out.println("Authorization Header: " + authorizationHeader);
        // Return the token in the response
        return "Received token: " + authorizationHeader;
    }

    
}
