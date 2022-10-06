package com.truphone.demo.model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author claudiog
 */
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "device")
public class Device {

    private long id;
    @NotEmpty(message = "Brand must not be empty")
    private String brand;
    private Timestamp creationTime;
 
    public Device() {
  
    }
 
    public Device(String brand) {
         this.brand = brand;
    }
 
    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
 
    @Column(name = "brand", nullable = false)
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
 
    @Column(name = "creation_time")
    public Timestamp getCreationTime() {
        return creationTime;
    }
    public void setCreationTime(Timestamp time) {
        this.creationTime = time;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", brand=" + brand + ", creationTime=" + creationTime + "]";
    }
 
}