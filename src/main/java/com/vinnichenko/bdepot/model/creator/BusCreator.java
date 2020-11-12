package com.vinnichenko.bdepot.model.creator;

import com.vinnichenko.bdepot.model.entity.Bus;

import java.math.BigDecimal;
import java.util.Map;

import static com.vinnichenko.bdepot.model.ParameterKey.*;

public class BusCreator {

    private BusCreator() {
    }

    public static Bus createBus(Map<String, String> parameters) {
        Bus bus = new Bus();
        if (parameters.containsKey(BUS_ID)) {
            bus.setBusId(Integer.parseInt(parameters.get(BUS_ID)));
        }
        bus.setBrand(parameters.get(BRAND));
        bus.setModel(parameters.get(MODEL));
        bus.setRegistrationNumber(parameters.get(REGISTRATION_NUMBER));
        bus.setNumberOfSeats(Integer.parseInt(parameters.get(NUMBER_OF_SEATS)));
        bus.setRate(BigDecimal.valueOf(Double.parseDouble(parameters.get(RATE))));
        bus.setImageName(parameters.get(IMAGE_NAME));
        bus.setStatus(Bus.BusStatus.NOT_ASSIGNED);
        bus.setUserId(0);
        return bus;
    }
}
