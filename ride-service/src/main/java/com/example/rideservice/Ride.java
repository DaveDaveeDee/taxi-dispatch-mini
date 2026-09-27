package com.example.rideservice;

public class Ride {

    private Long id;
    private String pickupLocation;
    private RideStatus status;
    private Long assignedDriverId;

    public Ride() {
    }

    public Ride(Long id, String pickupLocation, RideStatus status, Long assignedDriverId) {
        this.id = id;
        this.pickupLocation = pickupLocation;
        this.status = status;
        this.assignedDriverId = assignedDriverId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

    public Long getAssignedDriverId() {
        return assignedDriverId;
    }

    public void setAssignedDriverId(Long assignedDriverId) {
        this.assignedDriverId = assignedDriverId;
    }
}