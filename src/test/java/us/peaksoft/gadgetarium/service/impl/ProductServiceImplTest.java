package us.peaksoft.gadgetarium.service.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import us.peaksoft.gadgetarium.dto.ProductRequest;
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

import java.time.LocalDate;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
class ProductServiceImplTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    DiscountRepository discountRepository;

    @Autowired
    BasketRepository basketRepository;

    @Autowired
    ProductServiceImpl productService;

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
        product1.setColor(Color.BLACK);
        product1.setDateOfIssue("28-11-2023");
        product1.setOs(OS.ANDROID);
        product1.setRam("2gb");
        product1.setRom("64gb");
        product1.setSim("tele2");
        product1.setQuantityOfSim(2l);
        product1.setCpu("cpu");
        product1.setWeight("100gr");
        product1.setGuarantee("24-11-2024");
        product1.setImage("image.jpg");
        product1.setCategory(category);
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
    @Order(1)
    public void itShouldReturnAllProducts(){
        assertEquals(2,productService.getAllProducts().size());
    }

    @Test
    @Order(2)
    void itShouldSaveProduct() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("Phone");
        productRequest.setBrand(Brand.APPLE);
        productRequest.setColor(Color.BLACK);
        productRequest.setDateOfIssue("28-11-2023");
        productRequest.setOs(OS.IOS);
        productRequest.setRam("3gb");
        productRequest.setRom("128gb");
        productRequest.setSim("tele2");
        productRequest.setQuantityOfSim(2l);
        productRequest.setCpu("cpu");
        productRequest.setWeight("100gr");
        productRequest.setGuarantee("28-11-2024");
        productRequest.setDisplayInch("display");
        productRequest.setAppointment("appointment");
        productRequest.setCapacityBattery("100");
        productRequest.setCategoryId(1l);

        productService.save(productRequest);
        assertNotNull(productRepository.findById(3l).get());
    }

    @Test
    @Order(3)
    public void itShouldSavePriceAndQuantity(){
        ProductRequest productRequest = new ProductRequest();
        productRequest.setPrice(45000);
        productRequest.setDiscountId(1l);

        productService.savePriceAndQuantity(3l,productRequest);
        assertNotNull(productRepository.findById(3l).get().getPrice());
    }

    @Test
    @Order(4)
    public void itShouldSaveDescription(){
        ProductRequest productRequest = new ProductRequest();
        productRequest.setDescription("It is Iphone. Brand: Apple");
        productRequest.setImage("image.jpg");

        productService.saveDescription(3l,productRequest);
        assertNotNull(productRepository.findById(3l).get().getDescription());
    }

    @Test
    @Order(5)
    public void ItShouldReturnProductById(){
        Product product = productRepository.findById(3l).get();
        assertEquals(productService.getById(3l).getId(),product.getId());
    }

    @Test
    @Order(6)
    public void itShouldUpdateProduct(){
        Product product = productRepository.findById(2l).get();
        product.setName("Phone");
        product.setBrand(Brand.SAMSUNG);
        product.setColor(Color.BLACK);
        product.setDateOfIssue("28-11-2023");
        product.setOs(OS.ANDROID);
        product.setRam("3gb");
        product.setRom("128gb");
        product.setSim("tele2");
        product.setQuantityOfSim(2l);
        product.setCpu("cpu");
        product.setWeight("100gr");
        product.setGuarantee("28-11-2024");
        product.setDisplayInch("display");
        product.setAppointment("appointment");
        product.setCapacityBattery("100");
        product.setCategoryId(1l);
        product.setPrice(productRepository.findById(2l).get().getPrice());
        product.setDiscountId(2l);
        product.setDescription("It is Iphone. Brand: Apple");
        product.setImage("image.jpg");
        given(productRepository.findById(product.getId())).isNotSameAs(productRepository.findById(2l));

        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("Phone");
        productRequest.setBrand(Brand.SAMSUNG);
        productRequest.setColor(Color.BLACK);
        productRequest.setDateOfIssue("28-11-2023");
        productRequest.setOs(OS.ANDROID);
        productRequest.setRam("3gb");
        productRequest.setRom("128gb");
        productRequest.setSim("tele2");
        productRequest.setQuantityOfSim(2l);
        productRequest.setCpu("cpu");
        productRequest.setWeight("100gr");
        productRequest.setGuarantee("28-11-2024");
        productRequest.setDisplayInch("display");
        productRequest.setAppointment("appointment");
        productRequest.setCapacityBattery("100");
        productRequest.setCategoryId(1l);
        productRequest.setPrice(productRepository.findById(2l).get().getPrice());
        productRequest.setDiscountId(2l);
        productRequest.setDescription("It is Iphone. Brand: Apple");
        productRequest.setImage("image.jpg");
        productService.update(2l,productRequest);
    }

    @Test
    @Order(7)
    public void itShouldDeleteProduct(){
        productService.delete(1l);
        Boolean exists = productRepository.existsById(1l);
        assertTrue(exists.equals(false));
    }

    @Test
    @Order(8)
    public void itShouldSearchProducts(){
        String text = "APPLE";
        int page = 1;
        int size = 3;
        assertEquals(2, productService.searchAndPagination(text,page,size).size());
    }

}