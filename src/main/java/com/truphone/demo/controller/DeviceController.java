/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.truphone.demo.controller;

/**
 *
 * @author claudiog
 */
import com.truphone.demo.model.Device;
import com.truphone.demo.repository.DeviceRepository;
import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DeviceController {
    
    @Autowired
    DeviceRepository deviceRepository;
    
    @GetMapping("/devices")
    public ResponseEntity<List<Device>> getAllDevices() {
        try {
            List<Device> devices = new ArrayList<>();
            deviceRepository.findAll().forEach(devices::add);

            if (devices.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(devices, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/device")
    public ResponseEntity<Device> createDevice(@Valid @RequestBody Device device) {
	try {
            Clock cl = Clock.systemUTC(); 
            Instant lt = Instant.now(cl);
            
            Device deviceResponse = deviceRepository.save(new Device(device.getName(), device.getBrand(), lt));
            return new ResponseEntity<>(deviceResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }
  
    @PutMapping("/device/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable("id") long id, @RequestBody Device newDevice) {
        Optional<Device> deviceData = deviceRepository.findById(id);

        if (deviceData.isPresent()) {
            Device oldDevice = deviceData.get();
            
            if(!Objects.isNull(newDevice.getName())){
                oldDevice.setName(newDevice.getName());
            }
            
            if(!Objects.isNull(newDevice.getBrand())){
                oldDevice.setBrand(newDevice.getBrand());
            }
            
            return new ResponseEntity<>(deviceRepository.save(oldDevice), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/device/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable("id") long id) {
        Optional<Device> deviceData = deviceRepository.findById(id);

        if (deviceData.isPresent()) {
            return new ResponseEntity<>(deviceData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/device/{id}")
    public ResponseEntity<HttpStatus> deleteDeviceById(@PathVariable("id") long id) {
        try {
            Optional<Device> deviceData = deviceRepository.findById(id);
            if (deviceData.isPresent()) {
                deviceRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/device/brand/{brand}")
    public ResponseEntity<List<Device>> getDevicesByBrand(@PathVariable("brand") String brand) {
        try {
            List<Device> devices = new ArrayList<>();
            deviceRepository.findByBrand(brand).forEach(devices::add);

            if (devices.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(devices, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/device")
    public ResponseEntity<String> findByPublished() {
        return new ResponseEntity<>("Hello", HttpStatus.ACCEPTED);
    }

}
