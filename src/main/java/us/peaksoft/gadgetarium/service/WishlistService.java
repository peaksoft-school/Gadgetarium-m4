package us.peaksoft.gadgetarium.service;

import us.peaksoft.gadgetarium.dto.WishlistResponse;
import us.peaksoft.gadgetarium.dto.ProductRequest;
import us.peaksoft.gadgetarium.dto.ProductResponse;
import us.peaksoft.gadgetarium.dto.SimpleResponse;

import java.util.List;

public interface WishlistService {

    ProductResponse saveProductInChosen(Long id, ProductRequest productRequest);

    WishlistResponse getById(Long id);

    SimpleResponse deleteProductFromChosen(Long id,ProductRequest productRequest);

    List<ProductResponse> getProductsByChosenId(Long id, int page, int size);

    SimpleResponse deleteAllProducts(Long id,ProductRequest productRequest);

}
