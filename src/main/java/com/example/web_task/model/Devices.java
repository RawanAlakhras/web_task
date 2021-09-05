package com.example.web_task.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Devices implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private String solutionName;
    private String solutionType;
    private String solutionId;
    private String devicesNumber;
    private String city;
    private String description;
    private String version;
    private Date timeCreated;

    @ManyToOne
    private ApplicationUser owner;

    public Devices() {
    }

    public Devices(String status, String solutionName, String solutionType, String devicesNumber, String city, String description, String version,  ApplicationUser owner) {
        this.status = status;
        this.solutionName = solutionName;
        this.solutionType = solutionType;
        this.devicesNumber = devicesNumber;
        this.city = city;
        this.description = description;
        this.version = version;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSolutionName() {
        return solutionName;
    }

    public void setSolutionName(String solutionName) {
        this.solutionName = solutionName;
    }

    public String getSolutionType() {
        return solutionType;
    }

    public void setSolutionType(String solutionType) {
        this.solutionType = solutionType;
    }

    public String getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(String solutionId) {
        this.solutionId = solutionId;
    }

    public String getDevicesNumber() {
        return devicesNumber;
    }

    public void setDevicesNumber(String devicesNumber) {
        this.devicesNumber = devicesNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public ApplicationUser getOwner() {
        return owner;
    }

    public void setOwner(ApplicationUser owner) {
        this.owner = owner;
    }
}
