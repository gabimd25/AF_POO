package com.example.af_poo.servico;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;

import com.example.af_poo.dto.ReservaDTO;
import com.example.af_poo.modelo.Cliente;
import com.example.af_poo.modelo.Reserva;
import com.example.af_poo.modelo.Veiculo;
import com.example.af_poo.repositorio.ReservaRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReservaServico {
    @Autowired
    private ReservaRepositorio repositorioReserva;
    @Autowired
    private ClienteServico servicoCliente;
    @Autowired
    private VeiculoServico servicoVeiculo;

    public Reserva getReservaCodigo(int codigo){
        Optional<Reserva> op = repositorioReserva.getReservaCodigo(codigo);
        return op.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Reserva não encontrado!"));
    }

	public Reserva salvarReserva(Reserva reserva) {
        boolean inicioHoje = reserva.getInicio().isBefore(LocalDate.now());
        boolean domingoInicio = reserva.getInicio().getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean domingoFim = reserva.getFim().getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean fimInicio = reserva.getFim().isBefore(reserva.getInicio());
        boolean veiculoIndisponivel = repositorioReserva.veiculoIndisponivel(reserva.getVeiculo(),reserva.getInicio(), reserva.getFim());
        System.out.println("booleans: "+inicioHoje+domingoInicio+domingoFim+fimInicio+veiculoIndisponivel);
        if(!inicioHoje && !domingoInicio && !domingoFim && !fimInicio && !veiculoIndisponivel){
            return repositorioReserva.salvar(reserva);
        }        
        else{
            return null;
        }
    }
    
    public void deletarReserva(int codigo){
        repositorioReserva.deletar(getReservaCodigo(codigo));
    }

    public Reserva fromDTO(ReservaDTO dto){
        Reserva reserva = new Reserva();
        Cliente cliente = servicoCliente.getClienteCodigo(dto.getCodigoCliente());
        Veiculo veiculo = servicoVeiculo.getVeiculoCodigo(dto.getCodigoVeiculo());
        reserva.setCliente(cliente);
        reserva.setVeiculo(veiculo);
        reserva.setInicio(dto.getInicio());
        reserva.setFim(dto.getFim());
        reserva.setTotalReserva(dto.getFim().compareTo(dto.getInicio())*reserva.getVeiculo().getValorDiaria());
        return reserva;
    }
}
