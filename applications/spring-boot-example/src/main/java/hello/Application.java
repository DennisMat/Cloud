package hello;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class Application {

	@RequestMapping("/")
	public String home() {
		try {
			
			
			final String uri = "dennisservice2:8094/insideapp";
		     
		    RestTemplate restTemplate = new RestTemplate();
		    String resultInsideApp = "Inside app not working";
		    try {
		    	resultInsideApp = restTemplate.getForObject(uri, String.class);
		    } catch (Exception e) {
				
			}

			return "Hello from outside app, from dennis " 
		    + "<br>Ip adress = " + InetAddress.getLocalHost().getHostAddress()
		    + "<br>Time = " + (new Date()).toString()
		    + "<br><br><br>"+resultInsideApp;
		    
		} catch (Exception e) {
			return "";
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
