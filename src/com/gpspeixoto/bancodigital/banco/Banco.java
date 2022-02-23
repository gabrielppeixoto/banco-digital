package com.gpspeixoto.bancodigital.banco;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private Integer agencia;
    private String nome;
    private List<Conta> clientes;

    public Banco(Integer a, String nome)
    {
        this.agencia = a;
        this.nome = nome;
        this.clientes = new ArrayList<>();
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void cadastrarCliente(Conta c)
    {
        clientes.add(c);
    }

    public void deletarCliente(Conta c)
    {
        clientes.remove(c);
    }

    public List<Conta> getClientes() {
        return clientes;
    }

    public int quantidadeClientes()
    {
        return this.clientes.size();
    }

    public void setClientes(List<Conta> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        return "Banco {" +
                "agencia=" + agencia +
                ", nome='" + nome + '\'' +
                '}';
    }
}
