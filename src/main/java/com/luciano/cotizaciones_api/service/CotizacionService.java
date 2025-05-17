package com.luciano.cotizaciones_api.service;

import com.luciano.cotizaciones_api.dto.VariacionDTO;
import com.luciano.cotizaciones_api.dto.EstadisticasDTO;
import com.luciano.cotizaciones_api.exception.RangoFechaInvalidoException;
import com.luciano.cotizaciones_api.model.Cotizacion;
import com.luciano.cotizaciones_api.repository.CotizacionRepository;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CotizacionService {

    private final CotizacionRepository repository;

    public CotizacionService(CotizacionRepository repository) {
        this.repository = repository;
    }

    // 📄 Consultas generales
    public List<Cotizacion> obtenerTodas() {
        return repository.findAll();
    }

    public Page<Cotizacion> obtenerPaginado(Pageable pageable) {
        return repository.findAll(pageable);
    }

    // 📅 Consultas por fechas
    public List<Cotizacion> obtenerEntreFechas(LocalDateTime desde, LocalDateTime hasta) {
        return repository.findByFechaConsultaBetween(desde, hasta);
    }

    public Cotizacion obtenerCotizacionDelDia() {
        LocalDateTime inicioDelDia = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime finDelDia = inicioDelDia.plusDays(1);

        List<Cotizacion> cotizaciones = repository.findByFechaConsultaBetweenOrderByFechaConsultaDesc(inicioDelDia, finDelDia);

        if (cotizaciones.isEmpty()) {
            throw new RuntimeException("No se encontraron cotizaciones para el día actual");
        }

        return cotizaciones.get(0); // la más reciente
    }

    // 📈 Estadísticas
    public EstadisticasDTO calcularEstadisticasEntreFechas(LocalDateTime desde, LocalDateTime hasta) {
        validarRangoFechas(desde, hasta);

        List<Cotizacion> cotizaciones = repository.findByFechaConsultaBetween(desde, hasta);

        if (cotizaciones.isEmpty()) {
            return new EstadisticasDTO(0, 0, 0);
        }

        CompletableFuture<Double> promedioFuture = CompletableFuture.supplyAsync(() ->
                cotizaciones.stream().mapToDouble(Cotizacion::getDolarBlueVenta).average().orElse(0)
        );

        CompletableFuture<Double> minimoFuture = CompletableFuture.supplyAsync(() ->
                cotizaciones.stream().mapToDouble(Cotizacion::getDolarBlueVenta).min().orElse(0)
        );

        CompletableFuture<Double> maximoFuture = CompletableFuture.supplyAsync(() ->
                cotizaciones.stream().mapToDouble(Cotizacion::getDolarBlueVenta).max().orElse(0)
        );

        CompletableFuture.allOf(promedioFuture, minimoFuture, maximoFuture).join();

        try {
            double promedio = promedioFuture.get();
            double minimo = minimoFuture.get();
            double maximo = maximoFuture.get();
            return new EstadisticasDTO(promedio, minimo, maximo);
        } catch (Exception e) {
            return new EstadisticasDTO(0, 0, 0);
        }
    }

    public VariacionDTO calcularVariacion(LocalDateTime desde, LocalDateTime hasta) {
        validarRangoFechas(desde, hasta);

        List<Cotizacion> cotizaciones = repository.findByFechaConsultaBetween(desde, hasta);

        if (cotizaciones.size() < 2) {
            throw new RuntimeException("Se necesitan al menos dos cotizaciones para calcular la variación");
        }

        cotizaciones.sort(Comparator.comparing(Cotizacion::getFechaConsulta));

        double valorInicial = cotizaciones.get(0).getDolarBlueVenta();
        double valorFinal = cotizaciones.get(cotizaciones.size() - 1).getDolarBlueVenta();

        double variacion = ((valorFinal - valorInicial) / valorInicial) * 100;

        return new VariacionDTO(valorInicial, valorFinal, variacion);
    }

    // 🛡️ Validación reutilizable
    private void validarRangoFechas(LocalDateTime desde, LocalDateTime hasta) {
        if (desde.isAfter(hasta)) {
            throw new RangoFechaInvalidoException("La fecha 'from' no puede ser posterior a 'to'");
        }
    }
}
