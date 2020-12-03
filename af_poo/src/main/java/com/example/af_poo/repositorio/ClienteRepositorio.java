package com.example.af_poo.repositorio;

import java.util.ArrayList;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.example.af_poo.modelo.Cliente;

import org.springframework.stereotype.Component;

@Component
public class ClienteRepositorio {
    private int proxcodigo=1;
    ArrayList<Cliente> clientes;
    
    @PostConstruct
    public void criarClientes() {
        Cliente c1 = new Cliente();
        Cliente c2 = new Cliente();

        c1.setCodigo(1);
        c1.setNome("João");
        c1.setEndereco("Rua Maria do Bairro, 157");
        c1.getCpf();

        c2.setCodigo(2);
        c2.setNome("Maria");
        c2.setEndereco("Rua Y, 222");
        c2.getCpf();

        clientes = new ArrayList<Cliente>();
        clientes.add(c1);
        clientes.add(c2);

        proxcodigo = 3;

    }

    public Cliente salvar(Cliente cliente){
        cliente.setCodigo(proxcodigo++);
        clientes.add(cliente);
        return cliente;
    }
    public Cliente atualizar(Cliente cliente){
        Cliente aux = getClienteCodigo(cliente.getCodigo()).get();
        if(aux!=null){
            aux.setNome(cliente.getNome());
            aux.setEndereco(cliente.getEndereco());
        }
        return aux;
    }
    public void deletar(Cliente cliente){
        clientes.remove(cliente);
    }
    public ArrayList<Cliente> getClientes(){
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
