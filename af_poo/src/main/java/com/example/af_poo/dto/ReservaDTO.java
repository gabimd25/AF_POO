package com.example.af_poo.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;


public class ReservaDTO {
    @NotNull
    private int codigoCliente;
    @NotNull
    private int codigoVeiculo;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate inicio;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate fim;

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public int getCodigoVeiculo() {
        return codigoVeiculo;
    }

    public void setCodigoVeiculo(int codigoVeiculo) {
        this.codigoVeiculo = codigoVeiculo;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }
}
