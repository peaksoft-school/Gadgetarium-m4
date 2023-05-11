package us.peaksoft.gadgetarium.service_test;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import us.peaksoft.gadgetarium.dto.ProductRequest;
import us.peaksoft.gadgetarium.entity.Basket;
import us.peaksoft.gadgetarium.entity.Category;
import us.peaksoft.gadgetarium.entity.Discount;
import us.peaksoft.gadgetarium.entity.Product;
import us.peaksoft.gadgetarium.enums.Brand;
import us.peaksoft.gadgetarium.enums.Color;
import us.peaksoft.gadgetarium.enums.OS;
import us.peaksoft.gadgetarium.enums.Subcat;
import us.peaksoft.gadgetarium.repository.BasketRepository;
import us.peaksoft.gadgetarium.repository.CategoryRepository;
import us.peaksoft.gadgetarium.repository.DiscountRepository;
import us.peaksoft.gadgetarium.repository.ProductRepository;
import us.peaksoft.gadgetarium.service.ProductService;
import us.peaksoft.gadgetarium.service.impl.ProductServiceImpl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

import static com.amazonaws.services.route53domains.model.CountryCode.GB;
import static com.amazonaws.services.route53domains.model.CountryCode.GR;
import static org.junit.jupiter.api.Assertions.*;
import static us.peaksoft.gadgetarium.enums.Color.RED;

@SpringBootTest(properties = {
        "spring.datasource.password=bektur97@",
        "spring.datasource.url=jdbc:postgresql://localhost:5432/postgres",
        "spring.datasource.username=postgres",
        "spring.jpa.hibernate.ddl-auto=create",
        "spring.jpa.show-sql=true",
        "cloud.aws.credentials.access-key=AKIA2HDIMUN7XHVDGAXA",
        "cloud.aws.credentials.secret-key=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",
        "cloud.aws.region.static=eu-central-1",
        "application.bucket.name=gadgetarium-m4"
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductServiceTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BasketRepository basketRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    DiscountRepository discountRepository;


    @Test
    public void testDownloadFile() throws IOException {

         Basket basket = new Basket();
        System.out.println("hello");
        System.out.println("hello");
        System.out.println("hello");
        basket.setId(2l);
        basket.setSum(23);
        basket.setEndSum(23);
        basket.setQuantityOfProducts(344);
        basket.setDisPercentSum(22);
        System.out.println(basket.toString());
        System.out.println("hello");
        System.out.println("hello");
        System.out.println("hello");
        System.out.println(basket.getDisPercentSum());
        basketRepository.save(basket);

        Category category = new Category();
//        category.setId(2l);
        category.setName("Laptop");
        category.setSubcat(Subcat.LAPTOPS);
        System.out.println("Javs");
        categoryRepository.save(category);
        System.out.println("helllllll");

//        Basket basket = new Basket();
//        System.out.println("hello");
//        System.out.println("hello");
//        System.out.println("hello");
//        basket.setId(1l);
//        basket.setSum(23);
//        basket.setEndSum(23);
//        basket.setQuantityOfProducts(344);
//        basket.setDisPercentSum(22);
//        System.out.println(basket.toString());
//        System.out.println("hello");
//        System.out.println("hello");
//        System.out.println("hello");
//        System.out.println(basket.getDisPercentSum());
//        basketRepository.save(basket);

        Discount discount = new Discount();
        discount.setId(2l);
        discount.setPercent(15);
        discount.setDateOfFinish(LocalDate.of(2023, 3, 28));
        discount.setDateOfStart(LocalDate.of(2023, 4, 20));
        discountRepository.save(discount);

        ProductService productService = new ProductServiceImpl(categoryRepository, productRepository, discountRepository);


//        ProductRequest productRequest=new ProductRequest();
//        productRequest.setName(productRequest.getName());
//        productRequest.setBrand(productRequest.getBrand());
//        productRequest.setColor(productRequest.getColor());
//        productRequest.setDateOfIssue(productRequest.getDateOfIssue());
//        productRequest.setOs(productRequest.getOs());
//        productRequest.setRam(productRequest.getRam());
//        productRequest.setRom(productRequest.getRom());
//        productRequest.setSim(productRequest.getSim());
//        productRequest.setQuantityOfSim(productRequest.getQuantityOfSim());
//        productRequest.setCpu(productRequest.getCpu());
//        productRequest.setWeight(productRequest.getWeight());
//        productRequest.setGuarantee(productRequest.getGuarantee());
//        productRequest.setDisplayInch(productRequest.getDisplayInch());
//        productRequest.setAppointment(productRequest.getAppointment());
//        productRequest.setCapacityBattery(productRequest.getCapacityBattery());
//        productRequest.setCategoryId(1l);
//
//        Product product = new Product();
//        product.setName(productRequest.getName());
//        product.setBrand(productRequest.getBrand());
//        product.setColor(productRequest.getColor());
//        product.setDateOfIssue(productRequest.getDateOfIssue());
//        product.setOs(productRequest.getOs());
//        product.setRam(productRequest.getRam());
//        product.setRom(productRequest.getRom());
//        product.setSim(productRequest.getSim());
//        product.setQuantityOfSim(productRequest.getQuantityOfSim());
//        product.setCpu(productRequest.getCpu());
//        product.setWeight(productRequest.getWeight());
//        product.setGuarantee(productRequest.getGuarantee());
//        product.setDisplayInch(productRequest.getDisplayInch());
//        product.setAppointment(productRequest.getAppointment());
//        product.setCapacityBattery(productRequest.getCapacityBattery());
//     //   if (productRequest.getCategoryId() != null) {
//     ////       Category category1 = categoryRepository.findById(1l).get();
//            product.setCategory(category);
//    //    }

//        List<Product> productList = new ArrayList<>();
//        productList.add(product);
//        basket.setProducts(productList);
//        discount.setProducts(productList);
//        discountRepository.save(discount);
//        product.setDiscount(discount);
//        basketRepository.save(basket);
//        product.setBasket(basket);
//       productService.save(productRequest);

        File success = productService.file(1l);
assertNotEquals("Info.pdf",success);
        assertTrue(success.exists());
}}
