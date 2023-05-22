package us.peaksoft.gadgetarium.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import us.peaksoft.gadgetarium.dto.OrderRequest;
import us.peaksoft.gadgetarium.dto.OrderResponse;
import us.peaksoft.gadgetarium.dto.OrderResponseForVarietyOfDelivery;
import us.peaksoft.gadgetarium.entity.Address;
import us.peaksoft.gadgetarium.entity.Basket;
import us.peaksoft.gadgetarium.entity.Order;
import us.peaksoft.gadgetarium.entity.OrderReview;
import us.peaksoft.gadgetarium.entity.User;
import us.peaksoft.gadgetarium.enums.OrderStatus;
import us.peaksoft.gadgetarium.enums.Shipping;
import us.peaksoft.gadgetarium.repository.BasketRepository;
import us.peaksoft.gadgetarium.repository.OrderRepository;
import us.peaksoft.gadgetarium.repository.OrderReviewRepository;
import us.peaksoft.gadgetarium.service.OrderService;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BasketRepository basketRepository;
    private final OrderReviewRepository orderReviewEntityRepository;

    @Override
    public OrderResponseForVarietyOfDelivery saveMain(String orderReviewEntity, Long basketId, OrderRequest orderRequest) {
        Basket basket = basketRepository.findById(basketId).get();
        OrderReview orderReview = orderReviewEntityRepository.findByOrderReview(orderReviewEntity).get();
        Order order = mapToEntity(orderRequest);
        basket.setOrder(order);
        order.setBasket(basket);
        order.setOrderReview(orderReview);
        User user = basket.getUser();
        if (order.getShipping() == Shipping.COURIER_DELIVERY) {
            Address address = new Address();
            address.setCountryName(orderRequest.getCountryName());
            address.setCityName(orderRequest.getCityName());
            address.setStreetName(orderRequest.getStreetName());
            address.setStateName(orderRequest.getStateName());
            address.setPostalCode(orderRequest.getPostalCode());
            user.setAddress(address);
            order.setUser(user);
        } else if (order.getShipping() == Shipping.FROM_SHOP) {
            order.setUser(user);
        }
        orderRepository.save(order);
        basketRepository.save(basket);
        return mapToResponseVarietyOfDelivery(order);
    }

    @Override
    public OrderResponse savePayment(Long id, String orderReview, OrderRequest orderRequest) {
        Order order = orderRepository.findById(id).get();
        OrderReview orderReviewEntity = orderReviewEntityRepository.findByOrderReview(orderReview).get();
        order.setOrderReview(orderReviewEntity);
        order.setTypePayment(null);
        orderRepository.save(order);
        return mapToResponse(order);
    }

    @Override
    public OrderResponse saveOrderReview(Long id, String orderReview) {
        Order order = orderRepository.findById(id).get();
        OrderReview orderReviewEntity = orderReviewEntityRepository.findByOrderReview(orderReview).get();
        order.setOrderReview(orderReviewEntity);
        order.setTotalSum(order.getBasket().getEndSum());
        orderRepository.save(order);
        return mapToResponse(order);
    }

    private Order mapToEntity(OrderRequest orderRequest) {
        Order order = new Order();
        order.setShipping(orderRequest.getShipping());
        order.setTypePayment(orderRequest.getTypePayment());
        order.setOrderStatus(OrderStatus.CREATED);
        return order;
    }

    private OrderResponseForVarietyOfDelivery mapToResponseVarietyOfDelivery(Order order) {
        OrderResponseForVarietyOfDelivery orderResponseForVarietyOfDelivery = new OrderResponseForVarietyOfDelivery();
        orderResponseForVarietyOfDelivery.setId(order.getId());
        orderResponseForVarietyOfDelivery.setName(order.getUser().getFirstName());
        orderResponseForVarietyOfDelivery.setLastName(order.getUser().getLastName());
        orderResponseForVarietyOfDelivery.setEmail(order.getUser().getEmail());
        orderResponseForVarietyOfDelivery.setPhoneNumber(order.getUser().getPhoneNumber());
        if (order.getShipping() == Shipping.COURIER_DELIVERY) {
            orderResponseForVarietyOfDelivery.setAddress(order.getUser().getAddress().getCountryName() + ", " + order.getUser().getAddress().getCityName() + ", " +
                    order.getUser().getAddress().getStateName() + ", " + order.getUser().getAddress().getStreetName() + ", " + order.getUser().getAddress().getPostalCode());
            orderResponseForVarietyOfDelivery.setShipping(order.getShipping().getShipping());
        } else if (order.getShipping() == Shipping.FROM_SHOP) {
            orderResponseForVarietyOfDelivery.setShipping(order.getShipping().getShipping());
        }
        return orderResponseForVarietyOfDelivery;
    }

    private OrderResponse mapToResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        if (order.getShipping() == Shipping.COURIER_DELIVERY) {
            orderResponse.setAddress(order.getUser().getAddress().getCountryName() + ", " + order.getUser().getAddress().getCityName() + ", " +
                    order.getUser().getAddress().getStateName() + ", " + order.getUser().getAddress().getStreetName() + ", " + order.getUser().getAddress().getPostalCode());
            orderResponse.setShipping(order.getShipping().getShipping());
        } else if (order.getShipping() == Shipping.FROM_SHOP) {
            orderResponse.setShipping(order.getShipping().getShipping());
        }
        orderResponse.setEndSum(order.getTotalSum());
        return orderResponse;
    }

}