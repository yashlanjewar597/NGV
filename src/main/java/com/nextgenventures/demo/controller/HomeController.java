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
import org.springframework.web.bind.annotation.RestController;
import com.nextgenventures.demo.service.HomeService;



@RestController
@RequestMapping("/home")

public class HomeController {


    @Autowired
    private HomeService HomeService;

    @GetMapping("/user")
    public List<home> getData(@RequestParam("customerId") String customerId, @RequestParam("pageNumber") int pageNumber){
        // String accessToken = extractAccessToken(request);
        // System.out.println("Access Token: " + accessToken);
        return HomeService.getHotel(customerId, pageNumber);
    }

    @GetMapping("/nearRest")
    public List<response> getNearbyrestaurant(@RequestParam("Latitude") double Latitude, @RequestParam("Longitude") double Longitude, @RequestParam("Radius") double Radius, @RequestParam("pageNumber") int pageNumber){
       // String accessToken = extractAccessToken(request);
       //  System.out.println("Access Token: " + accessToken);
        return HomeService.NewQ(Latitude, Longitude, Radius, pageNumber);
       //return HomeService.getNearestHotel(Latitude, Longitude, Radius);
    }

    @GetMapping("/user/current")
    public List<home> getNearbyHotel(@RequestParam("customerId") String customerId, @RequestParam("pageNumber") int pageNumber, @RequestParam("Latitude") double Latitude, @RequestParam("Longitude") double Longitude, @RequestParam("Radius") double Radius){
        // String accessToken = extractAccessToken(request);
        // System.out.println("Access Token: " + accessToken);
        return HomeService.finalQuery(Latitude, Longitude, Radius, pageNumber);
    }

    @GetMapping("/user/current/test")
    public String getTest(){
        return "hello testing";
    }

    @GetMapping("/data")
    public List<home> getRestra(){
        return HomeService.filterByTimeOnly();
    }

    @GetMapping("/testToken")
    public String testToken(@RequestHeader("Authorization") String authorizationHeader) {
        // Log the token to the console
        System.out.println("Authorization Header: " + authorizationHeader);
        
        // Return the token in the response
        return "Received token: " + authorizationHeader;
    }

    // private String extractAccessToken(HttpServletRequest request) {
    //     String authorizationHeader = request.getHeader("Authorization");
    //     if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
    //         return authorizationHeader.substring(7); // Extract token after "Bearer "
    //     }
    //     return null; // or throw an exception if the token is required
    // }

    
}
