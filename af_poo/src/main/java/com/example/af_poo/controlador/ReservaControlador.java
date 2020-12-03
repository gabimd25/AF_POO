package com.example.af_poo.controlador;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.af_poo.dto.ReservaDTO;
import com.example.af_poo.modelo.Reserva;
import com.example.af_poo.servico.ReservaServico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/reservas")
public class ReservaControlador {

    @Autowired
    private ReservaServico reservaServico;

    @PostMapping
    public ResponseEntity<Reserva> salvarReserva(@Valid @RequestBody ReservaDTO reservaDTO,
            HttpServletRequest request, UriComponentsBuilder builder
    ){
        Reserva reserva = reservaServico.fromDTO(reservaDTO);
        Reserva novaReserva = reservaServico.salvarReserva(reserva);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + novaReserva.getCodigo()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Reserva> getReservaCodigo(@PathVariable int codigo) {
        Reserva reserva = reservaServico.getReservaCodigo(codigo);
        return ResponseEntity.ok(reserva);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable int codigo){
        reservaServico.deletarReserva(codigo);
        return ResponseEntity.noContent().build();
    }
}
