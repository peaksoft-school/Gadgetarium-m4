package us.peaksoft.gadgetarium.service;

import us.peaksoft.gadgetarium.dto.DiscountRequest;
import us.peaksoft.gadgetarium.dto.DiscountResponse;
import us.peaksoft.gadgetarium.dto.ProductResponse;
import us.peaksoft.gadgetarium.dto.SimpleResponse;

import java.util.List;

public interface DiscountService {

    List<DiscountResponse> getAllDiscounts();

    DiscountResponse save(DiscountRequest discountRequest);

    DiscountResponse update(Long id, DiscountRequest discountRequest);

    DiscountResponse getById(Long id);

    SimpleResponse delete(Long id);

    List<ProductResponse> getProductsByDiscountId(Long id, int page, int size);

}
