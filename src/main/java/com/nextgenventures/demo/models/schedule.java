package com.nextgenventures.demo.models;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class schedule {
    private int day;
    private List<time> pickup;
    private List<time> delivery;
    

}
