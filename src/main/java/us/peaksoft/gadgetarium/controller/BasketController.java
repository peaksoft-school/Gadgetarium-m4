package us.peaksoft.gadgetarium.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import us.peaksoft.gadgetarium.dto.*;
import us.peaksoft.gadgetarium.service.BasketService;

import java.util.List;

@RestController
@RequestMapping("api/cart")
@Tag(name = "BasketController", description = "API endpoints for managing Cart")
@RequiredArgsConstructor

public class BasketController {

    private final BasketService basketService;

    @PostMapping("products/{id}")
    @Operation(description = "Only users can add a Product to Cart")
    public ProductResponse saveProduct(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) {
        return basketService.saveProductIntoBasket(id, productRequest);
    }

    @PutMapping("products/{id}")
    @Operation(description = "Users and Admin can delete a Product from Cart")
    public SimpleResponse deleteProduct(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) {
        return basketService.removeProductFromBasket(id, productRequest);
    }


    @PutMapping("all-products/{basketId}")
    @Operation(description = "Users and Admin can delete a List of Products from Cart")
    public SimpleResponse deleteAllProduct(@PathVariable("basketId") Long id, @RequestBody ProductRequest productRequest){
        return basketService.removeAllProductFromBasket(id,productRequest);
    }

    @GetMapping("products/{basketId}")
    @Operation(description = "Users and Admin can see a List of Cart's Products")
    public List<ProductResponse> productsOfCart(@PathVariable(name = "id", required = false) Long id, @RequestParam(value = "page", required = false)
    int page, @RequestParam(name = "size", required = false) int size) {
        return basketService.getProductsByBasketId(id, page, size);
    }

    @GetMapping("order-sum/{basketId}")
    @Operation(description = "Users can see product's total sum in one Basket, quantity of products in Basket's," +
            " the difference in the amounts and sum without discounts")
    public OrderSumResponse orderSum(@PathVariable("id") Long id){
        return basketService.sumOfOrders(id);
    }
}
