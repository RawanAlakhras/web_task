package com.example.web_task.controller;

import com.example.web_task.Servic.DeviceServic;
import com.example.web_task.model.Devices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DevicesController {
    private final DeviceServic deviceServic;


    public DevicesController(DeviceServic deviceServic) {
        this.deviceServic = deviceServic;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Devices>> getAllDevices(){
        List <Devices>devices=deviceServic.findAllDevices();
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Devices> getDeviceById(@PathVariable Long id){
        Devices device=deviceServic.findDeviceById(id);
        return new ResponseEntity<>(device, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Devices> addDevice(@RequestBody Devices device){
        Devices newDevice=deviceServic.addDevice(device);
        return new ResponseEntity<>(newDevice, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Devices> updateDevice(@RequestBody Devices device){
        Devices updateDevice=deviceServic.updateDevice(device);
        return new ResponseEntity<>( updateDevice, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDevice(@PathVariable Long id){
        deviceServic.deleteDevice(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
