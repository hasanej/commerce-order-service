package hsn.commerce.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CommerceOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommerceOrderServiceApplication.class, args);
	}

}
