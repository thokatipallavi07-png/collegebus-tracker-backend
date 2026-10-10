package com.collegebus.bus_tracker.service;

import com.collegebus.bus_tracker.model.Bus;
import com.collegebus.bus_tracker.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class BusService {

    private final BusRepository busRepository;

    @Autowired
    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public List<Bus> getAllBuses(String query, String status) {
        if (query != null && !query.trim().isEmpty()) {
            String trimmed = query.trim();
            return busRepository.findByBusNumberContainingIgnoreCaseOrDriverNameContainingIgnoreCaseOrRouteNameContainingIgnoreCase(
                    trimmed, trimmed, trimmed
            );
        }
        if (status != null && !status.trim().isEmpty() && !status.equalsIgnoreCase("ALL")) {
            return busRepository.findByStatus(status.trim());
        }
        return busRepository.findAll();
    }

    public Optional<Bus> getBusById(Long id) {
        return busRepository.findById(id);
    }

    public Bus createBus(Bus bus) {
        bus.setLastUpdated(LocalDateTime.now());
        if (bus.getStatus() == null || bus.getStatus().isEmpty()) {
            bus.setStatus("Running");
        }
        return busRepository.save(bus);
    }

    public Bus updateBus(Long id, Bus busDetails) {
        Bus bus = busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus not found with id " + id));

        bus.setBusNumber(busDetails.getBusNumber());
        bus.setRouteName(busDetails.getRouteName());
        bus.setDriverName(busDetails.getDriverName());
        bus.setDriverPhone(busDetails.getDriverPhone());
        bus.setCurrentLocation(busDetails.getCurrentLocation());
        bus.setStatus(busDetails.getStatus());
        if (busDetails.getLatitude() != null) bus.setLatitude(busDetails.getLatitude());
        if (busDetails.getLongitude() != null) bus.setLongitude(busDetails.getLongitude());
        if (busDetails.getCapacity() != null) bus.setCapacity(busDetails.getCapacity());
        if (busDetails.getOccupiedSeats() != null) bus.setOccupiedSeats(busDetails.getOccupiedSeats());
        if (busDetails.getSpeed() != null) bus.setSpeed(busDetails.getSpeed());
        if (busDetails.getEstimatedArrival() != null) bus.setEstimatedArrival(busDetails.getEstimatedArrival());
        if (busDetails.getNextStop() != null) bus.setNextStop(busDetails.getNextStop());
        bus.setLastUpdated(LocalDateTime.now());

        return busRepository.save(bus);
    }

    public Bus updateLocation(Long id, Double lat, Double lng, String locationName, String status, String speed, String nextStop) {
        Bus bus = busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus not found with id " + id));

        if (lat != null) bus.setLatitude(lat);
        if (lng != null) bus.setLongitude(lng);
        if (locationName != null && !locationName.isEmpty()) bus.setCurrentLocation(locationName);
        if (status != null && !status.isEmpty()) bus.setStatus(status);
        if (speed != null) bus.setSpeed(speed);
        if (nextStop != null) bus.setNextStop(nextStop);
        bus.setLastUpdated(LocalDateTime.now());

        return busRepository.save(bus);
    }

    public void deleteBus(Long id) {
        if (!busRepository.existsById(id)) {
            throw new RuntimeException("Bus not found with id " + id);
        }
        busRepository.deleteById(id);
    }

    public Map<String, Object> getStats() {
        List<Bus> all = busRepository.findAll();
        long total = all.size();
        long running = all.stream().filter(b -> "Running".equalsIgnoreCase(b.getStatus())).count();
        long delayed = all.stream().filter(b -> "Delayed".equalsIgnoreCase(b.getStatus())).count();
        long stopped = all.stream().filter(b -> "Stopped".equalsIgnoreCase(b.getStatus()) || "Completed".equalsIgnoreCase(b.getStatus())).count();
        long maintenance = all.stream().filter(b -> "Maintenance".equalsIgnoreCase(b.getStatus())).count();
        int totalCapacity = all.stream().mapToInt(b -> b.getCapacity() != null ? b.getCapacity() : 0).sum();
        int totalOccupied = all.stream().mapToInt(b -> b.getOccupiedSeats() != null ? b.getOccupiedSeats() : 0).sum();

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalBuses", total);
        stats.put("runningBuses", running);
        stats.put("delayedBuses", delayed);
        stats.put("stoppedBuses", stopped);
        stats.put("maintenanceBuses", maintenance);
        stats.put("totalCapacity", totalCapacity);
        stats.put("totalOccupied", totalOccupied);
        stats.put("occupancyPercentage", totalCapacity > 0 ? Math.round(((double) totalOccupied / totalCapacity) * 100) : 0);

        return stats;
    }
}
