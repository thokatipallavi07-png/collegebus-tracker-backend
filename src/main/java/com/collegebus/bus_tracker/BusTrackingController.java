package com.collegebus.bus_tracker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BusTrackingController {

    @GetMapping("/tracking")
    public String tracking() {
        return "tracking";
    }
}