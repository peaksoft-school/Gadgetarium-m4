package us.peaksoft.gadgetarium.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import us.peaksoft.gadgetarium.dto.*;
import us.peaksoft.gadgetarium.enums.Brand;
import us.peaksoft.gadgetarium.enums.Color;
import us.peaksoft.gadgetarium.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> AllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("filter-products")
    public List<ProductResponse> filterProducts(@RequestParam("brand") Brand brand, @RequestParam("color") Color color,
                                                @RequestParam("ram") String ram, @RequestParam("rom") String rom){
        return productService.filterProducts(brand,color,ram,rom);
    }

    @PostMapping("save-main")
    public ProductResponse save(@RequestBody ProductRequest productRequest) {
        return productService.save(productRequest);
    }

    @PostMapping("save-price/{id}")
    public ProductResponse savePrice(@PathVariable("id") Long id,
                                     @RequestBody ProductRequest priceRequest) {
        return productService.savePriceAndQuantity(id, priceRequest);
    }

    @PostMapping("save-description/{id}")
    public ProductResponse saveDescription(@PathVariable("id") Long id, @RequestBody ProductRequest descriptionRequest) {
        return productService.saveDescription(id, descriptionRequest);
    }

    @GetMapping("{id}")
    public ProductResponse getById(@PathVariable("id") Long id) {
        return productService.getById(id);
    }

    @PutMapping("{id}")
    public ProductResponse update(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) {
        return productService.update(id, productRequest);
    }

    @DeleteMapping("{id}")
    public SimpleResponse delete(@PathVariable("id") Long id) {
        return productService.delete(id);
    }
}
