package hexlet.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		try {
			System.out.println("gnoooo");
			SpringApplication.run(AppApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
