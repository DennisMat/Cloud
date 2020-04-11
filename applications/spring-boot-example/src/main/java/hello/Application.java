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

			final String url1 = "http://dennisservice2:8094/insideapp";
			final String url2 ="http://dennisservice2.default.svc.cluster.local:8094/insideapp";
			final String url3 = "http://dennisservice2:8090/insideapp";
			final String url4="http://dennisservice2.default.svc.cluster.local:8090/insideapp";
			RestTemplate restTemplate = new RestTemplate();
			String resultInsideApp = "Inside app not working";
			try {
				resultInsideApp = restTemplate.getForObject(url1, String.class);
				resultInsideApp+="url1 works";
			} catch (Exception e) {}

			try {
				resultInsideApp += restTemplate.getForObject(url2, String.class);
				resultInsideApp+="url2 works";
			} catch (Exception e) {}
			
			try {
				resultInsideApp += restTemplate.getForObject(url3, String.class);
				resultInsideApp+="url3 works";
			} catch (Exception e) {}
			
			try {
				resultInsideApp += restTemplate.getForObject(url4, String.class);
				resultInsideApp+="url4 works";
			} catch (Exception e) {}

			return "Hello from outside app v7 , from dennis " 
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
