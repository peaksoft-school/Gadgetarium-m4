package us.peaksoft.gadgetarium.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import us.peaksoft.gadgetarium.dto.OrderSumResponse;
import us.peaksoft.gadgetarium.entity.Order;
import us.peaksoft.gadgetarium.repository.OrderRepository;
import us.peaksoft.gadgetarium.service.OrderService;
import us.peaksoft.gadgetarium.entity.Product;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    @Override
    public OrderSumResponse sumOfOrders(Long id) {
        Order order = orderRepository.findById(id).get();
        return mapToResponseForSumOfOrders(order);
    }

    private OrderSumResponse mapToResponseForSumOfOrders(Order order){
        OrderSumResponse orderSumResponse = new OrderSumResponse();
        orderSumResponse.setQuantityOfProducts(orderRepository.quantityOfProductsByOrderId());
        orderSumResponse.setSum(order.getTotalSum());
        for(Product product : order.getProducts()) {
            double discounted = (double) product.getPrice() / 100;
            double discountedPrice = product.getPrice()*discounted;
            int endSum = (int) (order.getTotalSum() - discountedPrice);
                    orderSumResponse.setEndSum(endSum);
        }
        return orderSumResponse;
    }
}
