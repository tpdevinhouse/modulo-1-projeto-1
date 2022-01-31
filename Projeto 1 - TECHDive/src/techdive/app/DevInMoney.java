package techdive.app;

import techdive.utils.conta.Conta;
import techdive.utils.investimento.Investimento;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DevInMoney {

    public static void main(String[] args) {

        Conta conta = new Conta();

        // Incia “loop” da aplicação
        boolean inicia = true;

        // Entrada do console
        Scanner entrada = new Scanner(System.in);
        Scanner ag = new Scanner(System.in);
        Scanner alteraNome = new Scanner(System.in);
        Scanner alteraRenda = new Scanner(System.in);
        Scanner alteraAg = new Scanner(System.in);

        // Interação com o usuário
        System.out.println("\nSeja bem vindo(a) a DevInMoney $");
        System.out.println("\nPara acessar a plataforma, por gentileza realizar o seu cadastro a seguir:");

        try {

            // Cadastro de usuário → Start
            System.out.print("\nDigite seu nome completo: ");
            String nomeCompleto = entrada.nextLine();
            conta.setNome(nomeCompleto);

            System.out.print("Digite seu CPF com pontuação: ");
            String cpf = entrada.next();
            conta.setCPF(cpf);

            System.out.print("Digite sua renda mensal: R$ ");
            double rendaMensal = entrada.nextDouble();
            conta.setRendaMensal(rendaMensal);

            System.out.println("Qual agência você deseja abrir a sua conta?");
            System.out.print("1 - FLORIANOPOLIS | 2 - SA0_JOSE : ");
            // Cadastro de usuário → End

            int tipoAgencia = entrada.nextInt();

            if (tipoAgencia == 1) {
                System.out.println("Você selecionou a agência " + conta.agencia(conta, tipoAgencia));
            } else if (tipoAgencia == 2) {
                System.out.println("Você selecionou a agência " + conta.agencia(conta, tipoAgencia));
            }

            // ID Sequencial
            conta.idSequencial(conta);

            System.out.println("Seu número de conta é " + "AG0" + tipoAgencia + "-0" + conta.getConta());


            while (inicia) {
                menu:
                System.out.println("\nOlá " + conta.getNome() + ", bem vindo ao menu do DevInMoney!");
                System.out.println("Escolhe uma das opções abaixo:");

                menu:
                if (true) {

                    System.out.println(
                            "1 - Déposito" +
                                    " | 2 - Saldo" +
                                    " | 3 - Saque" +
                                    " | 4 - Transferir" +
                                    " | 5 - Investimentos" +
                                    "\n6 - Extrato Bancário" +
                                    " | 7 - Alterar dados cadastrais" +
                                    " | 8 - Sair"
                    );
                    System.out.println();
                    System.out.print("\nDigite o número do menu para prosseguir: ");
                    int escolhaMenu = entrada.nextInt();

                    // Decidir qual menu o usuário irar acessar

                    // Menu 1 Depósito
                    if (escolhaMenu == 1) {

                        System.out.println("Você escolheu o menu de depósito");
                        System.out.print("Digite o valor a ser depósitado: R$ ");
                        Double valorDeposito = entrada.nextDouble();
                        System.out.println("Valor depósitado R$ " + valorDeposito);
                        conta.depositar(conta, valorDeposito);
                        System.out.println("Depósito realizada com sucesso!");
                        conta.saldo();
                        break menu;

                    }

                    // AQUI O Metodo de saldo não funciona
                    // Menu 2 Saldo
                    if (escolhaMenu == 2) {

                        System.out.println("Você escolheu o menu de saldo");
                        conta.saldo();
                        conta.chequeEspecial(conta, rendaMensal);
                        break menu;

                    }

                    // Menu 3 Saque
                    if (escolhaMenu == 3) {

                        System.out.println("Você escolheu o menu de saque");
                        System.out.print("Digite o valor a ser sacado: R$ ");
                        double valorSaque = entrada.nextDouble();
                        System.out.println("Valor sacado R$ " + valorSaque);
                        conta.saque(conta, valorSaque);
                        System.out.println("Saque realizado com sucesso!");
                        conta.saldo();
                        break menu;

                    }

                    // Menu 4 Transferir
                    if (escolhaMenu == 4) {

                        System.out.println("Você escolheu o menu de transferência");
                        System.out.print("1 - PIX | 2 - TED: ");
                        int escolhaTransfer = entrada.nextInt();

                        if (escolhaTransfer == 1) {
                            conta.transferencia(conta, escolhaTransfer);
                            break menu;
                        } else if (escolhaTransfer == 2) {
                            conta.transferencia(conta, escolhaTransfer);
                            break menu;
                        }

                    }

                    // Menu 5 Investimento
                    if (escolhaMenu == 5) {

                        System.out.println("Você escolheu o menu de Investimento");
                        System.out.print(
                                "1 - Poupança" +
                                        " | 2 - Fundos Imobiliários" +
                                        " | 3 - Cripto" +
                                        " | 4 - Bolsa de Valores: "
                        );

                        int escolheInvest = entrada.nextInt();

                        if (escolheInvest == 1) {
                            conta.investPoupanca(conta);
                        } else if (escolheInvest == 2) {
                            conta.investBolsaValores(conta);
                        } else if (escolheInvest == 3) {
                            conta.investCripto(conta);
                        } else if (escolheInvest == 4) {
                            conta.investBolsaValores(conta);
                        }
                        break menu;

                    }

                    // Menu 6 Extrato Bancário
                    if (escolhaMenu == 6) {
                        System.out.println("Você escolheu o menu Extrato Bancário");
                        String[] contaExtrato = conta.extrato(conta);
                        for (int i = 0; i < contaExtrato.length; i++) {
                            System.out.println(contaExtrato[i]);
                        }
                        break menu;
                    }

                    // Menu 7 Altera Dados Cadastrais
                    if (escolhaMenu == 7) {
                        System.out.println("Você escolheu o menu Altera Dados Cadastrais");
                        System.out.print(
                                "1 - Alterar nome" +
                                        " | 2 - Alterar renda" +
                                        " | 3 - Alterar agência: "
                        );

                        int escolheAlteracao = entrada.nextInt();

                        if (escolheAlteracao == 1) {
                            System.out.print("Digite seu novo nome: ");
                            String novoNome = alteraNome.nextLine();
                            conta.alteraNome(conta, novoNome);
                            System.out.println("Nome alterado para: " + conta.getNome());
                            break menu;
                        } else if (escolheAlteracao == 2) {
                            System.out.print("Digite sua nova renda: ");
                            double novaRenda = alteraRenda.nextDouble();
                            conta.alteraRenda(conta, novaRenda);
                            System.out.println("Renda alterada para: " + conta.getRendaMensal());
                            break menu;
                        } else if (escolheAlteracao == 3) {
                            System.out.println("Você escolheu alteração de agência");
                            System.out.print(
                                    "1 - FLORIANOPOLIS" +
                                            " | 2 - SAO_JOSE: "
                            );

                            int qualAg = ag.nextInt();

                            if (qualAg == 1) {
                                System.out.println("Você selecionou a agência FLORIANOPOLIS");
                                conta.agencia(conta, qualAg);
                                break menu;
                            } else if (qualAg == 2) {
                                System.out.println("Você selecionou a agência SAO_JOSE");
                                conta.agencia(conta, qualAg);
                                break menu;
                            }

                        }

                    }

                    // Menu 8 Sair
                    if (escolhaMenu == 8) {
                        System.out.println("Obrigado por usar o DevInMoney, até breve!");
                        break;
                    }


                }


            }


        } catch (Exception e) {
            System.out.println("Digite os dados corretamente");
        } finally {
            entrada.close();
            ag.close();
            alteraNome.close();
            alteraRenda.close();
            alteraAg.close();
        }
    }

}
