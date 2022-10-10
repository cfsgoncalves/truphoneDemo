package com.truphone.demo.model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author claudiog
 */
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "device")
public class Device {

    private long id;
    // @NotEmpty(message = "Brand must not be empty")
    private String brand;
    // @NotEmpty(message = "Name must not be empty")
    private String name;
    private Instant creationTime;
 
    public Device() {
  
    }
 
    public Device(String name, String brand, Instant creationTime) {
         this.name = name;
         this.brand = brand;
         this.creationTime = creationTime;
    }

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
    @Column(name = "name", nullable=false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
 
    @Column(name = "brand", nullable=false)
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
 
    @Column(name = "creation_time")
    public Instant getCreationTime() {
        return creationTime;
    }
    public void setCreationTime(Instant time) {
        this.creationTime = time;
    }
    
    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", brand=" + brand + ", creationTime=" + creationTime + "]";
    }
 
}