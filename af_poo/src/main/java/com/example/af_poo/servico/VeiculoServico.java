package com.example.af_poo.servico;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.af_poo.dto.VeiculoDTO;
import com.example.af_poo.modelo.Veiculo;
import com.example.af_poo.modelo.Reserva;
import com.example.af_poo.repositorio.ReservaRepositorio;
import com.example.af_poo.repositorio.VeiculoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VeiculoServico {
    @Autowired
    private VeiculoRepositorio repositorioVeiculo;

    @Autowired
    private ReservaRepositorio repositorioReserva;
	
	public ArrayList<Veiculo> getVeiculos(){
        return repositorioVeiculo.getVeiculos();
    }

    public Veiculo getVeiculoCodigo(int codigo){
        Optional<Veiculo> op = repositorioVeiculo.getVeiculoCodigo(codigo);
        return op.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado!"));
	}
	
	public Veiculo salvarVeiculo(Veiculo veiculo) {
        return repositorioVeiculo.salvar(veiculo);
	}

	public Veiculo atualizarVeiculo(Veiculo veiculo) {
        return repositorioVeiculo.atualizar(veiculo);
	}
    public Veiculo fromDTO(VeiculoDTO veiculoDTO){
        Veiculo veiculo = new Veiculo();

        veiculo.setModelo(veiculoDTO.getModelo());
        veiculo.setValorDiaria(veiculoDTO.getValorDiaria());
        return veiculo;
    }
    public void deletarVeiculo(int codigo){
        repositorioVeiculo.deletar(getVeiculoCodigo(codigo));
    }

	public List<Reserva> getReservasVeiculo(int codigo) {
		return repositorioReserva.getVeiculoReservas(codigo);
	}
}
