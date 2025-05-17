package com.luciano.cotizaciones_api.scheduler;

import com.luciano.cotizaciones_api.model.Cotizacion;
import com.luciano.cotizaciones_api.repository.CotizacionRepository;
import com.luciano.cotizaciones_api.service.CotizacionApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CotizacionScheduler {

    private static final Logger logger = LoggerFactory.getLogger(CotizacionScheduler.class);

    private final CotizacionApiService apiService;
    private final CotizacionRepository repository;

    public CotizacionScheduler(CotizacionApiService apiService, CotizacionRepository repository) {
        this.apiService = apiService;
        this.repository = repository;
    }

    @Scheduled(fixedRate = 3600000) // cada 1 hora (en milisegundos)
    public void guardarCotizacionPeriodica() {
        try {
            Cotizacion cotizacion = apiService.obtenerCotizacion();
            repository.save(cotizacion);
            logger.info("✔ Cotización guardada automáticamente: {}", cotizacion.getFechaConsulta());
        } catch (Exception e) {
            logger.error("❌ Error al guardar cotización automáticamente", e);
        }
    }
}
