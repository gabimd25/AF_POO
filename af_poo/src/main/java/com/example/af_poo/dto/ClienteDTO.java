package com.example.af_poo.dto;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public class ClienteDTO {

    @NotBlank(message = "Insira seu nome.")
    @Length(min=4,max=40, message = "O nome deve apresentar de 4 a 40 caracteres.")
    private String nome;
    
    @NotBlank(message = "Insira seu endereço.")
    @Length(min=10,max=80, message = "Endereço deve apresentar de 10 a 80 caracteres.")
    private String endereco;

    @NotBlank(message = "Insira seu CPF.")
    @CPF(message = "O CPF deve apresentar 11 caracteres.")
    private String cpf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }
}
