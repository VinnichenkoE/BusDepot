package com.vinnichenko.bdepot.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Bus implements Serializable, Cloneable{

    public enum BusStatus implements Serializable {
        READY,
        REPAIR,
        NOT_ASSIGNED
    }

    private int busId;
    private String brand;
    private String model;
    private String registrationNumber;
    private int numberOfSeats;
    private BigDecimal rate;
    private String imageName;
    private BusStatus status;
    private long userId;

    public Bus() {
    }

    public Bus(String brand, String model, String registrationNumber, int numberOfSeats, BigDecimal rate, String imageName, BusStatus status, long userId) {
        this.brand = brand;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.numberOfSeats = numberOfSeats;
        this.rate = rate;
        this.imageName = imageName;
        this.status = status;
        this.userId = userId;
    }

    public Bus(int busId, String brand, String model, String registrationNumber, int numberOfSeats, BigDecimal rate, String imageName, BusStatus status, long userId) {
        this.busId = busId;
        this.brand = brand;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.numberOfSeats = numberOfSeats;
        this.rate = rate;
        this.imageName = imageName;
        this.status = status;
        this.userId = userId;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public BusStatus getStatus() {
        return status;
    }

    public void setStatus(BusStatus status) {
        this.status = status;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bus bus = (Bus) o;

        if (busId != bus.busId) return false;
        if (numberOfSeats != bus.numberOfSeats) return false;
        if (userId != bus.userId) return false;
        if (brand != null ? !brand.equals(bus.brand) : bus.brand != null) return false;
        if (model != null ? !model.equals(bus.model) : bus.model != null) return false;
        if (registrationNumber != null ? !registrationNumber.equals(bus.registrationNumber) : bus.registrationNumber != null)
            return false;
        if (rate != null ? !rate.equals(bus.rate) : bus.rate != null) return false;
        if (imageName != null ? !imageName.equals(bus.imageName) : bus.imageName != null) return false;
        return status == bus.status;
    }

    @Override
    public int hashCode() {
        int result = busId;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (registrationNumber != null ? registrationNumber.hashCode() : 0);
        result = 31 * result + numberOfSeats;
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        result = 31 * result + (imageName != null ? imageName.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bus{");
        sb.append("busId=").append(busId);
        sb.append(", brand='").append(brand).append('\'');
        sb.append(", model='").append(model).append('\'');
        sb.append(", registrationNumber='").append(registrationNumber).append('\'');
        sb.append(", numberOfSeats=").append(numberOfSeats);
        sb.append(", rate=").append(rate);
        sb.append(", imageName='").append(imageName).append('\'');
        sb.append(", status=").append(status);
        sb.append(", userId=").append(userId);
        sb.append('}');
        return sb.toString();
    }
}
