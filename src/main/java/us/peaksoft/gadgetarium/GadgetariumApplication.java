package us.peaksoft.gadgetarium;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class GadgetariumApplication {

	public static void main(String[] args) {
		SpringApplication.run(GadgetariumApplication.class, args);
		System.out.println("Welcome colleagues, project name is Gadgetarium");
	}
	@GetMapping("/")
	public String greetingPage(){
		return "welcome";
	}
}
