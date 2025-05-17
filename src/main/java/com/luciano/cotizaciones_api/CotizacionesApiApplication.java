package com.luciano.cotizaciones_api;



import com.luciano.cotizaciones_api.model.Cotizacion;
import com.luciano.cotizaciones_api.repository.CotizacionRepository;
import com.luciano.cotizaciones_api.service.CotizacionApiService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableScheduling  //Automatización
public class CotizacionesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CotizacionesApiApplication.class, args);
	}


	/*
	@Bean
	public CommandLineRunner insertarCotizacionReal(CotizacionApiService apiService, CotizacionRepository repository) {
		return args -> {
			Cotizacion cot = apiService.obtenerCotizacion();
			repository.save(cot);
			System.out.println("✔ Cotización real guardada desde la API externa");
		};
	}
	 */
}