package com.nextgenventures.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class review {

    private String reviewer_name;

    private double rating;

    private String review_content;

}