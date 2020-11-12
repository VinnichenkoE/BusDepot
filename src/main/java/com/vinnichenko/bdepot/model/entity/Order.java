package com.vinnichenko.bdepot.model.entity;

import java.io.Serializable;

public class Order implements Serializable {

    public enum OrderStatus {
        SUBMITTED,
        PENDING,
        IN_PROCESS,
        COMPLETED
    }

    private long orderId;
    private int numberOfSeats;
    private long startDate;
    private long endDate;
    private String startPoint;
    private String endPoint;
    private int distance;
    private OrderStatus status;

    public Order() {
    }

    public Order(int numberOfSeats, long startDate, long endDate, String startPoint, String endPoint, int distance, OrderStatus status) {
        this.numberOfSeats = numberOfSeats;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distance = distance;
        this.status = status;
    }

    public Order(long orderId, int numberOfSeats, long startDate, long endDate, String startPoint, String endPoint, int distance, OrderStatus status) {
        this.orderId = orderId;
        this.numberOfSeats = numberOfSeats;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distance = distance;
        this.status = status;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
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

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (numberOfSeats != order.numberOfSeats) return false;
        if (startDate != order.startDate) return false;
        if (endDate != order.endDate) return false;
        if (distance != order.distance) return false;
        if (startPoint != null ? !startPoint.equals(order.startPoint) : order.startPoint != null) return false;
        if (endPoint != null ? !endPoint.equals(order.endPoint) : order.endPoint != null) return false;
        return status == order.status;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + numberOfSeats;
        result = 31 * result + (int) (startDate ^ (startDate >>> 32));
        result = 31 * result + (int) (endDate ^ (endDate >>> 32));
        result = 31 * result + (startPoint != null ? startPoint.hashCode() : 0);
        result = 31 * result + (endPoint != null ? endPoint.hashCode() : 0);
        result = 31 * result + distance;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", numberOfSeats=").append(numberOfSeats);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", startPoint='").append(startPoint).append('\'');
        sb.append(", endPoint='").append(endPoint).append('\'');
        sb.append(", distance=").append(distance);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
