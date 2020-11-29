package com.example.af_poo.repositorio;

import java.util.List;

import com.example.af_poo.modelo.Cliente;

import org.springframework.stereotype.Component;

@Component
public class ClienteRepositorio {
    private int proxcodigo=1;
    List<Cliente> clientes;
    
    
    public Cliente salvar(Cliente cliente){
        cliente.setCodigo(proxcodigo++);
        clientes.add(cliente);
        return cliente;
    }
    public Cliente atualizar(Cliente cliente){
        /* Comando para atualizar
        if(aux!=null){
            //verificar os componentes
            aux.setNome(cliente.getNome());
            aux.setEndereco(cliente.getEndereco());
            //aux.setCpf(cliente.getCpf());
        }*/
    }
    public Cliente deletar(Cliente cliente){
        clientes.remove(cliente);
    }
    public List<Cliente> getClientes(){
        return clientes;
    }
    public Optional<Cliente> getClienteCodigo(int codigo){
        for(Cliente aux: clientes){
            if(aux.getCodigo()==codigo)
                return Optional.of(aux);
        }
        return Optional.empty();
    }
    
}
