package com.luciano.cotizaciones_api.dto;

public class VariacionDTO {

    private double valorInicial;
    private double valorFinal;
    private double variacionPorcentual;

    public VariacionDTO() {}

    public VariacionDTO(double valorInicial, double valorFinal, double variacionPorcentual) {
        this.valorInicial = valorInicial;
        this.valorFinal = valorFinal;
        this.variacionPorcentual = variacionPorcentual;
    }

    public double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public double getVariacionPorcentual() {
        return variacionPorcentual;
    }

    public void setVariacionPorcentual(double variacionPorcentual) {
        this.variacionPorcentual = variacionPorcentual;
    }
}
