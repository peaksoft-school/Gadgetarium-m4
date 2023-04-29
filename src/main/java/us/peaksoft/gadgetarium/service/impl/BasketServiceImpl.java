package us.peaksoft.gadgetarium.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import us.peaksoft.gadgetarium.dto.BasketResponse;
import us.peaksoft.gadgetarium.dto.OrderSumResponse;
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
        int totalSum = 0;
        int currPrice = 0;
        int disPrice = 0;
        if (productRequest.getBasketId() != null) {
            Basket basket = basketRepository.findById(productRequest.getBasketId()).get();
            List<Product> products = basket.getProducts();
            product.setBasket(basket);
            productRepository.save(product);
            for (Product product1 : products) {
                totalSum += product1.getPrice();
                basket.setSum(totalSum);
                currPrice += product1.getCurrentPrice();
                disPrice = totalSum - currPrice;
                basket.setDisPercentSum(disPrice);
                basket.setEndSum(currPrice);
            }
            basket.setQuantityOfProducts(products.size());
            basketRepository.save(basket);
        }
        return mapToResponseForSavingIntoBasket(product);
    }

    @Override
    public BasketResponse getById(Long id) {
        Basket basket = basketRepository.findById(id).get();
        return mapToResponse(basket);
    }

    @Override
    public SimpleResponse removeProductFromBasket(Long productId, Long basketId, ProductRequest productRequest) {
        Product product = productRepository.findById(productId).get();
        Basket basket = basketRepository.findById(basketId).get();
        List<Product> products = basket.getProducts();
        SimpleResponse simpleResponse = new SimpleResponse();
        int minusSum = 0;
        int minusEndSum = 0;
        int discountedPrice = 0;
        int count = 0;
        for (Product product1 : products) {
            if (product.getId() == product1.getId()) {
                if(productRequest.getBasketId() == null) {
                    product1.setBasket(null);
                    productRepository.save(product1);
                    minusSum = basket.getSum() - product1.getPrice();
                    minusEndSum = basket.getEndSum() - product1.getCurrentPrice();
                    discountedPrice = minusSum - minusEndSum;
                    basket.setSum(minusSum);
                    basket.setEndSum(minusEndSum);
                    basket.setDisPercentSum(discountedPrice);
                    count++;
                    basket.setQuantityOfProducts(products.size() - count);
                    simpleResponse.setMessage("The product was successfully deleted from cart");
                    simpleResponse.setHttpStatus(HttpStatus.OK);
                }
            } else {
                simpleResponse.setMessage("The product is not exists in cart");
                simpleResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            }
        }
        if(products.isEmpty()){
            simpleResponse.setMessage("The cart is empty");
            simpleResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        }
        basketRepository.save(basket);
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

    @Override
    public SimpleResponse removeAllProductFromBasket(Long id, ProductRequest productRequest) {
        Basket basket = basketRepository.findById(id).get();
        List<Product> products = basket.getProducts();
        SimpleResponse simpleResponse = new SimpleResponse();
        int count = 0;
        for (Product product : products) {
            if (productRequest.getBasketId() == null) {
                basket.setQuantityOfProducts(0);
                basket.setSum(0);
                basket.setDisPercentSum(0);
                basket.setEndSum(0);
                product.setBasket(null);
                count++;
                simpleResponse.setMessage("Products were successfully deleted from cart. Count of products, that you've deleted: " + count);
                simpleResponse.setHttpStatus(HttpStatus.OK);
            }
            productRepository.save(product);
        }
        if(products.isEmpty()){
            simpleResponse.setMessage("The cart is empty");
            simpleResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        }
        basketRepository.save(basket);
        return simpleResponse;
    }

    @Override
    public OrderSumResponse sumOfOrders(Long id) {
        Basket basket = basketRepository.findById(id).get();
        return mapToResponseForSumOfOrders(basket);
    }

    private OrderSumResponse mapToResponseForSumOfOrders(Basket basket) {
        OrderSumResponse orderSumResponse = new OrderSumResponse();
        orderSumResponse.setQuantityOfProducts(basket.getQuantityOfProducts());
        orderSumResponse.setDisPercentSum(basket.getDisPercentSum());
        orderSumResponse.setSum(basket.getSum());
        orderSumResponse.setEndSum(basket.getEndSum());
        return orderSumResponse;
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
        productResponse.setQuantityOfProducts(productRepository.Quantity(product.getBrand(),
                product.getColor(), product.getRam(),
                product.getQuantityOfSim(), product.getPrice()));
        productResponse.setCurrentPrice(product.getCurrentPrice());
        if (product.getBasket() != null) {
            productResponse.setInBasket(true);
        } else {
            productResponse.setInBasket(false);
        }
        return productResponse;
    }
}
