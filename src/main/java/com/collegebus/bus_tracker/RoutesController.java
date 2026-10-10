package com.collegebus.bus_tracker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoutesController {

    @GetMapping("/routes")
    public String routes() {
        return "routes";
    }
}