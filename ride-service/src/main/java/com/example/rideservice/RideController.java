package com.example.rideservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/rides")
public class RideController {

    private final RideRepository rideRepository;
    private final DriverClient driverClient;

    public RideController(RideRepository rideRepository, DriverClient driverClient) {
        this.rideRepository = rideRepository;
        this.driverClient = driverClient;
    }

    @GetMapping
    public Collection<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ride> getRideById(@PathVariable Long id) {
        Ride ride = rideRepository.findById(id);
        if (ride == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ride);
    }

    @PostMapping
    public ResponseEntity<Ride> createRide(@RequestBody Ride ride) {
        ride.setId(null);
        ride.setStatus(RideStatus.REQUESTED);

        Optional<DriverDto> availableDriver = driverClient.findFirstAvailableDriver();

        if (availableDriver.isEmpty()) {
            ride.setStatus(RideStatus.REQUESTED);
            rideRepository.save(ride);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(ride);
        }

        DriverDto driver = availableDriver.get();
        ride.setAssignedDriverId(driver.getId());
        ride.setStatus(RideStatus.ASSIGNED);
        rideRepository.save(ride);

        driverClient.markDriverAsBusy(driver.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(ride);
    }
}