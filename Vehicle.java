package com.example;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private String name;
    private List<Part> parts;

    public Vehicle(String name) {
        this.name = name;
        this.parts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Part> getAllParts() {
        return parts;
    }

    public void addPart(Part part) {
        parts.add(part);
    }

    public static List<Vehicle> getSampleVehicles() {
        List<Vehicle> sampleVehicles = new ArrayList<>();
        sampleVehicles.add(new Vehicle("Sedan"));
        sampleVehicles.add(new Vehicle("SUV"));
        sampleVehicles.add(new Vehicle("Hatchback"));
        sampleVehicles.add(new Vehicle("Truck"));
        sampleVehicles.add(new Vehicle("Convertible"));
        return sampleVehicles;
    }

    @Override
    public String toString() {
        return name;
    }
}
