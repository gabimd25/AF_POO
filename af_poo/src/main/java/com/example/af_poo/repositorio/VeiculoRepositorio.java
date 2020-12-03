package com.example.af_poo.repositorio;

import java.util.ArrayList;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.example.af_poo.modelo.Veiculo;

import org.springframework.stereotype.Component;

@Component
public class VeiculoRepositorio {

	private ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
	private ArrayList<Veiculo> veiculosDisponiveis = new ArrayList<Veiculo>();
    private static int nextNumero = 1;

	@PostConstruct
    public void criarVeiculos() {
        Veiculo v1 = new Veiculo();
        Veiculo v2 = new Veiculo();

        v1.setCodigo(1);
        v1.setModelo("Fusca 2.0");
        v1.setValorDiaria(120.00);

        v2.setCodigo(2);
        v2.setModelo("Hilux 2.0");
        v2.setValorDiaria(180.00);

        veiculos.add(v1);
        veiculos.add(v2);

        nextNumero = 3;
    }

    public Veiculo salvar(Veiculo veiculo){
        veiculo.setCodigo(nextNumero++);
        veiculos.add(veiculo);
        return veiculo;
    }
    public Veiculo atualizar(Veiculo veiculo){
        Veiculo aux = getVeiculoCodigo(veiculo.getCodigo()).get();
        if(aux!=null){
            aux.setValorDiaria(veiculo.getValorDiaria());
        }
        return aux;
    }
    public void deletar(Veiculo veiculo){
        veiculos.remove(veiculo);
    }
    public ArrayList<Veiculo> getVeiculos(){
        return veiculos;
    }
    public Optional<Veiculo> getVeiculoCodigo(int codigo){
        for(Veiculo aux: veiculos){
            if(aux.getCodigo()==codigo)
                return Optional.of(aux);
        }
        return Optional.empty();
    }

    public ArrayList<Veiculo> getVeiculosDisponiveis() {
        return veiculosDisponiveis;
    }

    public void setVeiculosDisponiveis(ArrayList<Veiculo> veiculosDisponiveis) {
        this.veiculosDisponiveis = veiculosDisponiveis;
    }
}
