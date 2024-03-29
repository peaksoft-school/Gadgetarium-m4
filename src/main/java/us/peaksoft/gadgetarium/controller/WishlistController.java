package us.peaksoft.gadgetarium.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;;
import org.springframework.web.bind.annotation.*;
import us.peaksoft.gadgetarium.dto.WishlistResponse;
import us.peaksoft.gadgetarium.dto.ProductRequest;
import us.peaksoft.gadgetarium.dto.ProductResponse;
import us.peaksoft.gadgetarium.dto.SimpleResponse;
import us.peaksoft.gadgetarium.service.WishlistService;

import java.util.List;

@RestController
@RequestMapping("api/wishlist")
@Tag(name = "WishlistController", description = "API endpoints for managing Wishlist")
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishlistService;

    @GetMapping("/{id}")
    @Operation(description = "Only users can  get Wishlist by id")
    public WishlistResponse getById(@PathVariable("id") Long id) {
        return wishlistService.getById(id);
    }

    @PostMapping("products/{id}")
    @Operation(description = "Users and Admin can add a Product from Wishlist")
    public ProductResponse saveProduct(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) {
        return wishlistService.saveProductInChosen(id, productRequest);
    }

    @PutMapping("products/{id}")
    @Operation(description = "Users and Admin can delete a Product from Wishlist")
    public SimpleResponse deleteProduct(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) {
        return wishlistService.deleteProductFromChosen(id, productRequest);
    }

    @GetMapping("all-products/{id}")
    @Operation(description = "Users and Admin can see a List of Wishlist's Products")
    public List<ProductResponse> getAllProductFromChosen(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) int page,
                                                         @RequestParam(name = "size", required = false) int size) {
        return wishlistService.getProductsByChosenId(id, page, size);
    }

    @PutMapping("products/{wishlistId}")
    @Operation(description = "Users and Admin can delete a List of Products from Wishlist")
    public SimpleResponse deleteAllProducts(@PathVariable("wishlistId") Long wishlistId, @RequestBody ProductRequest productRequest) {
        return wishlistService.deleteAllProducts(wishlistId, productRequest);
    }

}
