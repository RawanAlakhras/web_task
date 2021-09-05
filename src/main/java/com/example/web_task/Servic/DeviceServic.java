package com.example.web_task.Servic;

import com.example.web_task.Repository.DeviceRepository;
import com.example.web_task.model.Devices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DeviceServic {

    private final DeviceRepository deviceRepository;
    @Autowired
    public DeviceServic(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }
    public Devices addDevice(Devices device){
        device.setSolutionId(UUID.randomUUID().toString());
        Date date = new Date();
        device.setTimeCreated(date);
        return deviceRepository.save(device);
    }
    public List <Devices> findAllDevices(){
        return deviceRepository.findAll();
    }
    public Devices updateDevice(Devices device){
        return deviceRepository.save(device);
    }
    public Devices findDeviceById(Long id){
        return deviceRepository.findById(id).get();
    }
    public void deleteDevice(Long id){
        deviceRepository.deleteById(id);
    }
}
