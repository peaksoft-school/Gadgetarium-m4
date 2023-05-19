package us.peaksoft.gadgetarium.service_test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import us.peaksoft.gadgetarium.entity.Product;
import us.peaksoft.gadgetarium.repository.BasketRepository;
import us.peaksoft.gadgetarium.repository.CategoryRepository;
import us.peaksoft.gadgetarium.repository.DiscountRepository;
import us.peaksoft.gadgetarium.repository.ProductRepository;
import us.peaksoft.gadgetarium.service.ProductService;
import us.peaksoft.gadgetarium.service.impl.ProductServiceImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {"spring.datasource.password=postgres",
        "spring.datasource.url=jdbc:postgresql://localhost:5432/gadgetarium",
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
        assertTrue(file2.exists());

        file.delete();
    }
}

