package us.peaksoft.gadgetarium.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import us.peaksoft.gadgetarium.entity.Category;
import us.peaksoft.gadgetarium.entity.Discount;
import us.peaksoft.gadgetarium.entity.Product;
import us.peaksoft.gadgetarium.enums.Brand;
import us.peaksoft.gadgetarium.enums.Color;
import us.peaksoft.gadgetarium.enums.OS;
import us.peaksoft.gadgetarium.enums.Subcat;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties ={"spring.datasource.password=aidai2004",
        "spring.datasource.url=jdbc:postgresql://localhost:5432/postgres",
        "spring.datasource.username=postgres",
        "spring.sql.init.platform=org.hibernate.dialect.PostgreSQLDialect",
        "spring.jpa.hibernate.ddl-auto=create",
        "spring.jpa.show-sql=true",
        "cloud.aws.credentials.access-key=AKIA2HDIMUN7XHVDGAXA",
        "cloud.aws.credentials.secret-key=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",
        "cloud.aws.region.static=eu-central-1",
        "application.bucket.name=gadgetarium-m4"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DiscountRepository discountRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @BeforeEach
    public void beforeEach(){
        Discount discount = new Discount();
        discount.setId(1l);
        discount.setPercent(0);
        discount.setDateOfFinish(LocalDate.of(2023,3,23));
        discount.setDateOfStart(LocalDate.of(2023,4,23));
        discountRepository.save(discount);

        Discount discount1 = new Discount();
        discount.setId(2l);
        discount.setPercent(10);
        discount.setDateOfFinish(LocalDate.of(2023,3,23));
        discount.setDateOfStart(LocalDate.of(2023,4,23));
        discountRepository.save(discount1);

        Category category = new Category();
        category.setId(1l);
        category.setSubcat(Subcat.SMARTPHONES);
        category.setName("Phones");
        categoryRepository.save(category);

        Category category1 = new Category();
        category1.setId(2l);
        category1.setSubcat(Subcat.SMARTPHONES);
        category1.setName("Phones12");
        categoryRepository.save(category1);

        Product product = new Product();
        product.setId(1l);
        product.setName("IPhone11");
        product.setPrice(25000);
        product.setBrand(Brand.APPLE);
        product.setColor(Color.BLACK);
        product.setDateOfIssue("28-11-2023");
        product.setOs(OS.IOS);
        product.setRam("3gb");
        product.setRom("128gb");
        product.setSim("tele2");
        product.setQuantityOfSim(2l);
        product.setCpu("cpu");
        product.setWeight("100gr");
        product.setGuarantee("24-11-2024");
        product.setImage("image.jpg");
        product.setCategory(category);
        product.setDiscount(discount);
        product.setDisPercent(discount.getPercent());
        if (product.getDiscount().getId() != null) {
            double disPer = (double) product.getDiscount().getPercent() / 100;
            double disPrice = product.getPrice() * disPer;
            int discountedPrice = (int) (product.getPrice() - disPrice);
            product.setCurrentPrice(discountedPrice);
            product.setDisPercent(product.getDiscount().getPercent());
        }
        productRepository.save(product);


        Product product1 = new Product();
        product1.setId(2l);
        product1.setName("Samsung");
        product1.setPrice(15000);
        product1.setBrand(Brand.SAMSUNG);
        product1.setColor(Color.RED);
        product1.setDateOfIssue("28-11-2023");
        product1.setOs(OS.ANDROID);
        product1.setRam("3gb");
        product1.setRom("64gb");
        product1.setSim("tele2");
        product1.setQuantityOfSim(2l);
        product1.setCpu("cpu");
        product1.setWeight("100gr");
        product1.setGuarantee("24-11-2024");
        product1.setImage("image.jpg");
        product1.setCategory(category1);
        product1.setDiscount(discountRepository.findById(1l).get());
        product1.setDisPercent(discount.getPercent());
        if (product1.getDiscount().getId() != null) {
            double disPer = (double) product1.getDiscount().getPercent() / 100;
            double disPrice = product1.getPrice() * disPer;
            int discountedPrice = (int) (product1.getPrice() - disPrice);
            product1.setCurrentPrice(discountedPrice);
            product1.setDisPercent(product1.getDiscount().getPercent());
        }
        productRepository.save(product1);
    }

    @Test
    void itShouldCountQuantity() {
        Long quantity = productRepository.Quantity(Brand.APPLE,Color.BLACK,"3gb",2l,25000);
        assertEquals(1,quantity);
    }

    @Test
    void itShouldFilter() {
        int page = 1;
        int size = 3;
        Pageable pageable = PageRequest.of(page-1,size);
        assertEquals(1,productRepository.filter("APPLE","BLACK","2gb","128gb",30000,50000,pageable).size());
    }

    @Test
    void itShouldSearchProduct() {
        String text = "SMA";
        int page = 1;
        int size = 3;
        Pageable pageable = PageRequest.of(page-1,size);
        assertEquals(2, productRepository.searchProductAndPagination(text,pageable).size());
    }
}