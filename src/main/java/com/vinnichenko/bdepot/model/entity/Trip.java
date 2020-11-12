package com.vinnichenko.bdepot.model.entity;

import java.math.BigDecimal;

public class Trip {
    private long tripId;
    private long startDate;
    private long endDate;
    private BigDecimal cost;
    private long orderId;
    private long userId;

    public Trip() {
    }

    public Trip(long startDate, long endDate, BigDecimal cost, long orderId, long userId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
        this.orderId = orderId;
        this.userId = userId;
    }

    public Trip(long tripId, long startDate, long endDate, BigDecimal cost, long orderId, long userId) {
        this.tripId = tripId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
        this.orderId = orderId;
        this.userId = userId;
    }

    public long getTripId() {
        return tripId;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
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
