package us.peaksoft.gadgetarium.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import us.peaksoft.gadgetarium.dto.BasketResponse;
import us.peaksoft.gadgetarium.dto.ProductRequest;
import us.peaksoft.gadgetarium.dto.ProductResponse;
import us.peaksoft.gadgetarium.dto.SimpleResponse;
import us.peaksoft.gadgetarium.entity.Basket;
import us.peaksoft.gadgetarium.entity.Product;
import us.peaksoft.gadgetarium.repository.BasketRepository;
import us.peaksoft.gadgetarium.repository.ProductRepository;
import us.peaksoft.gadgetarium.service.BasketService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;

    @Override
    public ProductResponse saveProductIntoBasket(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).get();
        if (productRequest.getBasketId() != null) {
            Basket basket = basketRepository.findById(productRequest.getBasketId()).get();
            product.setBasket(basket);
        }
        productRepository.save(product);
        return mapToResponseForSavingIntoBasket(product);
    }

    @Override
    public BasketResponse getById(Long id) {
        Basket basket = basketRepository.findById(id).get();
        return mapToResponse(basket);
    }

    @Override
    public SimpleResponse removeProductFromBasket(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).get();
        SimpleResponse simpleResponse = new SimpleResponse();
        if (productRequest.getBasketId() == null) {
            product.setBasket(null);
            simpleResponse.setMessage("The product was successfully deleted from cart");
            simpleResponse.setHttpStatus(HttpStatus.OK);
        }
        productRepository.save(product);
        return simpleResponse;
    }

    @Override
    public List<ProductResponse> getProductsByBasketId(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        List<Product> products = basketRepository.getProductsByBasketId(id, pageable);
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(mapToResponseForSavingIntoBasket(product));
        }
        return productResponses;
    }

    private BasketResponse mapToResponse(Basket basket) {
        BasketResponse basketResponse = new BasketResponse();
        basketResponse.setId(basket.getId());
        return basketResponse;
    }

    private ProductResponse mapToResponseForSavingIntoBasket(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setBrand(product.getBrand());
        productResponse.setColor(product.getColor());
        productResponse.setDateOfIssue(product.getDateOfIssue());
        productResponse.setOs(product.getOs());
        productResponse.setRam(product.getRam());
        productResponse.setRom(product.getRom());
        productResponse.setSim(product.getSim());
        productResponse.setQuantityOfSim(product.getQuantityOfSim());
        productResponse.setCpu(product.getCpu());
        productResponse.setWeight(product.getWeight());
        productResponse.setGuarantee(product.getGuarantee());
        productResponse.setImage(product.getImage());
        productResponse.setDisplayInch(product.getDisplayInch());
        productResponse.setAppointment(product.getAppointment());
        productResponse.setCapacityBattery(product.getCapacityBattery());
        productResponse.setDescription(product.getDescription());
        productResponse.setPDF(product.getPDF());
        productResponse.setQuantityOfProducts(productRepository.Quantity(product.getBrand(),
                product.getColor(), product.getRam(),
                product.getQuantityOfSim(), product.getPrice()));
        if (product.getDiscount().getId() != null) {
            double disPer = (double) product.getDiscount().getPercent() / 100;
            double disPrice = product.getPrice() * disPer;
            int discountedPrice = (int) (product.getPrice() - disPrice);
            productResponse.setCurrentPrice(discountedPrice);
            productResponse.setDisPercent(product.getDiscount().getPercent());
        }
        if (product.getBasket() != null) {
            productResponse.setInBasket(true);
        } else {
            productResponse.setInBasket(false);
        }
        return productResponse;
    }
}
