package us.peaksoft.gadgetarium.service;

import org.springframework.stereotype.Service;
import us.peaksoft.gadgetarium.dto.ProductRequest;
import us.peaksoft.gadgetarium.dto.ProductResponse;
import us.peaksoft.gadgetarium.entity.Category;

import java.util.List;

public interface ProductService {
   List<ProductResponse> getAllProducts();
   ProductResponse save(ProductRequest product);
   ProductResponse update(Long id, ProductRequest product);
   ProductResponse getById(Long id);
   void delete(Long id);

}
