package com.luciano.cotizaciones_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "cotizaciones")
public class Cotizacion {

    @Id
    private String id;

    private LocalDateTime fechaConsulta;

    private double dolarBlueCompra;
    private double dolarBlueVenta;

    private double dolarOficialCompra;
    private double dolarOficialVenta;

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(LocalDateTime fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public double getDolarBlueCompra() {
        return dolarBlueCompra;
    }

    public void setDolarBlueCompra(double dolarBlueCompra) {
        this.dolarBlueCompra = dolarBlueCompra;
    }

    public double getDolarBlueVenta() {
        return dolarBlueVenta;
    }

    public void setDolarBlueVenta(double dolarBlueVenta) {
        this.dolarBlueVenta = dolarBlueVenta;
    }

    public double getDolarOficialCompra() {
        return dolarOficialCompra;
    }

    public void setDolarOficialCompra(double dolarOficialCompra) {
        this.dolarOficialCompra = dolarOficialCompra;
    }

    public double getDolarOficialVenta() {
        return dolarOficialVenta;
    }

    public void setDolarOficialVenta(double dolarOficialVenta) {
        this.dolarOficialVenta = dolarOficialVenta;
    }
}
