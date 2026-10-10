package com.collegebus.bus_tracker.repository;

import com.collegebus.bus_tracker.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    Optional<Bus> findByBusNumber(String busNumber);
    List<Bus> findByStatus(String status);
    List<Bus> findByRouteNameContainingIgnoreCase(String routeName);
    List<Bus> findByBusNumberContainingIgnoreCaseOrDriverNameContainingIgnoreCaseOrRouteNameContainingIgnoreCase(
            String busNumber, String driverName, String routeName
    );
}
