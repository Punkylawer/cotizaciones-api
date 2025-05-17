package com.luciano.cotizaciones_api.dto;

public class EstadisticasDTO {

    private double promedioBlueVenta;
    private double minimoBlueVenta;
    private double maximoBlueVenta;

    public EstadisticasDTO() {}

    public EstadisticasDTO(double promedio, double minimo, double maximo) {
        this.promedioBlueVenta = promedio;
        this.minimoBlueVenta = minimo;
        this.maximoBlueVenta = maximo;
    }

    public double getPromedioBlueVenta() {
        return promedioBlueVenta;
    }

    public void setPromedioBlueVenta(double promedioBlueVenta) {
        this.promedioBlueVenta = promedioBlueVenta;
    }

    public double getMinimoBlueVenta() {
        return minimoBlueVenta;
    }

    public void setMinimoBlueVenta(double minimoBlueVenta) {
        this.minimoBlueVenta = minimoBlueVenta;
    }

    public double getMaximoBlueVenta() {
        return maximoBlueVenta;
    }

    public void setMaximoBlueVenta(double maximoBlueVenta) {
        this.maximoBlueVenta = maximoBlueVenta;
    }
}
