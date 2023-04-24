package us.peaksoft.gadgetarium.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import us.peaksoft.gadgetarium.dto.*;
import us.peaksoft.gadgetarium.entity.Chosen;
import us.peaksoft.gadgetarium.entity.Product;
import us.peaksoft.gadgetarium.repository.ChosenRepository;
import us.peaksoft.gadgetarium.repository.ProductRepository;
import us.peaksoft.gadgetarium.service.ChosenService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChosenServiceImpl implements ChosenService {
    private final ProductRepository productRepository;
    private final ChosenRepository chosenRepository;

    @Override
    public ProductResponse saveProductInChosen(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).get();
        if (productRequest.getChosenId() != null) {
            Chosen chosen = chosenRepository.findById(productRequest.getChosenId()).get();
            product.setChosen(chosen);
        }
        productRepository.save(product);
        return mapToResponseForProduct(product);
    }

    @Override
    public ChosenResponse getById(Long id) {
        Chosen chosen = chosenRepository.findById(id).get();
        return mapToResponse(chosen);
    }

    @Override
    public SimpleResponse deleteProductFromChosen(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).get();
        SimpleResponse simpleResponse = new SimpleResponse();
        if (productRequest.getChosenId() == null) {
            product.setChosen(null);
            simpleResponse.setMessage("This product is successful deleted...");
            simpleResponse.setHttpStatus(HttpStatus.OK);
        }
        productRepository.save(product);
        return simpleResponse;
    }

    @Override
    public List<ProductResponse> getProductsByChosenId(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        List<Product> products = chosenRepository.getProductsByChosenId(id, pageable);
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(mapToResponseForProduct(product));
        }
        return productResponses;
    }

    @Override
    public SimpleResponse deleteAllProducts(Long id, ProductRequest productRequest) {
        Chosen chosen = chosenRepository.findById(id).get();
        List<Product> products = chosen.getProducts();
        SimpleResponse simpleResponse = new SimpleResponse();
        for (Product product : products) {
            if (product.getChosenId() == null) {
                product.setChosen(null);
                simpleResponse.setMessage("All products is successful deleted");
                simpleResponse.setHttpStatus(HttpStatus.OK);
            }
            productRepository.save(product);
        }
        return simpleResponse;
    }

    public ChosenResponse mapToResponse(Chosen chosen) {
        ChosenResponse chosenResponse = new ChosenResponse();
        chosenResponse.setId(chosen.getId());
        return chosenResponse;
    }

    public Chosen mapToRequest(ChosenRequest chosenRequest) {
        Chosen chosen = new Chosen();
        chosen.setId(chosenRequest.getId());
        return chosen;
    }

    public ProductResponse mapToResponseForProduct(Product product) {
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
        productResponse.setDisPercent(product.getDiscount().getPercent());
        double disPer = (double) product.getDiscount().getPercent() / 100;
        double disPrice = product.getPrice() * disPer;
        int discountedPrice = (int) (product.getPrice() - disPrice);
        productResponse.setCurrentPrice(discountedPrice);
        return productResponse;

    }

}