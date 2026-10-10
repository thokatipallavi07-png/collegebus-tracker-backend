package com.collegebus.bus_tracker.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "buses")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String busNumber;

    @Column(nullable = false)
    private String routeName;

    @Column(nullable = false)
    private String driverName;

    private String driverPhone;

    @Column(nullable = false)
    private String currentLocation;

    @Column(nullable = false)
    private String status; // "Running", "Delayed", "Completed", "Maintenance"

    private Double latitude;

    private Double longitude;

    private Integer capacity = 50;

    private Integer occupiedSeats = 0;

    private String speed = "0 km/h";

    private String estimatedArrival;

    private String nextStop;

    private LocalDateTime lastUpdated;

    public Bus() {
        this.lastUpdated = LocalDateTime.now();
    }

    public Bus(String busNumber, String routeName, String driverName, String driverPhone, 
               String currentLocation, String status, Double latitude, Double longitude, 
               Integer capacity, Integer occupiedSeats, String speed, String estimatedArrival, 
               String nextStop) {
        this.busNumber = busNumber;
        this.routeName = routeName;
        this.driverName = driverName;
        this.driverPhone = driverPhone;
        this.currentLocation = currentLocation;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
        this.capacity = capacity;
        this.occupiedSeats = occupiedSeats;
        this.speed = speed;
        this.estimatedArrival = estimatedArrival;
        this.nextStop = nextStop;
        this.lastUpdated = LocalDateTime.now();
    }

    @PrePersist
    @PreUpdate
    public void onUpdate() {
        this.lastUpdated = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getOccupiedSeats() {
        return occupiedSeats;
    }

    public void setOccupiedSeats(Integer occupiedSeats) {
        this.occupiedSeats = occupiedSeats;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getEstimatedArrival() {
        return estimatedArrival;
    }

    public void setEstimatedArrival(String estimatedArrival) {
        this.estimatedArrival = estimatedArrival;
    }

    public String getNextStop() {
        return nextStop;
    }

    public void setNextStop(String nextStop) {
        this.nextStop = nextStop;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
