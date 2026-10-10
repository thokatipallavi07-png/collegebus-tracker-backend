package com.collegebus.bus_tracker.service;

import com.collegebus.bus_tracker.model.Route;
import com.collegebus.bus_tracker.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Optional<Route> getRouteById(Long id) {
        return routeRepository.findById(id);
    }

    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

    public Route updateRoute(Long id, Route details) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found with id " + id));

        route.setRouteNumber(details.getRouteNumber());
        route.setRouteName(details.getRouteName());
        route.setStartPoint(details.getStartPoint());
        route.setEndPoint(details.getEndPoint());
        route.setDepartureTime(details.getDepartureTime());
        route.setReturnTime(details.getReturnTime());
        route.setStops(details.getStops());
        route.setTotalDistance(details.getTotalDistance());
        route.setEstimatedDuration(details.getEstimatedDuration());
        route.setActiveBusesCount(details.getActiveBusesCount());

        return routeRepository.save(route);
    }

    public void deleteRoute(Long id) {
        if (!routeRepository.existsById(id)) {
            throw new RuntimeException("Route not found with id " + id);
        }
        routeRepository.deleteById(id);
    }
}
