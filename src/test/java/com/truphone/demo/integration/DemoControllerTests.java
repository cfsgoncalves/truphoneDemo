package com.truphone.demo.integration;

import com.truphone.demo.controller.DeviceController;
import com.truphone.demo.model.Device;
import com.truphone.demo.repository.DeviceRepository;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class DemoControllerTests {
    
        @Autowired
        DeviceRepository deviceRepository;
    
        @Autowired
        DeviceController deviceController;

        @BeforeEach 
        void init() {
            deviceRepository.deleteAll();
        }
        
	@Test
	void getAllDevicesIntegration() {  
            Device newDevice = new Device("foo", "foobiz", new Date().toInstant());
            deviceRepository.save(newDevice);
            ResponseEntity<List<Device>> re = deviceController.getAllDevices();

            assertEquals(re.getStatusCode(),HttpStatus.OK);
            // Better option would be to override the equals method in Device
            assertEquals(re.getBody().get(0).getId(),newDevice.getId());
        }
}
