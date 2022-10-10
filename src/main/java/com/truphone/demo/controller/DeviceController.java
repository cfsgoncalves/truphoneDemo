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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DeviceController {
    
    Logger logger = LoggerFactory.getLogger(DeviceController.class);    
    
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

            logger.info("All devices returned with sucess");
            return new ResponseEntity<>(devices, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Internal server error while executing getAllDevices()");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/device")
    public ResponseEntity<Device> createDevice(@Valid @RequestBody Device device) {
	try {
            Clock clock = Clock.systemUTC(); 
            Instant instant = Instant.now(clock);
            
            Device newDevice = new Device(device.getName(), device.getBrand(), instant);
            Device deviceResponse = deviceRepository.save(newDevice);
                        
            logger.info("Device created with sucess: " + newDevice.toString());
            return new ResponseEntity<>(deviceResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Internal server error while creating a device");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }
  
    @PutMapping("/device/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable("id") long id, @RequestBody Device newDevice) {
        Optional<Device> deviceData = deviceRepository.findById(id);

        if (deviceData.isPresent()) {
            newDevice.setId(id);
            newDevice.setCreationTime(deviceData.get().getCreationTime());
            Device deviceSaved = deviceRepository.save(newDevice);
            logger.info("Device was substituted sucessfully: " + newDevice.toString());
            return new ResponseEntity<>(deviceSaved, HttpStatus.OK);
        } else {
            logger.warn("Device that you are trying to update was not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PatchMapping("/device/{id}")
    public ResponseEntity<Device> updateDevicePartially(@PathVariable("id") long id, @RequestBody Device newDevice) {
        Optional<Device> deviceData = deviceRepository.findById(id);

        if (deviceData.isPresent()) {
            Device oldDevice = deviceData.get();
            
            if(!Objects.isNull(newDevice.getName())){
                oldDevice.setName(newDevice.getName());
            }
            
            if(!Objects.isNull(newDevice.getBrand())){
                oldDevice.setBrand(newDevice.getBrand());
            }
            
            Device deviceSaved = deviceRepository.save(oldDevice);
            logger.info("Device was partially updated sucessfully: " + oldDevice.toString());
            return new ResponseEntity<>(deviceSaved, HttpStatus.OK);
        } else {
            logger.warn("Device that you are trying to update was not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/device/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable("id") long id) {
        Optional<Device> deviceData = deviceRepository.findById(id);

        if (deviceData.isPresent()) {
            logger.warn("Device was fetched sucessfully: " + deviceData.toString());
            return new ResponseEntity<>(deviceData.get(), HttpStatus.OK);
        } else {
            logger.warn("Device that you are trying to fetch was not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/device/{id}")
    public ResponseEntity<HttpStatus> deleteDeviceById(@PathVariable("id") long id) {
        try {
            Optional<Device> deviceData = deviceRepository.findById(id);
            if (deviceData.isPresent()) {
                deviceRepository.deleteById(id);
                logger.info("Device was deleted sucessfully: " + deviceData.toString());
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                logger.warn("Device was not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Internal server error while deleting a device");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/device/brand/{brand}")
    public ResponseEntity<List<Device>> getDevicesByBrand(@PathVariable("brand") String brand) {
        try {
            List<Device> devices = new ArrayList<>();
            deviceRepository.findByBrand(brand).forEach(devices::add);

            if (devices.isEmpty()) {
                logger.warn("Devices were not found for the brand " + brand);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            logger.info("Devices by brand were found: " + devices.toString());
            return new ResponseEntity<>(devices, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Internal server error while searching a devices by a brand");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
