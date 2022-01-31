package techdive.utils.investimento;

import techdive.utils.conta.Conta;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Poupanca {
    private double valorInvestimento;
    private final double juros = 0.10;

    /////////// GETTERS E SETTERS ///////////

    public double getValorInvestimento() {
        return valorInvestimento;
    }

    public void setValorInvestimento(double valorInvestimento) {
        this.valorInvestimento = valorInvestimento;
    }

    public double getJuros() {
        return juros;
    }

    /////////// MÉTODOS ///////////

    // Método Rentabilidade Poupança
    public void rentabilidadePoupanca(Conta poupanca, double valor, double juros) {
        double valorRentavel = valor * juros;
        poupanca.setValorInvestimento(poupanca.getValorInvestimento() + valorRentavel);
    }

    public void investPoupanca(Conta investPoupanca) {
        Scanner entradaInvestPoupanca = new Scanner(System.in);
        Scanner entradaTempoInvestPoupanca = new Scanner(System.in);
        double valorPoupanca;
        int tempoPoupanca;
        int j = 0;

        System.out.println("Você escolheu o investimento Poupança");
        investPoupanca.saldo();
        System.out.print("Digite o valor a ser investido na Poupança: R$  ");
        valorPoupanca = entradaInvestPoupanca.nextDouble();
        investPoupanca.saque(investPoupanca, valorPoupanca);
        System.out.print("Por quanto meses você deseja investir?: ");
        tempoPoupanca = entradaTempoInvestPoupanca.nextInt();

        if(tempoPoupanca <= 5) {
            for(int i = 0; i < tempoPoupanca; i++) {
                j++;
                investPoupanca.rentabilidadeCripto(investPoupanca, valorPoupanca, juros);
                System.out.println("Rentabilidade do Mês - " + j + ": R$ " +
                        new DecimalFormat("#,##0.##").format(investPoupanca.getValorInvestimento()));
            }

        }else if (tempoPoupanca > 6 & tempoPoupanca <= 12) {
            for(int i = 0; i < tempoPoupanca; i++) {
                j++;
                investPoupanca.rentabilidadeCripto(investPoupanca, valorPoupanca, juros);
                System.out.println("Rentabilidade do Mês - " + j + ": R$ " +
                        new DecimalFormat("#,##0.##").format(investPoupanca.getValorInvestimento()));
            }
        }else if (tempoPoupanca > 12) {
            for(int i = 0; i < tempoPoupanca; i++) {
                j++;
                investPoupanca.rentabilidadeCripto(investPoupanca, valorPoupanca, juros);
                System.out.println("Rentabilidade do Mês - " + j + ": R$ " +
                        new DecimalFormat("#,##0.##").format(investPoupanca.getValorInvestimento()));
            }
        }

        System.out.println("Você obteve um lucro de R$ " +
                new DecimalFormat("#,##0.##").format(investPoupanca.getValorInvestimento()));
        investPoupanca.setSaldo(investPoupanca.getSaldo() + investPoupanca.getValorInvestimento());
        investPoupanca.saldo();

    }


}
