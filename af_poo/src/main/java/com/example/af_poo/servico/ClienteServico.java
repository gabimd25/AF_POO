package com.example.af_poo.servico;

import java.util.List;
import java.util.Optional;

import com.example.af_poo.dto.ClienteDTO;
import com.example.af_poo.modelo.Cliente;
import com.example.af_poo.modelo.Reserva;
import com.example.af_poo.repositorio.ClienteRepositorio;
import com.example.af_poo.repositorio.ReservaRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteServico {
    @Autowired
    private ClienteRepositorio repositorioCliente;

    @Autowired
    private ReservaRepositorio repositorioReserva;

    public Cliente fromDTO(ClienteDTO dto){
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEndereco(dto.getEndereco());
        cliente.setCpf(dto.getCPF());
        return cliente;
    }

    public Cliente salvarCliente(Cliente cliente){
        return repositorioCliente.salvar(cliente);
    }

    public List<Cliente> getClientes(){
        return repositorioCliente.getClientes();
    }

    public Cliente getClienteCodigo(int codigo){
        Optional<Cliente> op = repositorioCliente.getClienteCodigo(codigo);
        return op.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado!"));
    }

    public Cliente atualizarCliente(Cliente cliente){
        return repositorioCliente.atualizar(cliente);
    }

    public void deletarCliente(int codigo){
        repositorioCliente.deletar(getClienteCodigo(codigo));
    }

	public List<Reserva> getReservasCliente(int codigo) {
		return repositorioReserva.getClienteReservas(codigo);
	}
	

}
