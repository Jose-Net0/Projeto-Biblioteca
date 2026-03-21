package org.iftm.modelo_api_rest;

import java.time.Instant;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ModeloApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModeloApiRestApplication.class, args);
	}

}
