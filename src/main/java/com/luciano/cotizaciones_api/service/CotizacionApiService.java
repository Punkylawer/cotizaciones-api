package com.luciano.cotizaciones_api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.luciano.cotizaciones_api.model.Cotizacion;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;


import java.time.LocalDateTime;

@Service
public class CotizacionApiService {

    private final WebClient webClient;

    public CotizacionApiService() {
        this.webClient = WebClient.create("https://api.bluelytics.com.ar/v2");
    }



    public Cotizacion obtenerCotizacion() {
        JsonNode json = webClient.get()
                .uri("/latest")
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block(); // usamos block() para mantenerlo simple y evitar trabajar con flujos reactivos por ahora

        if (json == null || !json.has("blue") || !json.has("oficial")) {
            throw new RuntimeException("Respuesta inválida de la API externa");
        }

        JsonNode blue = json.get("blue");
        JsonNode oficial = json.get("oficial");

        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setFechaConsulta(LocalDateTime.now());
        cotizacion.setDolarBlueCompra(blue.get("value_buy").asDouble());
        cotizacion.setDolarBlueVenta(blue.get("value_sell").asDouble());
        cotizacion.setDolarOficialCompra(oficial.get("value_buy").asDouble());
        cotizacion.setDolarOficialVenta(oficial.get("value_sell").asDouble());

        return cotizacion;

    }
}
