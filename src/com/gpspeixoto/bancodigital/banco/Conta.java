package com.gpspeixoto.bancodigital.banco;

import com.gpspeixoto.bancodigital.enums.TipoConta;
import com.gpspeixoto.bancodigital.pessoa.Pessoa;

import java.util.UUID;

public class Conta {
    private Pessoa titular;
    private UUID númeroConta;
    private TipoConta tipoConta;
    private double saldo;
    public double limite;

    public Conta(Pessoa titular, TipoConta tipoConta) {
        this.titular = titular;
        this.númeroConta = UUID.randomUUID();
        this.tipoConta = tipoConta;
        this.saldo = 0.0;
        if(tipoConta == TipoConta.CORRENTE)
            this.limite = 5000.00;
        else
            this.limite = 0.0;
    }

    public Pessoa getTitular() {
        return titular;
    }

    public void setTitular(Pessoa titular) {
        this.titular = titular;
    }

    public int getNúmeroConta() {
        return númeroConta.clockSequence();
    }

    public void setNúmeroConta(UUID númeroConta) {
        this.númeroConta = númeroConta;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public void depositar(double valor)
    {
        this.saldo += valor;
    }

    private boolean verificaSeHaSaldo(double valor)
    {
        if(this.saldo >= valor)
            return true;
        else
            return false;
    }

    public void sacar(double valor)
    {
        if(!verificaSeHaSaldo(valor))
        {
            System.out.println("Erro: saldo inferior a " + valor);
            return;
        }
        else
        {
            this.saldo -= valor;
        }
    }

    public void transferir(Conta dest, double valor)
    {
        if(verificaSeHaSaldo(valor))
        {
            sacar(valor);
            dest.depositar(valor);
        }
        else
        {
            System.out.println("Erro: saldo inferior a " + valor);
            return;
        }
    }

    @Override
    public String toString() {
        return "Conta {" +
                "titular=" + titular +
                ", númeroConta=" + númeroConta +
                ", tipoConta=" + tipoConta +
                '}';
    }
}
