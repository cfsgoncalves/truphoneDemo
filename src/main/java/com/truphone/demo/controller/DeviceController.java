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

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DeviceController {
    
    @Autowired
    DeviceRepository deviceRepository;

    @PostMapping("/device")
    public ResponseEntity<Device> createDevice(@Valid @RequestBody Device device) {
	try {
            Device deviceResponse = deviceRepository.save(device);
            return new ResponseEntity<>(deviceResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }
    
    @GetMapping("/test")
    public ResponseEntity<String> findByPublished() {
        return new ResponseEntity<>("Hello", HttpStatus.ACCEPTED);
    }

}
