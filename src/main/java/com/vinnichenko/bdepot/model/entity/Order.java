package com.vinnichenko.bdepot.model.entity;

import java.io.Serializable;

/**
 * The type Order.
 * Business data object: order.
 */
public class Order implements Serializable {

    /**
     * The enum Order status.
     */
    public enum OrderStatus {
        /**
         * Submitted order status.
         */
        SUBMITTED,
        /**
         * Pending order status.
         */
        PENDING,
        /**
         * In process order status.
         */
        IN_PROCESS,
        /**
         * Completed order status.
         */
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

    /**
     * Instantiates a new Order.
     */
    public Order() {
    }

    /**
     * Instantiates a new Order.
     *
     * @param numberOfSeats the number of seats
     * @param startDate     the start date
     * @param endDate       the end date
     * @param startPoint    the start point
     * @param endPoint      the end point
     * @param distance      the distance
     * @param status        the status
     */
    public Order(int numberOfSeats, long startDate, long endDate, String startPoint, String endPoint, int distance, OrderStatus status) {
        this.numberOfSeats = numberOfSeats;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distance = distance;
        this.status = status;
    }

    /**
     * Instantiates a new Order.
     *
     * @param orderId       the order id
     * @param numberOfSeats the number of seats
     * @param startDate     the start date
     * @param endDate       the end date
     * @param startPoint    the start point
     * @param endPoint      the end point
     * @param distance      the distance
     * @param status        the status
     */
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
     * Gets start point.
     *
     * @return the start point
     */
    public String getStartPoint() {
        return startPoint;
    }

    /**
     * Sets start point.
     *
     * @param startPoint the start point
     */
    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    /**
     * Gets end point.
     *
     * @return the end point
     */
    public String getEndPoint() {
        return endPoint;
    }

    /**
     * Sets end point.
     *
     * @param endPoint the end point
     */
    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    /**
     * Gets distance.
     *
     * @return the distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Sets distance.
     *
     * @param distance the distance
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
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
