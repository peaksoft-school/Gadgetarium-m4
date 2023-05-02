package us.peaksoft.gadgetarium.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import us.peaksoft.gadgetarium.dto.OrderRequest;
import us.peaksoft.gadgetarium.dto.OrderResponse;
import us.peaksoft.gadgetarium.dto.OrderResponseForVarietyOfDelivery;
import us.peaksoft.gadgetarium.service.OrderService;

@RestController
@RequestMapping("api/orders")
@Tag(name = "OrderController", description = "API endpoints for managing orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("save/{review-status}/{basketId}")
    @Operation(description = "All authenticated List of Users can create an Order by Basket's id")
    public OrderResponseForVarietyOfDelivery saveMain(@PathVariable("review-status") String orderReview,
                                                      @PathVariable("basketId") Long basketId, @RequestBody OrderRequest orderRequest) {
        return orderService.saveMain(orderReview, basketId, orderRequest);
    }

    @PostMapping("save-finish/{id}/{review-status}")
    @Operation(description = "All authenticated List of Users can save a total sum of Basket's Products" +
            "into already created Order by its id")
    public OrderResponse saveReview(@PathVariable("id") Long id, @PathVariable("review-status") String orderReview) {
        return orderService.saveOrderReview(id, orderReview);
    }
}
