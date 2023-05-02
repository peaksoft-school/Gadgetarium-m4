package us.peaksoft.gadgetarium.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import us.peaksoft.gadgetarium.dto.*;
import us.peaksoft.gadgetarium.entity.Category;
import us.peaksoft.gadgetarium.entity.Discount;
import us.peaksoft.gadgetarium.entity.Product;
import us.peaksoft.gadgetarium.repository.CategoryRepository;
import us.peaksoft.gadgetarium.repository.DiscountRepository;
import us.peaksoft.gadgetarium.repository.ProductRepository;
import us.peaksoft.gadgetarium.service.ProductService;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final DiscountRepository discountRepository;


    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productsList = new ArrayList<>();
        for (Product product : products) {
            productsList.add(mapToResponseForDescriptionAndSavingPrice(product));
        }
        return productsList;
    }

    @Override
    public List<ProductResponse> filterProducts(String brand, String color, String ram, String rom, int fromPrice, int toPrice, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        List<Product> products = productRepository.filter(brand, color, ram, rom, fromPrice, toPrice, pageable);
        List<ProductResponse> productsList = new ArrayList<>();
        for (Product product : products) {
            productsList.add(mapToResponse(product));
            productsList.add(mapToResponseForDescriptionAndSavingPrice(product));
        }
        return productsList;
    }
    @Override
    public File file(Long id) throws IOException {
        File file = new File("Info.pdf");
        FileWriter writer = new FileWriter("Info.pdf");

        Product product = productRepository.findById(id).get();
        writer.write("Product name: " + product.getName() + "\n");
        writer.write("Product brand: " + product.getBrand() + "\n");
        writer.write("Product color: " + product.getColor() + "\n");
        writer.write("Product price: " + product.getPrice() + "\n");
        writer.write("Date of issue: " + product.getDateOfIssue() + "\n");
        writer.write("Product os: " + product.getOs() + "\n");
        writer.write("Product ram: " + product.getRam() + "\n");
        writer.write("Product sim: " + product.getSim() + "\n");
        writer.write("Product rom: " + product.getRom() + "\n");
        writer.write("Product cpu: " + product.getCpu() + "\n");
        writer.write("Product appointment: " + product.getAppointment() + "\n");
        writer.write("Product capacity battery: " + product.getCapacityBattery() + "\n");
        writer.write("Product guarantee: " + product.getGuarantee() + "\n");
        writer.write("Product description: " + product.getDisplayInch() + "\n");
        writer.write("Product image: " + product.getImage() + "\n");
        writer.write("Product quantity of similar: " + product.getQuantityOfSim() + "\n");
        writer.write("Product weight: " + product.getWeight() + "\n");
        writer.write("Product category: " + product.getCategory() + "\n");
        writer.close();

        return file;
    }

    @Override
    public ProductResponse save(ProductRequest productRequest) {
        Product product = mapToEntity(productRequest);
        productRepository.save(product);
        return mapToResponse(product);
    }

    @Override
    public ProductResponse savePriceAndQuantity(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).get();
        product.setPrice(productRequest.getPrice());
        if (productRequest.getDiscountId() != null) {
            Discount discount = discountRepository.findById(productRequest.getDiscountId()).get();
            product.setDiscount(discount);
            product.setDisPercent(discount.getPercent());
            if (product.getDiscount().getId() != null) {
                double disPer = (double) product.getDiscount().getPercent() / 100;
                double disPrice = product.getPrice() * disPer;
                int discountedPrice = (int) (product.getPrice() - disPrice);
                product.setCurrentPrice(discountedPrice);
                product.setDisPercent(product.getDiscount().getPercent());
            }
        }
        productRepository.save(product);
        return mapToResponseForDescriptionAndSavingPrice(product);

    }

    @Override
    public ProductResponse saveDescription(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).get();
        product.setImage(productRequest.getImage());
        product.setDescription(productRequest.getDescription());
        productRepository.save(product);
        return mapToResponseForDescriptionAndSavingPrice(product);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).get();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setBrand(productRequest.getBrand());
        product.setColor(productRequest.getColor());
        product.setDateOfIssue(productRequest.getDateOfIssue());
        product.setOs(productRequest.getOs());
        product.setRam(productRequest.getRam());
        product.setRom(productRequest.getRom());
        product.setSim(productRequest.getSim());
        product.setQuantityOfSim(productRequest.getQuantityOfSim());
        product.setCpu(productRequest.getCpu());
        product.setWeight(productRequest.getWeight());
        product.setGuarantee(productRequest.getGuarantee());
        product.setImage(productRequest.getImage());
        product.setDisplayInch(productRequest.getDisplayInch());
        product.setAppointment(productRequest.getAppointment());
        product.setCapacityBattery(productRequest.getCapacityBattery());
        if (productRequest.getCategoryId() != null) {
            Category category = categoryRepository.findById(productRequest.getCategoryId()).get();
            product.setCategory(category);
        }
        product.setDescription(productRequest.getDescription());
        productRepository.saveAndFlush(product);
        return mapToResponseForDescriptionAndSavingPrice(product);
    }

    @Override
    public ProductResponse getById(Long id) {
        Product product = productRepository.findById(id).get();
        return mapToResponseForDescriptionAndSavingPrice(product);
    }

    @Override
    public SimpleResponse delete(Long id) {
        SimpleResponse productDeleteResponse = new SimpleResponse();
        Boolean exists1 = productRepository.existsById(id);
        Product product = new Product();
        if (exists1) {
            product = productRepository.findById(id).get();
        }
        if (product.getId() == id) {
            productRepository.delete(product);
            productDeleteResponse.setHttpStatus(HttpStatus.OK);
            productDeleteResponse.setMessage("the product with this id: " + product.getId() + " was deleted");
        } else {
            productDeleteResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            productDeleteResponse.setMessage("the product's id is " + product.getId());
        }
        return productDeleteResponse;
    }

    @Override
    public List<ProductDetailsResponse> productDetails() {
        List<Product> products = productRepository.findAll();
        List<ProductDetailsResponse> productsList = new ArrayList<>();
        for (Product product : products) {
            productsList.add(mapToDetailsResponse(product));
        }
        return productsList;
    }

    @Override
    public List<ProductResponse> searchAndPagination(String text, int page, int size) {
        String text1 = text == null ? "" : text;
        Pageable pageable = PageRequest.of(page - 1, size);
        List<Product> products = productRepository.searchProductAndPagination(text1.toUpperCase(), pageable);
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(mapToResponse(product));
        }
        return productResponses;
    }

    private Product mapToEntity(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setBrand(productRequest.getBrand());
        product.setColor(productRequest.getColor());
        product.setDateOfIssue(productRequest.getDateOfIssue());
        product.setOs(productRequest.getOs());
        product.setRam(productRequest.getRam());
        product.setRom(productRequest.getRom());
        product.setSim(productRequest.getSim());
        product.setQuantityOfSim(productRequest.getQuantityOfSim());
        product.setCpu(productRequest.getCpu());
        product.setWeight(productRequest.getWeight());
        product.setGuarantee(productRequest.getGuarantee());
        product.setDisplayInch(productRequest.getDisplayInch());
        product.setAppointment(productRequest.getAppointment());
        product.setCapacityBattery(productRequest.getCapacityBattery());
        if (productRequest.getCategoryId() != null) {
            Category category = categoryRepository.findById(productRequest.getCategoryId()).get();
            product.setCategory(category);
        }
        if (productRequest.getDiscountId() != null) {
            Discount discount = discountRepository.findById(productRequest.getDiscountId()).get();
            product.setDiscount(discount);
        }
        return product;
    }


    private ProductResponse mapToResponse(Product product) {
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
        return productResponse;
    }

    private ProductResponse mapToResponseForDescriptionAndSavingPrice(Product product) {
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

    private ProductDetailsResponse mapToDetailsResponse(Product product) {
        ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse();
        productDetailsResponse.setId(product.getId());
        productDetailsResponse.setImage(productDetailsResponse.getImage());
        productDetailsResponse.setName(productDetailsResponse.getName());
        productDetailsResponse.setColor(product.getColor());
        productDetailsResponse.setSim(product.getSim());
        productDetailsResponse.setRam(product.getRam());
        productDetailsResponse.setRom(product.getRom());
        productDetailsResponse.setPrice(product.getPrice());
        productDetailsResponse.setQuantityOfProducts(productRepository.Quantity(product.getBrand(),
                product.getColor(), product.getRam(),
                product.getQuantityOfSim(), product.getPrice()));
        return productDetailsResponse;
    }
}
