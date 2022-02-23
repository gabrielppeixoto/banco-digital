package com.gpspeixoto.bancodigital;

import com.gpspeixoto.bancodigital.banco.Banco;
import com.gpspeixoto.bancodigital.banco.Conta;
import com.gpspeixoto.bancodigital.enums.TipoConta;
import com.gpspeixoto.bancodigital.pessoa.Pessoa;
import com.gpspeixoto.bancodigital.pessoa.PessoaFisica;
import com.gpspeixoto.bancodigital.pessoa.PessoaJuridica;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Banco> bancoList = new ArrayList<>();
        int input;

        System.out.println("Bem-vindo ao Banco Digital.");
        do {
            System.out.println("O que deseja fazer agora?");
            System.out.println("0 - Sair");
            System.out.println("1 - Cadastrar um banco");
            System.out.println("2 - Listar os bancos disponíveis");
            System.out.println("3 - Cadastrar um cliente");
            System.out.println("4 - Listar clientes de um banco");

            input = Integer.parseInt(in.nextLine());

            switch (input)
            {
                case 0:
                    break;

                case 1:
                    cadastrarBanco(in, bancoList);
                    break;

                case 2:
                    exibirBancos(bancoList);
                    break;

                case 3:
                    cadastrarCliente(in, bancoList);
                    break;

                case 4:
                    listarClientes(in, bancoList);
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }while (input != 0);

    }

    private static void exibirBancos(List<Banco> bancoList)
    {
        bancoList.forEach(bn -> System.out.println(bn));
    }

    private static void listarClientes(Scanner in, List<Banco> bancoList) {
        int numBanco;
        if(bancoList.size() == 0)
        {
            System.out.println("Erro: lista de bancos vazia. Adicione uma agência primeiro");
            return;
        }
        System.out.println("Digite o número do banco:");
        numBanco = Integer.parseInt(in.nextLine());
        for(Banco b : bancoList)
        {
            if(b.getAgencia() == numBanco)
            {
                if(b.quantidadeClientes() == 0)
                    System.out.println("Nenhum cliente cadastrado");
                else
                    b.getClientes().forEach(cn -> System.out.println(cn));
                break;
            }
        }
    }

    private static void cadastrarCliente(Scanner in, List<Banco> bancoList) {
        int numBanco, tipoConta, tDoc;
        String nome, endereco, telefone, email, documento;
        if(bancoList.size() == 0)
        {
            System.out.println("Erro: lista de bancos vazia. Adiciona uma agência primeiro");
            return;
        }
        System.out.println("Lista de bancos:");
        exibirBancos(bancoList);
        System.out.println("Digite o número do banco onde deseja cadastrar o cliente:");
        numBanco = Integer.parseInt(in.nextLine());
        System.out.println("Digite o nome do cliente:");
        nome = in.nextLine();
        System.out.println("Digite o endereço do cliente:");
        endereco = in.nextLine();
        System.out.println("Digite o telefone do cliente:");
        telefone = in.nextLine();
        System.out.println("Digite o e-mail do cliente:");
        email = in.nextLine();
        System.out.println("Conta corrente (1) ou poupança (2)? ");
        tipoConta = Integer.parseInt(in.nextLine());
        System.out.println("CPF (1) ou CNPJ (2)? ");
        tDoc = Integer.parseInt(in.nextLine());
        System.out.println("Digite o número do documento");
        documento = in.nextLine();

        Pessoa p;
        if(tDoc == 1)
            p = new PessoaFisica(nome, endereco, telefone, email, documento);
        else
            p = new PessoaJuridica(nome, endereco, telefone, email, documento);

        Conta c;
        if(tipoConta == 1)
            c = new Conta(p, TipoConta.CORRENTE);
        else
            c = new Conta(p, TipoConta.POUPANCA);

        for(Banco bn : bancoList)
        {
            if(bn.getAgencia() == numBanco)
            {
                bn.cadastrarCliente(c);
                break;
            }
        }
    }

    private static void cadastrarBanco(Scanner in, List<Banco> bancoList) {
        String nome;
        int numero;
        System.out.println("Digite o nome do banco: ");
        nome = in.nextLine();
        System.out.println("Digite o número do banco:");
        numero = Integer.parseInt(in.nextLine());

        Banco b = new Banco(numero, nome);
        bancoList.add(b);
    }
}
