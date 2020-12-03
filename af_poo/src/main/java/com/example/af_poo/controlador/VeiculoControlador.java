package com.example.af_poo.controlador;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.af_poo.dto.VeiculoDTO;
import com.example.af_poo.modelo.Reserva;
import com.example.af_poo.modelo.Veiculo;
import com.example.af_poo.servico.VeiculoServico;

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
@RequestMapping("/veiculos")
public class VeiculoControlador {

    @Autowired
    private VeiculoServico veiculoServico;

    @GetMapping
    public List<Veiculo> getVeiculos(){
        return veiculoServico.getVeiculos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Veiculo> getVeiculoCodigo(@PathVariable int codigo) {
        Veiculo veiculo = veiculoServico.getVeiculoCodigo(codigo);
        return ResponseEntity.ok(veiculo);	
    }

    @PostMapping
    public ResponseEntity<Veiculo> salvarVeiculo(@Valid @RequestBody VeiculoDTO veiculoDTO,
            HttpServletRequest request, UriComponentsBuilder builder
    ){
        Veiculo veiculo = veiculoServico.fromDTO(veiculoDTO);
        Veiculo novoVeiculo = veiculoServico.salvarVeiculo(veiculo);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + novoVeiculo.getCodigo()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Veiculo> atualizarVeiculo(@RequestBody VeiculoDTO veiculoDTO, @PathVariable int codigo) {
        Veiculo aux = veiculoServico.getVeiculoCodigo(codigo);
        if(aux != null){
            Veiculo veiculo = veiculoServico.fromDTO(veiculoDTO);
            veiculo.setCodigo(codigo);
            veiculoServico.atualizarVeiculo(veiculo);
            return ResponseEntity.ok(veiculo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable int codigo){
        veiculoServico.deletarVeiculo(codigo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{codigo}/reservas")
    public List<Reserva> getReservasVeiculo(@PathVariable int codigo){
        return veiculoServico.getReservasVeiculo(codigo);
    }
}
