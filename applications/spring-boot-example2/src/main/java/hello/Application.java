package hello;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	@RequestMapping("/insideapp")
	public String home() {
		try {
			return "Hello from the inside app. " 
		    + "<br>Ip adress = " + InetAddress.getLocalHost().getHostAddress()
		    + "<br>Time = " + (new Date()).toString();
		    
		} catch (UnknownHostException e) {
			return "";
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
