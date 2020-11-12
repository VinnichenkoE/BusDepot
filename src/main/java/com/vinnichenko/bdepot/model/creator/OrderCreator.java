package com.vinnichenko.bdepot.model.creator;

import com.vinnichenko.bdepot.model.entity.Order;
import com.vinnichenko.bdepot.util.DateConverter;

import java.util.Map;

import static com.vinnichenko.bdepot.model.ParameterKey.*;

public class OrderCreator {

    private OrderCreator() {
    }

    public static Order createOrder(Map<String, String> parameters) {
        Order order = new Order();
        if (parameters.containsKey(ORDER_ID)) {
            order.setOrderId(Long.parseLong(parameters.get(ORDER_ID)));
        }
        order.setNumberOfSeats(Integer.parseInt(parameters.get(NUMBER_OF_SEATS)));
        order.setStartDate(DateConverter.toLong(parameters.get(START_DATE)));
        order.setEndDate(DateConverter.toLong(parameters.get(END_DATE)));
        order.setStartPoint(parameters.get(START_POINT));
        order.setEndPoint(parameters.get(END_POINT));
        order.setDistance(Integer.parseInt(parameters.get(DISTANCE)));
        order.setStatus(Order.OrderStatus.SUBMITTED);
        return order;
    }
}

