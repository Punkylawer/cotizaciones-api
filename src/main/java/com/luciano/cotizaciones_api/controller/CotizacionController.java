package com.luciano.cotizaciones_api.controller;

import com.luciano.cotizaciones_api.dto.EstadisticasDTO;
import com.luciano.cotizaciones_api.dto.VariacionDTO;
import com.luciano.cotizaciones_api.model.Cotizacion;
import com.luciano.cotizaciones_api.service.CotizacionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/cotizaciones")
public class CotizacionController {

    private final CotizacionService service;

    public CotizacionController(CotizacionService service) {
        this.service = service;
    }

    //GET obtener la cotización del día
    @GetMapping("/hoy")
    public Cotizacion obtenerCotizacionDelDia() {
        return service.obtenerCotizacionDelDia();
    }


    // GET paginado y ordenado por defecto
    @GetMapping
    public Page<Cotizacion> obtenerPaginado(Pageable pageable) {
        return service.obtenerPaginado(pageable);
    }

    // GET sin paginación (opcional, explícito)
    @GetMapping("/todas")
    public List<Cotizacion> obtenerTodas() {
        return service.obtenerTodas();
    }

    // Filtro por fecha
    @GetMapping("/filtrar")
    public List<Cotizacion> obtenerEntreFechas(@RequestParam("from") String desdeStr,
                                               @RequestParam("to") String hastaStr) {
        LocalDateTime desde = LocalDateTime.parse(desdeStr);
        LocalDateTime hasta = LocalDateTime.parse(hastaStr);
        return service.obtenerEntreFechas(desde, hasta);
    }

    // Estadísticas: promedio, min, max
    @GetMapping("/estadisticas")
    public EstadisticasDTO obtenerEstadisticas(@RequestParam("from") String desdeStr,
                                               @RequestParam("to") String hastaStr) {
        LocalDateTime desde = LocalDateTime.parse(desdeStr);
        LocalDateTime hasta = LocalDateTime.parse(hastaStr);
        return service.calcularEstadisticasEntreFechas(desde, hasta);
    }

    // Variación porcentual
    @GetMapping("/variacion")
    public VariacionDTO obtenerVariacion(@RequestParam("from") String desdeStr,
                                         @RequestParam("to") String hastaStr) {
        LocalDateTime desde = LocalDateTime.parse(desdeStr);
        LocalDateTime hasta = LocalDateTime.parse(hastaStr);
        return service.calcularVariacion(desde, hasta);
    }
}
