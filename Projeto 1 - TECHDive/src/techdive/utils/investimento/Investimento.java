package techdive.utils.investimento;

import techdive.utils.conta.Conta;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Investimento extends Poupanca{

    private double valorInvestimento;
    private double valorInvestimentoCripto;
    private double valorInvestimentoBolsa;
    private final String fundoImobiliario = String.valueOf(TipoInvestimento.FUNDO_IMOBILIARIO);
    private final String cripto = String.valueOf(TipoInvestimento.CRIPTO);
    private final String bolsaValores = String.valueOf(TipoInvestimento.BOLSA_VALORES);
    private final double jurosBasico = 0.30;
    private final double jurosIntermediario = 0.50;
    private final double jurosAvancado = 0.80;

    private double valorRentavelFundos;

    Random random = new Random();
    private final Double lucroSeguro = random.nextDouble(5);
    private final Double lucroNeutro = random.nextDouble(7);
    private final Double lucroArriscado = random.nextDouble(12);

    /////////// GETTERS E SETTERS ///////////

    public double getValorInvestimento() {
        return valorInvestimento;
    }

    public void setValorInvestimento(double valorInvestimento) {
        this.valorInvestimento = valorInvestimento;
    }

    public String getFundoImobiliario() {
        return fundoImobiliario;
    }

    public String getCripto() {
        return cripto;
    }

    public String getBolsaValores() {
        return bolsaValores;
    }

    public Double getJurosBasico() {
        return jurosBasico;
    }

    public Double getJurosIntermediario() {
        return jurosIntermediario;
    }

    public Double getJurosAvancado() {
        return jurosAvancado;
    }

    public Double getLucroSeguro() {
        return lucroSeguro;
    }

    public Double getLucroNeutro() {
        return lucroNeutro;
    }

    public Double getLucroArriscado() {
        return lucroArriscado;
    }

    public double getValorRentavelFundos() {
        return valorRentavelFundos;
    }

    public void setValorRentavelFundos(double valorRentavelFundos) {
        this.valorRentavelFundos = valorRentavelFundos;
    }

    public double getValorInvestimentoCripto() {
        return valorInvestimentoCripto;
    }

    public void setValorInvestimentoCripto(double valorInvestimentoCripto) {
        this.valorInvestimentoCripto = valorInvestimentoCripto;
    }

    public double getValorInvestimentoBolsa() {
        return valorInvestimentoBolsa;
    }

    public void setValorInvestimentoBolsa(double valorInvestimentoBolsa) {
        this.valorInvestimentoBolsa = valorInvestimentoBolsa;
    }

    /////////// MÉTODOS ///////////

    // Método Investimento - Fundo Imobiliário

    // Método Rentabilidade - Fundo Imobiliário - OK
    public void rentabilidadeFundo(Conta fundo, double valor, double juros) {
        double valorRentavel = valor * juros;
        fundo.setValorRentavelFundos(fundo.getValorRentavelFundos() + valorRentavel);
    }

    public void investFundoImob(Conta investFundo) {
        Scanner entradaInvestimento = new Scanner(System.in);
        Scanner entradaTempoInvestimento = new Scanner(System.in);
        double valor;
        int tempo;
        int j = 0;

        System.out.println("Você escolheu o investimento " + fundoImobiliario);
        investFundo.saldo();
        System.out.print("Digite o valor a ser investido no " + fundoImobiliario + ": R$ ");
        valor = entradaInvestimento.nextDouble();
        investFundo.saque(investFundo, valor);
        System.out.print("Por quanto meses você deseja investir?: ");
        tempo = entradaTempoInvestimento.nextInt();

        if(tempo <= 5) {
            for(int i = 0; i < tempo; i++){
                j++;
                investFundo.rentabilidadeFundo(investFundo, valor, jurosBasico);
                System.out.println("Rentabilidade do Mês - " + j + ": R$ " + investFundo.getValorRentavelFundos());
            }
        }else if(tempo > 6 & tempo <= 12) {
            for(int i = 0; i < tempo; i++){
                j++;
                investFundo.rentabilidadeFundo(investFundo, valor, jurosIntermediario);
                System.out.println("Rentabilidade do Mês - " + j + ": R$ " + investFundo.getValorRentavelFundos());
            }
        }else if (tempo > 12) {
            for(int i = 0; i < tempo; i++){
                j++;
                investFundo.rentabilidadeFundo(investFundo, valor, jurosAvancado);
                System.out.println("Rentabilidade do Mês - " + j + ": R$ " + investFundo.getValorRentavelFundos());
            }
        }

        System.out.println("Você obteve um lucro de R$ " + investFundo.getValorRentavelFundos());
        investFundo.setSaldo(investFundo.getSaldo() + investFundo.getValorRentavelFundos());
        investFundo.saldo();
    }


    // Método Rentabilidade - Cripto - OK
    public void rentabilidadeCripto(Conta cripto, double valor, double acoes) {
        double rentabilidade = valor * acoes;
        cripto.setValorInvestimento(cripto.getValorInvestimento() + rentabilidade);
    }

    public void investCripto(Conta investCripto) {
        Scanner entradaInvestCripto = new Scanner(System.in);
        Scanner entradaTempoInvestCripto = new Scanner(System.in);
        double valorCripto;
        int tempoCripto;
        int j = 0;

        System.out.println("Você escolheu o investimento " + cripto);
        investCripto.saldo();
        System.out.print("Digite o valor a ser investido no " + cripto + ": R$ ");
        valorCripto = entradaInvestCripto.nextDouble();
        investCripto.saque(investCripto, valorCripto);
        System.out.print("Por quanto meses você deseja investir? ");
        tempoCripto = entradaTempoInvestCripto.nextInt();

        if(tempoCripto <= 5) {
            for(int i = 0; i < tempoCripto; i++) {
                j++;
                investCripto.rentabilidadeCripto(investCripto, valorCripto, lucroSeguro);
                System.out.println("Rentabilidade do Mês - " + j + ": R$ " +
                        new DecimalFormat("#,##0.##").format(investCripto.getValorInvestimentoCripto()));
            }

        }else if (tempoCripto > 6 & tempoCripto <= 12) {
            for(int i = 0; i < tempoCripto; i++) {
                j++;
                investCripto.rentabilidadeCripto(investCripto, valorCripto, lucroNeutro);
                System.out.println("Rentabilidade do Mês - " + j + ": R$ " +
                        new DecimalFormat("#,##0.##").format(investCripto.getValorInvestimentoCripto()));
            }
        }else if (tempoCripto > 12) {
            for(int i = 0; i < tempoCripto; i++) {
                j++;
                investCripto.rentabilidadeCripto(investCripto, valorCripto, lucroArriscado);
                System.out.println("Rentabilidade do Mês - " + j + ": R$ " +
                        new DecimalFormat("#,##0.##").format(investCripto.getValorInvestimentoCripto()));
            }
        }

        System.out.println("Você obteve um lucro de R$ " +
                new DecimalFormat("#,##0.##").format(investCripto.getValorInvestimentoCripto()));
        investCripto.setSaldo(investCripto.getSaldo() + investCripto.getValorInvestimentoCripto());
        investCripto.saldo();

    }

    // Método Rentabilidade - Bolsa de Valores - OK
    public void rentabilidadeBolsaValores(Conta bolsa, double valor, double acoes) {
        double rentabilidadeBolsa = valor * acoes;
        bolsa.setValorInvestimento(bolsa.getValorInvestimento() + rentabilidadeBolsa);
    }

    public void investBolsaValores(Conta investBolsa) {
        Scanner entradaInvestBolsa = new Scanner(System.in);
        Scanner entradaTempoInvestBolsa = new Scanner(System.in);
        double valorBolsa;
        int tempoBolsa;
        int j = 0;

        System.out.println("Você escolheu o investimento " + bolsaValores);
        investBolsa.saldo();
        System.out.print("Digite o valor a ser investido no " + bolsaValores + ": R$ ");
        valorBolsa = entradaInvestBolsa.nextDouble();
        investBolsa.saque(investBolsa, valorBolsa);
        System.out.print("Por quantos meses você deseja investir? ");
        tempoBolsa = entradaTempoInvestBolsa.nextInt();

        if(tempoBolsa <= 5) {
            for(int i = 0; i < tempoBolsa; i++) {
                j++;
                investBolsa.rentabilidadeBolsaValores(investBolsa, valorBolsa, lucroSeguro);
                System.out.println("Rentabilidade do Mês - " + j + ": R$ " +
                        new DecimalFormat("#,##0.##").format(investBolsa.getValorInvestimentoBolsa()));
            }
        }else if (tempoBolsa > 6 & tempoBolsa <= 12) {
            for(int i = 0; i < tempoBolsa; i++) {
                j++;
                investBolsa.rentabilidadeBolsaValores(investBolsa, valorBolsa, lucroNeutro);
                System.out.println("Rentabilidade do Mês - " + j + ": R$ " +
                        new DecimalFormat("#,##0.##").format(investBolsa.getValorInvestimentoBolsa()));
            }
        }else if (tempoBolsa > 12) {
            for(int i = 0; i < tempoBolsa; i++) {
                j++;
                investBolsa.rentabilidadeBolsaValores(investBolsa, valorBolsa, lucroArriscado);
                System.out.println("Rentabilidade do Mês - " + j + ": R$ " +
                        new DecimalFormat("#,##0.##").format(investBolsa.getValorInvestimentoBolsa()));
            }
        }
        System.out.println("Você obteve um lucro de R$ " +
                new DecimalFormat("#,##0.##").format(investBolsa.getValorInvestimentoBolsa()));
        investBolsa.setSaldo(investBolsa.getSaldo() + investBolsa.getValorInvestimentoBolsa());
        investBolsa.saldo();

    }



}
