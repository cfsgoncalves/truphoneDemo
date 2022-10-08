package com.truphone.demo.unit;

import com.truphone.demo.controller.DeviceController;
import com.truphone.demo.model.Device;
import com.truphone.demo.repository.DeviceRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class DemoControllerTests {
    
        @Mock
        DeviceRepository deviceRepository;
        
        @InjectMocks
        DeviceController deviceController;

	@Test
	void getAllDevicesHappyPathTest() {            
            List<Device> devices = new ArrayList<>();
            devices.add(new Device("foobiz","foobar",new Date().toInstant()));
            
            when(deviceRepository.findAll()).thenReturn(devices);

            ResponseEntity<List<Device>> re = deviceController.getAllDevices();
            
            verify(deviceRepository, times(1)).findAll();            
            assertEquals(re, new ResponseEntity<>(devices, HttpStatus.OK));
        }
        
        @Test
	void getAllDevicesDevicesDBIsEmpty() {  
            List<Device> devices = new ArrayList<>();
            
            when(deviceRepository.findAll()).thenReturn(devices);

            ResponseEntity<List<Device>> re = deviceController.getAllDevices();
            
            verify(deviceRepository, times(1)).findAll();            
            assertEquals(re, new ResponseEntity<>(HttpStatus.NO_CONTENT)); 
        }

        @Test
	void getAllDevicesDevicesErrorAcessingDB() { 
            List<Device> devices = new ArrayList<>();
            
            when(deviceRepository.findAll()).thenThrow(NullPointerException.class);

            ResponseEntity<List<Device>> re = deviceController.getAllDevices();
            
            verify(deviceRepository, times(1)).findAll();            
            assertEquals(re, new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR)); 

        } 
}
