package com.collegebus.bus_tracker.controller;

import com.collegebus.bus_tracker.model.Bus;
import com.collegebus.bus_tracker.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/buses")
public class BusRestController {

    private final BusService busService;

    @Autowired
    public BusRestController(BusService busService) {
        this.busService = busService;
    }

    // READ: Get all buses with optional filtering
    @GetMapping
    public ResponseEntity<List<Bus>> getAllBuses(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String status) {
        return ResponseEntity.ok(busService.getAllBuses(q, status));
    }

    // READ: Get bus by id
    @GetMapping("/{id}")
    public ResponseEntity<Bus> getBusById(@PathVariable Long id) {
        return busService.getBusById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE: Add new bus
    @PostMapping
    public ResponseEntity<Bus> createBus(@RequestBody Bus bus) {
        Bus created = busService.createBus(bus);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // UPDATE: Update existing bus details
    @PutMapping("/{id}")
    public ResponseEntity<Bus> updateBus(@PathVariable Long id, @RequestBody Bus bus) {
        try {
            Bus updated = busService.updateBus(id, bus);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // UPDATE / TELEMETRY: Quick live telemetry update
    @PatchMapping("/{id}/telemetry")
    public ResponseEntity<Bus> updateTelemetry(
            @PathVariable Long id,
            @RequestBody Map<String, Object> payload) {
        try {
            Double lat = payload.get("latitude") != null ? Double.valueOf(payload.get("latitude").toString()) : null;
            Double lng = payload.get("longitude") != null ? Double.valueOf(payload.get("longitude").toString()) : null;
            String location = (String) payload.get("currentLocation");
            String status = (String) payload.get("status");
            String speed = (String) payload.get("speed");
            String nextStop = (String) payload.get("nextStop");

            Bus updated = busService.updateLocation(id, lat, lng, location, status, speed, nextStop);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Delete bus by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteBus(@PathVariable Long id) {
        try {
            busService.deleteBus(id);
            return ResponseEntity.ok(Map.of("message", "Bus deleted successfully", "id", id.toString()));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET stats
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        return ResponseEntity.ok(busService.getStats());
    }
}
