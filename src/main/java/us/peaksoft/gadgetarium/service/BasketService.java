package us.peaksoft.gadgetarium.service;
import us.peaksoft.gadgetarium.dto.*;

import java.util.List;

public interface BasketService {

    ProductResponse saveProductIntoBasket(Long id, ProductRequest productRequest);

    BasketResponse getById(Long id);

    SimpleResponse removeProductFromBasket(Long id, ProductRequest productRequest);

    List<ProductResponse> getProductsByBasketId(Long id, int page, int size);

    SimpleResponse removeAllProductFromBasket(Long id, ProductRequest productRequest);

    OrderSumResponse sumOfOrders(Long id);
}
