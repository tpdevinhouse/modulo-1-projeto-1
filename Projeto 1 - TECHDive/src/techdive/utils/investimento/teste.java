package techdive.utils.investimento;

import techdive.utils.conta.Conta;

import java.util.Scanner;

public class teste {

    public static void main(String[] args) {

        Conta conta = new Conta();
        Scanner entrada = new Scanner(System.in);

        while (true) {
            menu:
            if(true) {
                System.out.print("\nDigite o número do menu para prosseguir: ");
                int escolhaMenu = entrada.nextInt();



                // Menu 1 Depósito
                if (escolhaMenu == 1) {

                    System.out.println("Você escolheu o menu de depósito");
                    System.out.print("Digite o valor a ser depósitado: R$ ");
                    Double valorDeposito = entrada.nextDouble();
                    System.out.println("Valor depósitado R$ " + valorDeposito);
                    conta.depositar(conta, valorDeposito);
                    conta.setSaldo(valorDeposito);
                    System.out.println("Depósito realizada com sucesso!");
                    conta.saldo(conta);
                    break menu;

                }

                // AQUI O Metodo de saldo funciona
                // Menu 2 Saldo
                if (escolhaMenu == 2) {

                    System.out.println("Você escolheu o menu de saldo");
                    conta.saldo(conta);
                    break menu;

                }
            }
        }


    }

}
