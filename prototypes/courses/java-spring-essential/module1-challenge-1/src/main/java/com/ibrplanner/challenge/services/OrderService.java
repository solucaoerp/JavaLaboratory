package com.ibrplanner.challenge.services;

import com.ibrplanner.challenge.domain.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public double total(Order order) {
        return order.getBasic() - (order.getBasic() * order.getDiscount());
    }
}
