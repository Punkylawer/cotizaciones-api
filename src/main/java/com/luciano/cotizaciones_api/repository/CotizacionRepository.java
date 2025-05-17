package com.luciano.cotizaciones_api.repository;



import com.luciano.cotizaciones_api.model.Cotizacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CotizacionRepository extends MongoRepository<Cotizacion, String> {

    List<Cotizacion> findByFechaConsultaBetween(LocalDateTime desde, LocalDateTime hasta);
    List<Cotizacion> findByFechaConsultaBetweenOrderByFechaConsultaDesc(LocalDateTime desde, LocalDateTime hasta);


}
