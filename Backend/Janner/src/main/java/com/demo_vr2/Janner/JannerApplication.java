package com.demo_vr2.Janner;

import com.demo_vr2.Janner.dto.PersonaDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JannerApplication {

	public static void main(String[] args) {

		SpringApplication.run(
				JannerApplication.class,
				args);

		ClienteRest clienteRest =
				new ClienteRest();
	}
}


