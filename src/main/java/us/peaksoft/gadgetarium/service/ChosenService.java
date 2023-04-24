package us.peaksoft.gadgetarium.service;

import us.peaksoft.gadgetarium.dto.ChosenResponse;
import us.peaksoft.gadgetarium.dto.ProductRequest;
import us.peaksoft.gadgetarium.dto.ProductResponse;
import us.peaksoft.gadgetarium.dto.SimpleResponse;

import java.util.List;

public interface ChosenService {

    ProductResponse saveProductInChosen(Long id, ProductRequest productRequest);

    ChosenResponse getById(Long id);

    SimpleResponse deleteProductFromChosen(Long id,ProductRequest productRequest);

    List<ProductResponse> getProductsByChosenId(Long id, int page, int size);

    SimpleResponse deleteAllProducts(Long id,ProductRequest productRequest);

}
