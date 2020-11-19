package com.vinnichenko.bdepot.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The type Trip.
 * Business data object: trip.
 */
public class Trip implements Serializable {
    private long tripId;
    private long startDate;
    private long endDate;
    private BigDecimal cost;
    private long orderId;
    private long userId;

    /**
     * Instantiates a new Trip.
     */
    public Trip() {
    }

    /**
     * Instantiates a new Trip.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @param cost      the cost
     * @param orderId   the order id
     * @param userId    the user id
     */
    public Trip(long startDate, long endDate, BigDecimal cost, long orderId, long userId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
        this.orderId = orderId;
        this.userId = userId;
    }

    /**
     * Instantiates a new Trip.
     *
     * @param tripId    the trip id
     * @param startDate the start date
     * @param endDate   the end date
     * @param cost      the cost
     * @param orderId   the order id
     * @param userId    the user id
     */
    public Trip(long tripId, long startDate, long endDate, BigDecimal cost, long orderId, long userId) {
        this.tripId = tripId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
        this.orderId = orderId;
        this.userId = userId;
    }

    /**
     * Gets trip id.
     *
     * @return the trip id
     */
    public long getTripId() {
        return tripId;
    }

    /**
     * Sets trip id.
     *
     * @param tripId the trip id
     */
    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public long getStartDate() {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public long getEndDate() {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets cost.
     *
     * @return the cost
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * Sets cost.
     *
     * @param cost the cost
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * Gets order id.
     *
     * @return the order id
     */
    public long getOrderId() {
        return orderId;
    }

    /**
     * Sets order id.
     *
     * @param orderId the order id
     */
    public void setOrderId(long orderId) {
        this.orderId = orderId;
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

        Trip trip = (Trip) o;

        if (tripId != trip.tripId) return false;
        if (startDate != trip.startDate) return false;
        if (endDate != trip.endDate) return false;
        if (orderId != trip.orderId) return false;
        if (userId != trip.userId) return false;
        return cost != null ? cost.equals(trip.cost) : trip.cost == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (tripId ^ (tripId >>> 32));
        result = 31 * result + (int) (startDate ^ (startDate >>> 32));
        result = 31 * result + (int) (endDate ^ (endDate >>> 32));
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Trip{");
        sb.append("tripId=").append(tripId);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", cost=").append(cost);
        sb.append(", orderId=").append(orderId);
        sb.append(", userId=").append(userId);
        sb.append('}');
        return sb.toString();
    }
}
