package us.peaksoft.gadgetarium.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import us.peaksoft.gadgetarium.dto.ProductRequest;
import us.peaksoft.gadgetarium.dto.ProductResponse;
import us.peaksoft.gadgetarium.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("api/gadgetarium/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductResponse>AllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping
    public ProductResponse save(@RequestBody ProductRequest productRequest){
       return productService.save(productRequest);
    }

    @GetMapping("{id}")
    public ProductResponse getById (@PathVariable("id") Long id){
        return productService.getById(id);
    }

    @PutMapping("{id}")
    public  ProductResponse update(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest){
        return productService.update(id,productRequest);
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id){
        productService.delete(id);
        return "The product with this id: " + id + " was deleted";
    }
}