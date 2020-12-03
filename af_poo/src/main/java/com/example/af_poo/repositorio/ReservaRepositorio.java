package com.example.af_poo.repositorio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import com.example.af_poo.modelo.Reserva;
import com.example.af_poo.modelo.Veiculo;

import org.springframework.stereotype.Component;

@Component
public class ReservaRepositorio {
    private int proxcodigo=1;
    ArrayList<Reserva> reservas = new ArrayList<Reserva>();

    public Optional<Reserva> getReservaCodigo(int codigo){
        for(Reserva aux: reservas){
            if(aux.getCodigo()==codigo)
                return Optional.of(aux);
        }
        return Optional.empty();
    }
    public Reserva salvar(Reserva reserva){
        reserva.setCodigo(proxcodigo++);
        reservas.add(reserva);
        return reserva;
    }
    public void deletar(Reserva reserva){
        reservas.remove(reserva);
    }
    public ArrayList<Reserva> getClienteReservas(int codigo){
        ArrayList<Reserva> lista = new ArrayList<Reserva>();
        for(Reserva aux:reservas){
            if(aux.getCliente().getCodigo()==codigo)
            lista.add(aux);
        }
        return lista;
    }
    public ArrayList<Reserva> getVeiculoReservas(int codigo){
        ArrayList<Reserva> lista = new ArrayList<Reserva>();
        for(Reserva aux:reservas){
            if(aux.getVeiculo().getCodigo()==codigo)
            lista.add(aux);
        }
        return lista;
    }
    public boolean veiculoIndisponivel(Veiculo veiculo, LocalDate inicio, LocalDate fim){
        for(Reserva aux: reservas){
            if(aux.getVeiculo().getCodigo() == veiculo.getCodigo())
                if(inicio.isBefore(aux.getFim()) || fim.isAfter(aux.getInicio()) )
                    return true;
        }
        return false;
    }
}
