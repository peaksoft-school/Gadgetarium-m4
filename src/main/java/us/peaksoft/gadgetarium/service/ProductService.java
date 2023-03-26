package us.peaksoft.gadgetarium.service;

import us.peaksoft.gadgetarium.dto.ProductDeleteResponse;
import us.peaksoft.gadgetarium.dto.ProductRequest;
import us.peaksoft.gadgetarium.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProducts();
    ProductResponse save(ProductRequest productRequest);
    ProductResponse update(Long id, ProductRequest productRequest);
    ProductResponse getById(Long id);
    ProductDeleteResponse delete(Long id);
}
