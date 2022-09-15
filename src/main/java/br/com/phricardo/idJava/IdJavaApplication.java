package br.com.phricardo.idJava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication()
public class IdJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdJavaApplication.class, args);
	}

}
