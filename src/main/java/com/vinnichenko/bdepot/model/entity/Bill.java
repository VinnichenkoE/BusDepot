package com.vinnichenko.bdepot.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The type Bill.
 * Business data object: bill.
 */
public class Bill implements Serializable {

    private long billId;
    private BigDecimal cost;
    private byte isPayed;
    private long orderId;
    private long userId;

    /**
     * Instantiates a new Bill.
     */
    public Bill() {
    }

    /**
     * Instantiates a new Bill.
     *
     * @param cost    the cost
     * @param isPayed the is payed
     * @param orderId the order id
     * @param userId  the user id
     */
    public Bill(BigDecimal cost, byte isPayed, long orderId, long userId) {
        this.cost = cost;
        this.isPayed = isPayed;
        this.orderId = orderId;
        this.userId = userId;
    }

    /**
     * Instantiates a new Bill.
     *
     * @param billId  the bill id
     * @param cost    the cost
     * @param isPayed the is payed
     * @param orderId the order id
     * @param userId  the user id
     */
    public Bill(long billId, BigDecimal cost, byte isPayed, long orderId, long userId) {
        this.billId = billId;
        this.cost = cost;
        this.isPayed = isPayed;
        this.orderId = orderId;
        this.userId = userId;
    }

    /**
     * Gets bill id.
     *
     * @return the bill id
     */
    public long getBillId() {
        return billId;
    }

    /**
     * Sets bill id.
     *
     * @param billId the bill id
     */
    public void setBillId(long billId) {
        this.billId = billId;
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
     * Gets is payed.
     *
     * @return the is payed
     */
    public byte getIsPayed() {
        return isPayed;
    }

    /**
     * Sets is payed.
     *
     * @param isPayed the is payed
     */
    public void setIsPayed(byte isPayed) {
        this.isPayed = isPayed;
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

        Bill bill = (Bill) o;

        if (billId != bill.billId) return false;
        if (isPayed != bill.isPayed) return false;
        if (orderId != bill.orderId) return false;
        if (userId != bill.userId) return false;
        return cost != null ? cost.equals(bill.cost) : bill.cost == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (billId ^ (billId >>> 32));
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (int) isPayed;
        result = 31 * result + (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bill{");
        sb.append("billId=").append(billId);
        sb.append(", cost=").append(cost);
        sb.append(", isPayed=").append(isPayed);
        sb.append(", orderId=").append(orderId);
        sb.append(", userId=").append(userId);
        sb.append('}');
        return sb.toString();
    }
}
