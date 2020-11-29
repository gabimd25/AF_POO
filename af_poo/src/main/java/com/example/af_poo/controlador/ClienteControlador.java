package com.example.af_poo.controlador;

import com.example.af_poo.modelo.Cliente;
import com.example.af_poo.servico.ClienteServico;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteControlador{
    @Autowired
    private ClienteServico servico;

    @GetMapping
    public ResponseEntity salvarCliente(@RequestBody Cliente cliente, HttpServletRequest request,
     UriComponentsBuilder builder){
        Cliente novoCliente = servico.salvarCliente();
        UriComponents uriComponents = builder.path(request.getRequestURI()+"/"+ novoCliente.getCodigo()).build;
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping
    public List<Cliente> getClientes(){
        return servico.getClientes();
    }    

    @GetMapping("/{codigo}")
    public Cliente getClienteCodigo(@PathVariable int codigo){
        Cliente cliente = servico.getClienteCodigo(codigo);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{codigo}")
    public String atualizarCliente(@RequestBody Cliente cliente, @PathVariable int codigo){
        //padrão dto
        Cliente aux = servico.getClienteCodigo(codigo);
        if(aux != null){
            cliente.setCodigo(codigo);
            servico.atualizarCliente(cliente);
            return ResponseEntity.ok(cliente);
        }
        return ResponseEntity().NOT_FOUND.build();
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletarCliente(@PathVariable int codigo){
        //Cliente cliente 
        return ResponseEntity.noContent().build();
    }
}