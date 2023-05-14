package us.peaksoft.gadgetarium.service_test;

import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import us.peaksoft.gadgetarium.entity.Basket;
import us.peaksoft.gadgetarium.entity.Category;
import us.peaksoft.gadgetarium.entity.Discount;
import us.peaksoft.gadgetarium.entity.Product;
import us.peaksoft.gadgetarium.enums.Subcat;
import us.peaksoft.gadgetarium.repository.BasketRepository;
import us.peaksoft.gadgetarium.repository.CategoryRepository;
import us.peaksoft.gadgetarium.repository.DiscountRepository;
import us.peaksoft.gadgetarium.repository.ProductRepository;
import us.peaksoft.gadgetarium.service.ProductService;
import us.peaksoft.gadgetarium.service.impl.ProductServiceImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {"spring.datasource.password=bektur97@",
"spring.datasource.url=jdbc:postgresql://localhost:5432/postgres",
"spring.datasource.username=postgres", "spring.jpa.hibernate.ddl-auto=create",
"spring.jpa.show-sql=true", "cloud.aws.credentials.access-key=AKIA2HDIMUN7XHVDGAXA",
"cloud.aws.credentials.secret-key=/wmqjBqv8QwBDVjh0nq+pUA3Hc91A+RH8LQAs0i9",
"cloud.aws.region.static=eu-central-1", "application.bucket.name=gadgetarium-m4"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductServiceTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BasketRepository basketRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    DiscountRepository discountRepository;

    @BeforeAll
    public void beforeAll() {
        Basket basket = new Basket();
        basket.setId(2l);
        basket.setSum(23);
        basket.setEndSum(23);
        basket.setQuantityOfProducts(344);
        basket.setDisPercentSum(22);
        basketRepository.save(basket);

        Category category = new Category();
        category.setId(2l);
        category.setName("Laptop");
        category.setSubcat(Subcat.LAPTOPS);
        categoryRepository.save(category);

        Discount discount = new Discount();
        discount.setId(2l);
        discount.setPercent(15);
        discount.setDateOfFinish(LocalDate.of(2023, 3, 28));
        discount.setDateOfStart(LocalDate.of(2023, 4, 20));
        discountRepository.save(discount);
    }

    @Test
    public void testDownloadFile() throws IOException {
        ProductService productService = new ProductServiceImpl(categoryRepository, productRepository, discountRepository);

        File file = new File("Test.pdf");
        FileWriter writer = new FileWriter("Test.pdf");
        Product product = productRepository.findById(1l).get();
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
        Scanner scan1 = new Scanner(file);
        String fileContext1 = scan1.nextLine();
        scan1.close();

        File file2 = productService.file(1l);
        Scanner scan2 = new Scanner(file2);
        String fileContext2 = scan2.nextLine();
        scan2.close();

        assertEquals(fileContext1, fileContext2);
        assertNotEquals(null, fileContext2);

        file.delete();
    }
}

