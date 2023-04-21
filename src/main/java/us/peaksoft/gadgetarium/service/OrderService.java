package us.peaksoft.gadgetarium.service;

import us.peaksoft.gadgetarium.dto.OrderSumResponse;

public interface OrderService {
    OrderSumResponse sumOfOrders(Long id);
}
