package us.peaksoft.gadgetarium.service;

import us.peaksoft.gadgetarium.dto.ProductDetailsResponse;
import us.peaksoft.gadgetarium.dto.SimpleResponse;
import us.peaksoft.gadgetarium.dto.ProductRequest;
import us.peaksoft.gadgetarium.dto.ProductResponse;
import us.peaksoft.gadgetarium.enums.Brand;
import us.peaksoft.gadgetarium.enums.Color;


import java.util.List;

public interface ProductService {

    List<ProductResponse> getAllProducts();

    List<ProductResponse> filterProducts(String brand, String color, String ram, String rom, int fromPrice, int toPrice, int page, int size);

    ProductResponse save(ProductRequest productRequest);

    ProductResponse savePriceAndQuantity(Long id, ProductRequest productRequest);

    ProductResponse saveDescription(Long id, ProductRequest productRequest);

    ProductResponse update(Long id, ProductRequest productRequest);

    ProductResponse getById(Long id);

    SimpleResponse delete(Long id);

    List<ProductDetailsResponse> productDetails();

    List<ProductResponse> searchAndPagination(String text, int page, int size);
}
