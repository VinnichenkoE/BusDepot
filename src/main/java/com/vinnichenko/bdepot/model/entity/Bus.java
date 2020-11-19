package com.vinnichenko.bdepot.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The type Bus.
 * Business data object: bus.
 */
public class Bus implements Serializable {

    /**
     * The enum Bus status.
     * Describes the statuses of buses.
     */
    public enum BusStatus implements Serializable {
        /**
         * Ready bus status.
         */
        READY,
        /**
         * Repair bus status.
         */
        REPAIR,
        /**
         * Not assigned bus status.
         */
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

    /**
     * Instantiates a new Bus.
     */
    public Bus() {
    }

    /**
     * Instantiates a new Bus.
     *
     * @param brand              the brand
     * @param model              the model
     * @param registrationNumber the registration number
     * @param numberOfSeats      the number of seats
     * @param rate               the rate
     * @param imageName          the image name
     * @param status             the status
     * @param userId             the user id
     */
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

    /**
     * Instantiates a new Bus.
     *
     * @param busId              the bus id
     * @param brand              the brand
     * @param model              the model
     * @param registrationNumber the registration number
     * @param numberOfSeats      the number of seats
     * @param rate               the rate
     * @param imageName          the image name
     * @param status             the status
     * @param userId             the user id
     */
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

    /**
     * Gets bus id.
     *
     * @return the bus id
     */
    public int getBusId() {
        return busId;
    }

    /**
     * Sets bus id.
     *
     * @param busId the bus id
     */
    public void setBusId(int busId) {
        this.busId = busId;
    }

    /**
     * Gets brand.
     *
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets brand.
     *
     * @param brand the brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Gets model.
     *
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets model.
     *
     * @param model the model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets registration number.
     *
     * @return the registration number
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Sets registration number.
     *
     * @param registrationNumber the registration number
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * Gets number of seats.
     *
     * @return the number of seats
     */
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    /**
     * Sets number of seats.
     *
     * @param numberOfSeats the number of seats
     */
    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    /**
     * Gets rate.
     *
     * @return the rate
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * Sets rate.
     *
     * @param rate the rate
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * Gets image name.
     *
     * @return the image name
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * Sets image name.
     *
     * @param imageName the image name
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public BusStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(BusStatus status) {
        this.status = status;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
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
