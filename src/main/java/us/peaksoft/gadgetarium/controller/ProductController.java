package us.peaksoft.gadgetarium.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.peaksoft.gadgetarium.dto.*;
import us.peaksoft.gadgetarium.enums.Brand;
import us.peaksoft.gadgetarium.enums.Color;
import us.peaksoft.gadgetarium.service.ProductService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/products")
@Tag(name = "ProductController", description = "API endpoints for managing products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @Operation(description = "All users and admin can see a list of products ")
    public List<ProductResponse> AllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("filter-products")
    public List<ProductResponse> filterProducts(@RequestParam("brand") String brand, @RequestParam("color") String color,
                                                @RequestParam("ram") String ram, @RequestParam("rom") String rom,
                                                @RequestParam("fromPrice") int fromPrice, @RequestParam("toPrice") int toPrice,
                                                @RequestParam(value = "page") int page, @RequestParam(name = "size") int size) {
        return productService.filterProducts(brand, color, ram, rom, fromPrice, toPrice, page, size);
    }

    @PostMapping("save-main")
    @Operation(description = "Only admin can add a product")
    public ProductResponse save(@RequestBody ProductRequest productRequest) {
        return productService.save(productRequest);
    }

    @PostMapping("contacts")
public void contact(@RequestBody ContactRequest contact){
        productService.sendEmail(contact);
}

    @GetMapping("download/{id}")
    @Operation(description = "The method works automatically, all users can use this method")
    public ResponseEntity<InputStreamResource> PDFfile(Long id) throws IOException {
        File file=productService.file(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
    @Operation(description = "Only admin can add a price to product")
    @PostMapping("save-price/{id}")
    public ProductResponse savePrice(@PathVariable("id") Long id,
                                     @RequestBody ProductRequest priceRequest) {
        return productService.savePriceAndQuantity(id, priceRequest);
    }

    @PostMapping("save-description/{id}")
    @Operation(description = "Only admin can add a description to product")
    public ProductResponse saveDescription(@PathVariable("id") Long id, @RequestBody ProductRequest descriptionRequest) {
        return productService.saveDescription(id, descriptionRequest);
    }

    @GetMapping("{id}")
    @Operation(description = "All users and admin can get product by id")
    public ProductResponse getById(@PathVariable("id") Long id) {
        return productService.getById(id);
    }

    @PutMapping("{id}")
    @Operation(description = "Only admin can update a product")
    public ProductResponse update(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) {
        return productService.update(id, productRequest);
    }

    @DeleteMapping("{id}")
    @Operation(description = "Only admin can delete a product")
    public SimpleResponse delete(@PathVariable("id") Long id) {
        return productService.delete(id);
    }

    @GetMapping("product-details")
    public List<ProductDetailsResponse> productDetails() {
        return productService.productDetails();
    }

    @GetMapping("search-product")
    public List<ProductResponse> search(@RequestParam(name = "text", required = false) String text, @RequestParam(value = "page"
            , required = false) int page, @RequestParam(name = "size", required = false) int size) {
        return productService.searchAndPagination(text, page, size);
    }
}