package com.example.af_poo.modelo;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

public class Reserva {

        private int codigo;
        @NotNull
        private Cliente cliente;
        @NotNull
        private Veiculo veiculo;
        LocalDate inicio;
        LocalDate fim;
        private double totalReserva;

        public Reserva() { 
        }        
        public Reserva(int codigo, Cliente cliente, Veiculo veiculo, LocalDate inicio, LocalDate fim, double totalReserva) {
            this.codigo = codigo;
            this.cliente = cliente;
            this.veiculo = veiculo;
            this.inicio = inicio;
            this.fim = fim;
            this.totalReserva = totalReserva;
        }
        public int getCodigo() {
            return codigo;
        }

        public void setCodigo(int codigo){
            this.codigo=codigo;
        }

        public Cliente getCliente() {
            return cliente;
        }

        public void setCliente(Cliente cliente) {
            this.cliente = cliente;
        }

        public Veiculo getVeiculo() {
            return veiculo;
        }  

        public void setVeiculo(Veiculo veiculo) {
            this.veiculo = veiculo;
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

        public double getTotalReserva() {
            return totalReserva;
        }
        
        public void setTotalReserva(double totalReserva) {
            this.totalReserva = totalReserva;
        }
    }

