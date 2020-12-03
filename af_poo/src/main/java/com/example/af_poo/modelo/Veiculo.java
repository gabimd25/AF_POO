package com.example.af_poo.modelo;

public class Veiculo {
    private int codigo;
    private String modelo;
    private double valorDiaria;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    @Override
    public String toString() {
        return "Veiculo [codigo= " + codigo + ", modelo= " + modelo + ", valorDiaria= " + valorDiaria+" ]";
    }

	
}

