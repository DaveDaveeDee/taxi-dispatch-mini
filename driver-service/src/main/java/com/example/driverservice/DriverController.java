package com.example.driverservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverController(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @GetMapping
    public Collection<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long id) {
        Driver driver = driverRepository.findById(id);
        if (driver == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(driver);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Driver createDriver(@RequestBody Driver driver) {
        driver.setId(null); // säkerställ att klienten inte kan sätta eget id
        return driverRepository.save(driver);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Driver> updateStatus(@PathVariable Long id, @RequestBody DriverStatus status) {
        Driver driver = driverRepository.findById(id);
        if (driver == null) {
            return ResponseEntity.notFound().build();
        }
        driver.setStatus(status);
        driverRepository.save(driver);
        return ResponseEntity.ok(driver);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        boolean deleted = driverRepository.deleteById(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}