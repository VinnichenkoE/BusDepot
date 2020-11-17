package com.vinnichenko.bdepot.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Bill implements Serializable, Cloneable {

    private long billId;
    private BigDecimal cost;
    private byte isPayed;
    private long orderId;
    private long userId;

    public Bill() {
    }

    public Bill(BigDecimal cost, byte isPayed, long orderId, long userId) {
        this.cost = cost;
        this.isPayed = isPayed;
        this.orderId = orderId;
        this.userId = userId;
    }

    public Bill(long billId, BigDecimal cost, byte isPayed, long orderId, long userId) {
        this.billId = billId;
        this.cost = cost;
        this.isPayed = isPayed;
        this.orderId = orderId;
        this.userId = userId;
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public byte getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(byte isPayed) {
        this.isPayed = isPayed;
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
