package us.peaksoft.gadgetarium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import us.peaksoft.gadgetarium.dto.ContactRequest;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Controller
@SpringBootApplication
public class GadgetariumApplication {

	public static void main(String[] args) {
		SpringApplication.run(GadgetariumApplication.class, args);
		System.out.println("Welcome colleagues, project name is Gadgetarium");
//		ContactRequest contact = new ContactRequest();
//		contact.setNumber("8393939393993");
//		contact.setUsername("king kobra");
//		contact.setName("aiadai");
//		contact.setMessage("hello");
//		contact.setEmail("jibek.82@gmail.com");
	}
	@GetMapping("/")
	public String greetingPage(){
		return "welcome";
	}
}
