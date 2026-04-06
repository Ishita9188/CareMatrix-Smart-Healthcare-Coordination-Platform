package com.example.CareMatrix;

import jakarta.persistence.*;

@Entity
public class ResourceAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String resourceType; 
    private int availableUnits;

    public ResourceAllocation() {}

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getResourceType() { return resourceType; }

    public void setResourceType(String resourceType) { this.resourceType = resourceType; }

    public int getAvailableUnits() { return availableUnits; }

    public void setAvailableUnits(int availableUnits) { this.availableUnits = availableUnits; }
}