package us.peaksoft.gadgetarium.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import us.peaksoft.gadgetarium.dto.ProductRequest;
import us.peaksoft.gadgetarium.dto.ProductResponse;
import us.peaksoft.gadgetarium.entity.Category;
import us.peaksoft.gadgetarium.entity.Product;
import us.peaksoft.gadgetarium.repository.CategoryRepository;
import us.peaksoft.gadgetarium.repository.ProductRepository;
import us.peaksoft.gadgetarium.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product>products = productRepository.findAll();
        List<ProductResponse>productResponses = new ArrayList<>();
        for(Product product : products){
            productResponses.add(mapToResponse(product));
        }
        return productResponses;
    }

    @Override
    public ProductResponse save(ProductRequest product) {
        Product product1 = mapToEntity(product);
        productRepository.save(product1);
        return mapToResponse(product1);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest product) {
        Product product1 = productRepository.findById(id).get();
        product1.setName(product.getName());
        product1.setColor(product.getColor());
        product1.setBrand(product.getBrand());
        product1.setDateOfIssue(product.getDateOfIssue());
        product1.setOs(product.getOs());
        product1.setRam(product.getRam());
        product1.setRom(product.getRom());
        product1.setSim(product.getSim());
        product1.setCpu(product.getCpu());
        product1.setGuarantee(product.getGuarantee());
        product1.setImage(product.getImage());
        product1.setDisplayInch(product.getDisplayInch());
        product1.setAppointment(product.getAppointment());
        product1.setCapacityBattery(product.getCapacityBattery());
        if(product1.getCategoryId() != null){
            Category category = categoryRepository.findById(product.getCategoryId()).get();
            product1.setCategory(category);
        }
        productRepository.saveAndFlush(product1);
        return mapToResponse(product1);
    }

    @Override
    public ProductResponse getById(Long id) {
       Product product = productRepository.findById(id).get();
        return mapToResponse(product);
    }

    @Override
    public void delete(Long id) {
       Product product = productRepository.findById(id).get();
        productRepository.delete(product);
    }
    public Product mapToEntity ( ProductRequest productRequest ){
       Product product = new Product();
       product.setName(productRequest.getName());
       product.setPrice(productRequest.getPrice());
       product.setBrand(productRequest.getBrand());
       product.setColor(productRequest.getColor());
       product.setDateOfIssue(productRequest.getDateOfIssue());
       product.setOs(productRequest.getOs());
       product.setRam(productRequest.getRam());
       product.setRom(productRequest.getRom());
       product.setSim(productRequest.getSim());
       product.setCpu(productRequest.getCpu());
       product.setGuarantee(productRequest.getGuarantee());
       product.setImage(productRequest.getImage());
       product.setDisplayInch(productRequest.getDisplayInch());
       product.setAppointment(productRequest.getAppointment());
       product.setCapacityBattery(productRequest.getCapacityBattery());
        if(product.getCategoryId() != null){
            Category category = categoryRepository.findById(product.getCategoryId()).get();
            product.setCategory(category);
        }
       return product;
    }

    public ProductResponse mapToResponse(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setColor(product.getColor());
        productResponse.setBrand(product.getBrand());
        productResponse.setDateOfIssue(product.getDateOfIssue());
        productResponse.setOs(product.getOs());
        productResponse.setRam(product.getRam());
        productResponse.setRom(product.getRom());
        productResponse.setSim(product.getSim());
        productResponse.setCpu(product.getCpu());
        productResponse.setGuarantee(product.getGuarantee());
        productResponse.setImage(product.getImage());
        productResponse.setDisplayInch(product.getDisplayInch());
        productResponse.setAppointment(product.getAppointment());
        productResponse.setCapacityBattery(product.getCapacityBattery());
        return productResponse;
    }
}
