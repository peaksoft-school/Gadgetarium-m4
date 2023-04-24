package us.peaksoft.gadgetarium.controller;

import lombok.RequiredArgsConstructor;;
import org.springframework.web.bind.annotation.*;
import us.peaksoft.gadgetarium.dto.ChosenResponse;
import us.peaksoft.gadgetarium.dto.ProductRequest;
import us.peaksoft.gadgetarium.dto.ProductResponse;
import us.peaksoft.gadgetarium.dto.SimpleResponse;
import us.peaksoft.gadgetarium.service.ChosenService;

import java.util.List;

@RestController
@RequestMapping("api/wishlist")
@RequiredArgsConstructor
public class ChosenController {
    private final ChosenService chosenService;

    @GetMapping("/{id}")
    public ChosenResponse getById(@PathVariable("id") Long id) {
        return chosenService.getById(id);
    }

    @PostMapping("save-product-to-chosen/{id}")
    public ProductResponse saveProduct(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) {
        return chosenService.saveProductInChosen(id, productRequest);
    }

    @PutMapping("delete-product-from-chosen/{id}")
    public SimpleResponse deleteProduct(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) {
        return chosenService.deleteProductFromChosen(id, productRequest);
    }

    @GetMapping("products-of-chosen/{id}")
    public List<ProductResponse> getAllProductFromChosen(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) int page,
                                                         @RequestParam(name = "size", required = false) int size) {
        return chosenService.getProductsByChosenId(id, page, size);
    }

    @PutMapping("delete-all-products-form-chosen/{id}")
    public SimpleResponse deleteAllProducts(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) {
        return chosenService.deleteAllProducts(id, productRequest);
    }

}
