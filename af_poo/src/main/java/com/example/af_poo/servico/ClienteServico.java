package com.example.af_poo.servico;

import java.util.Optional;

import com.example.af_poo.dto.ClienteDTO;
import com.example.af_poo.modelo.Cliente;
import com.example.af_poo.repositorio.ClienteRepositorio;

import org.springframework.stereotype.Service;

@Service
public class ClienteServico {
    @Autowired
    private ClienteRepositorio repositorio;

    public Cliente fromDTO(ClienteDTO dto){
        Cliente cliente = new Cliente();
        //cliente.setNome(dto.getNome());
        //cliente.setEndereco(dto.getEndereco());
        return cliente;
    }

    public Cliente salvarCliente(){
        return repositorio.salvar(cliente);
    }

    public Cliente getClientes(){
        return repositorio.getClientes();
    }

    public Cliente getClienteCodigo(int codigo){
        Optional<Cliente> op = repositorio.getClienteCodigo(codigo);
        return op.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado!"));
    }

    public Cliente atualizarCliente(Cliente cliente){
        return repositorio.atualizar(cliente);
    }

    public Cliente deletarCliente(Cliente cliente){
        return repositorio.deletar(cliente);
    }

}
