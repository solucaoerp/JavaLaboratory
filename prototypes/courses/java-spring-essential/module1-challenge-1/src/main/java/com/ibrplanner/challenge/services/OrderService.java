package com.ibrplanner.challenge.services;

import com.ibrplanner.challenge.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private ShippingService shippingService;

    public double total(Order order) {
        double total = order.getBasic() - (order.getBasic() * order.getDiscount() / 100);
        double shipment = shippingService.shipment(order);

        total += shipment;

        return total;
    }
}
