package com.example.af_poo.controlador;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.af_poo.dto.ClienteDTO;
import com.example.af_poo.modelo.Cliente;
import com.example.af_poo.modelo.Reserva;
import com.example.af_poo.servico.ClienteServico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/clientes")
public class ClienteControlador{
    @Autowired
    private ClienteServico clienteServico;

    @GetMapping
    public List<Cliente> getClientes(){
        return clienteServico.getClientes();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Cliente> getClienteCodigo(@PathVariable int codigo) {
        Cliente cliente = clienteServico.getClienteCodigo(codigo);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping()
    public ResponseEntity<Cliente> salvarCliente(@Valid @RequestBody ClienteDTO clienteDTO, HttpServletRequest request,
            UriComponentsBuilder builder
    ) {
        Cliente cliente = clienteServico.fromDTO(clienteDTO);
        Cliente novoCliente = clienteServico.salvarCliente(cliente);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + novoCliente.getCodigo()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Cliente> atualizarCliente(@RequestBody ClienteDTO clienteDTO, @PathVariable int codigo) {
        Cliente aux = clienteServico.getClienteCodigo(codigo);
        if(aux != null){
            Cliente cliente = clienteServico.fromDTO(clienteDTO);
            cliente.setCodigo(codigo);
            clienteServico.atualizarCliente(cliente);
            return ResponseEntity.ok(cliente);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletarCliente(@PathVariable int codigo){
        clienteServico.deletarCliente(codigo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{codigo}/reservas")
    public List<Reserva> getReservasCliente(@PathVariable int codigo){
        return clienteServico.getReservasCliente(codigo);
    }
    
}