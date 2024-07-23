package com.nextgenventures.demo.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.nextgenventures.demo.models.home;
import com.nextgenventures.demo.models.response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.nextgenventures.demo.service.HomeService;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/user")
    public List<home> getData(@RequestParam("customerId") String customerId, @RequestParam("pageNumber") int pageNumber, HttpServletRequest request) {
        String accessToken = extractAccessToken(request);
        System.out.println("Access Token: " + accessToken);
        return homeService.getHotel(customerId, pageNumber);
    }

    @GetMapping("/nearRest")
    public List<response> getNearbyRestaurant(@RequestParam("Latitude") double Latitude, @RequestParam("Longitude") double Longitude, @RequestParam("Radius") double Radius, @RequestParam("pageNumber") int pageNumber, HttpServletRequest request) {
        String accessToken = extractAccessToken(request);
        System.out.println("Access Token: " + accessToken);
        return homeService.NewQ(Latitude, Longitude, Radius, pageNumber);
    }

    @GetMapping("/user/current")
    public List<home> getNearbyHotel(@RequestParam("customerId") String customerId, @RequestParam("pageNumber") int pageNumber, @RequestParam("Latitude") double Latitude, @RequestParam("Longitude") double Longitude, @RequestParam("Radius") double Radius, HttpServletRequest request) {
        String accessToken = extractAccessToken(request);
        System.out.println("Access Token: " + accessToken);
        return homeService.finalQuery(Latitude, Longitude, Radius, pageNumber);
    }

    @GetMapping("/user/current/test")
    public String getTest() {
        return "hello testing";
    }

    @GetMapping("/data")
    public List<home> getRestra() {
        return homeService.filterByTimeOnly();
    }

    private String extractAccessToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // Extract token after "Bearer "
        }
        return null; // or throw an exception if the token is required
    }
}
