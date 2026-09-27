package com.example.driverservice;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class DriverRepository {

    private final Map<Long, Driver> drivers = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public Driver save(Driver driver) {
        if (driver.getId() == null) {
            driver.setId(idCounter.incrementAndGet());
        }
        drivers.put(driver.getId(), driver);
        return driver;
    }

    public Collection<Driver> findAll() {
        return drivers.values();
    }

    public Driver findById(Long id) {
        return drivers.get(id);
    }

    public boolean deleteById(Long id) {
        return drivers.remove(id) != null;
    }
}