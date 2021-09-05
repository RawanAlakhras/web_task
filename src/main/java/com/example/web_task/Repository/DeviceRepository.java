package com.example.web_task.Repository;

import com.example.web_task.model.Devices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Devices,Long> {
}
