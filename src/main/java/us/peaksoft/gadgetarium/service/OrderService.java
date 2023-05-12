package us.peaksoft.gadgetarium.service;

import us.peaksoft.gadgetarium.dto.OrderRequest;
import us.peaksoft.gadgetarium.dto.OrderResponse;
import us.peaksoft.gadgetarium.dto.OrderResponseForVarietyOfDelivery;

public interface OrderService {

    OrderResponseForVarietyOfDelivery saveMain(String orderReview, Long basketId, OrderRequest orderRequest);

    OrderResponse savePayment(Long id, String orderReview, OrderRequest orderRequest);

    OrderResponse saveOrderReview(Long id, String orderReview);
}
