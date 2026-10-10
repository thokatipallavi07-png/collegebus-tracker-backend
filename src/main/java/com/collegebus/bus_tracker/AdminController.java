package com.collegebus.bus_tracker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    // Store buses temporarily while the application is running
    private final List<Bus> buses = new ArrayList<>();

    // Admin page
    @GetMapping("/admin")
    public String admin(Model model) {

        // Add some default buses only when the list is empty
        if (buses.isEmpty()) {
            buses.add(new Bus(
                    "BUS-01",
                    "Keesara Route",
                    "Driver 1",
                    "Keesara",
                    "Running"
            ));

            buses.add(new Bus(
                    "BUS-02",
                    "ECIL Route",
                    "Driver 2",
                    "ECIL X Road",
                    "Running"
            ));

            buses.add(new Bus(
                    "BUS-03",
                    "Hyderabad Route",
                    "Driver 3",
                    "Hyderabad",
                    "Stopped"
            ));
        }

        model.addAttribute("buses", buses);

        return "admin";
    }

    // Open Add Bus page
    @GetMapping("/admin/add-bus")
    public String showAddBus() {
        return "add-bus";
    }

    // Receive Add Bus form
    @PostMapping("/admin/add-bus")
    public String addBus(
            @RequestParam String busNumber,
            @RequestParam String route,
            @RequestParam String driverName,
            @RequestParam String currentLocation,
            @RequestParam String status) {

        // Create new bus
        Bus newBus = new Bus(
                busNumber,
                route,
                driverName,
                currentLocation,
                status
        );

        // Save the bus
        buses.add(newBus);

        System.out.println("New Bus Added:");
        System.out.println("Bus Number: " + busNumber);
        System.out.println("Route: " + route);
        System.out.println("Driver: " + driverName);
        System.out.println("Location: " + currentLocation);
        System.out.println("Status: " + status);

        return "redirect:/admin";
    }

    // Simple Bus class
    public static class Bus {

        private String busNumber;
        private String route;
        private String driverName;
        private String currentLocation;
        private String status;

        public Bus(String busNumber, String route, String driverName,
                   String currentLocation, String status) {

            this.busNumber = busNumber;
            this.route = route;
            this.driverName = driverName;
            this.currentLocation = currentLocation;
            this.status = status;
        }

        public String getBusNumber() {
            return busNumber;
        }

        public String getRoute() {
            return route;
        }

        public String getDriverName() {
            return driverName;
        }

        public String getCurrentLocation() {
            return currentLocation;
        }

        public String getStatus() {
            return status;
        }
    }
}