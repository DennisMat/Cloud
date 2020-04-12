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

			String h = System.getenv("DENNISSERVICE2_SERVICE_HOST"); //to get environment variables.
			
			final String url ="http://dennisexposedservice2:8094/insideapp";			
			
			RestTemplate restTemplate = new RestTemplate();
			String resultInsideApp = "Inside app not working";
			try {
				resultInsideApp = restTemplate.getForObject(url, String.class);
			} catch (Exception e) {}


			return "Hello from client app v9 , from dennis " 
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
