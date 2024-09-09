package br.com.itau.geradornotafiscal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "br.com.itau.geradornotafiscal.model")
public class GeradorNotaFiscalApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeradorNotaFiscalApplication.class, args);
	}

}
