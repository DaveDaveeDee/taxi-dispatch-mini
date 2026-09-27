package com.example.driverservice;

public class Driver {

    private Long id;
    private String name;
    private DriverStatus status;

    public Driver() {
    }

    public Driver(Long id, String name, DriverStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

}