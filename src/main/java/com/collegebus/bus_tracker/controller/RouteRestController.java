package com.collegebus.bus_tracker.controller;

import com.collegebus.bus_tracker.model.Route;
import com.collegebus.bus_tracker.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/routes")
public class RouteRestController {

    private final RouteService routeService;

    @Autowired
    public RouteRestController(RouteService routeService) {
        this.routeService = routeService;
    }

    // READ: Get all routes
    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes() {
        return ResponseEntity.ok(routeService.getAllRoutes());
    }

    // READ: Get route by id
    @GetMapping("/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable Long id) {
        return routeService.getRouteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE: Add new route
    @PostMapping
    public ResponseEntity<Route> createRoute(@RequestBody Route route) {
        Route created = routeService.createRoute(route);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // UPDATE: Update route
    @PutMapping("/{id}")
    public ResponseEntity<Route> updateRoute(@PathVariable Long id, @RequestBody Route route) {
        try {
            Route updated = routeService.updateRoute(id, route);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Delete route
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteRoute(@PathVariable Long id) {
        try {
            routeService.deleteRoute(id);
            return ResponseEntity.ok(Map.of("message", "Route deleted successfully", "id", id.toString()));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
