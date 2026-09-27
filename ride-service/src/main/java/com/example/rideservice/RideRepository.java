package com.example.rideservice;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class RideRepository {

    private final Map<Long, Ride> rides = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public Ride save(Ride ride) {
        if (ride.getId() == null) {
            ride.setId(idCounter.incrementAndGet());
        }
        rides.put(ride.getId(), ride);
        return ride;
    }

    public Collection<Ride> findAll() {
        return rides.values();
    }

    public Ride findById(Long id) {
        return rides.get(id);
    }
}