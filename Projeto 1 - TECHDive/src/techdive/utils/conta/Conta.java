package techdive.utils.conta;

import techdive.utils.investimento.Investimento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Conta extends Investimento {

    private String nome;
    private String CPF;
    private double rendaMensal;
    private int conta; // ID da conta fazer incremental e padronizar os números
    private final String ag1 = String.valueOf(EscolheAgencia.FLORIANOPOLIS);
    private final String ag2 = String.valueOf(EscolheAgencia.SAO_JOSE);
    private double saldo;
    private double chequeEspecial;


    // Variável para data e hora local
    DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    Calendar validaFDS = Calendar.getInstance();

    boolean domingo = validaFDS.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    boolean sabado = validaFDS.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;


    String hitoricoTransferCPF;
    String historicoTransferCNPJ;


    // Variáveis para o Método Transferir
    private double valorEnviarCNPJ;
    private double valorEnviarCPF;
    private String chaveCPFCNPJ;

    // Variável para armazenar o valor recebido da transferência
    private double valorRecebido;

    // Variável tipo transferência
    private final String pix = String.valueOf(TipoTransferencia.PIX);
    private final String ted = String.valueOf(TipoTransferencia.TED);

    /////////// GETTERS E SETTERS ///////////

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Double getRendaMensal() {
        return rendaMensal;
    }

    public void setRendaMensal(Double rendaMensal) {
        this.rendaMensal = rendaMensal;
    }

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public String getAg1() {
        return ag1;
    }

    public String getAg2() {
        return ag2;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public double getChequeEspecial() {
        return chequeEspecial;
    }

    public void setChequeEspecial(double chequeEspecial) {
        this.chequeEspecial = chequeEspecial;
    }

    public double getValorEnviarCNPJ() {
        return valorEnviarCNPJ;
    }

    public void setValorEnviarCNPJ(double valorEnviarCNPJ) {
        this.valorEnviarCNPJ = valorEnviarCNPJ;
    }

    public double getValorEnviarCPF() {
        return valorEnviarCPF;
    }

    public void setValorEnviarCPF(double valorEnviarCPF) {
        this.valorEnviarCPF = valorEnviarCPF;
    }

    public String getChaveCPFCNPJ() {
        return chaveCPFCNPJ;
    }

    public void setChaveCPFCNPJ(String chaveCPFCNPJ) {
        this.chaveCPFCNPJ = chaveCPFCNPJ;
    }

    /////////// MÉTODOS ///////////

    // Método ID Sequencial
    public void idSequencial(Conta id) {
        id.setConta(id.getConta() + 1);
    }

    // Método Saque
    public void saque(Conta saque, double valor) {
        saque.setSaldo(saque.getSaldo() - valor);
    }

    // Método Deposito
    public void depositar(Conta deposito, Double valor) {
        deposito.setSaldo(deposito.getSaldo() + valor);
    }

    // Método Saldo

    public void saldo(Conta saldo) {
        System.out.println("Seu saldo é de R$ " +
                new DecimalFormat("#,##0.##").format(saldo.getSaldo()));
    }

   // chequeEspecial(Conta cheque, double valor)

    // Método Extrato
    public String[] extrato(Conta extrato){
       return new String[] {
               "\nSegue abaixo o extrato bancário de sua conta",
               "Saldo: R$ " + new DecimalFormat("#,##0.##").format(extrato.getSaldo()),
               "Renda Mensal: R$ " + new DecimalFormat("#,##0.##").format(extrato.getRendaMensal()),
               "Rentabilidade Poupança: R$ " +
                       new DecimalFormat("#,##0.##").format(extrato.getValorInvestimento()),
               "Rentabilidade Fundos Imobiliários: R$ " +
                       new DecimalFormat("#,##0.##").format(extrato.getValorRentavelFundos()),
               "Rentabilidade Cripto: R$ " +
                       new DecimalFormat("#,##0.##").format(extrato.getValorInvestimentoCripto()),
               "Rentabilidade Bolsa de Valores: R$ " +
                       new DecimalFormat("#,##0.##").format(extrato.getValorInvestimentoBolsa()),
               "Ultima Transferência de CPF " + extrato.getCPF() + " para " + extrato.getChaveCPFCNPJ() +
                       " no valor de R$ " + extrato.getValorEnviarCPF() + hitoricoTransferCPF,
               "Ultima Transferência de CPNJ " + extrato.getCPF() + " para " + extrato.getChaveCPFCNPJ() +
                       " no valor de R$ " + extrato.getValorEnviarCNPJ() + historicoTransferCNPJ,



       };
    }


    // Método Transferir -> START

    //  Regex CPF ou CNPJ (\d{2}\.\d{3}\.\d{3}\/\d{4}\-\d{2})|(\d{3}\.\d{3}\.\d{3}\-\d{2})
    //  Link de pesquisa do Regex CPF/CNPJ: https://regexlib.com/Search.aspx?k=CPF&c=-1&m=-1&ps=20
    //  ANSI - Cor em texto: https://www.geeksforgeeks.org/how-to-print-colored-text-in-java-console/

    public void metodoTransferencia(Conta transferir) {
        Scanner entradaTransferencia = new Scanner(System.in);
        // Variável "chave" para armazenar o valor do CPF ou CNPJ

        System.out.print("\nDigite o CPF/CNPJ do recebedor da transferência: ");
        transferir.setChaveCPFCNPJ(entradaTransferencia.next());

        Pattern pattern = Pattern.compile(
                "(\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2})|(\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2})"
        );
        Matcher matcher = pattern.matcher(transferir.getChaveCPFCNPJ());

        // Aplica cor ao texto para ter uma validação visual para o usuário
        String cor = "\u001B[";
        String cpfCNPValido = cor + "32" + "m" + transferir.getChaveCPFCNPJ() + cor + "m"; // cor verde
        String cpfCNPJInvalido = cor + "31" + "m" + transferir.getChaveCPFCNPJ() + cor + "m"; // cor vermelha
        String notificaDiasUTeis = cor + "33" + "m" +
                "** Transferência apenas em dias úteis **" + cor + "m"; // cor amarela
        String notificaCPF = cor + "31" + "m" +
                "** Você não pode transferir dinheiro para você mesmo!! **" + cor + "m"; // cor vermelha
        String notificaSaldo = cor + "31" + "m" +
                "** Saldo infuficiente para a transferência! **" + cor + "m"; // cor vermelha

        if(matcher.matches() & transferir.getChaveCPFCNPJ().length() == 14 & !sabado & !domingo &
                !Objects.equals(transferir.getCPF(), transferir.getChaveCPFCNPJ())) {
            System.out.println("O CPF " + cpfCNPValido + " é válido");
            transferir.saldo(transferir);
            System.out.print("Digite o valor para transferir para o CPF " + cpfCNPValido + ": R$ ");
            Scanner valorEntradaCPF = new Scanner(System.in);
            transferir.setValorEnviarCPF(valorEntradaCPF.nextDouble());

            if(transferir.getValorEnviarCPF() < (transferir.getSaldo() + transferir.getChequeEspecial())) {
                hitoricoTransferCPF = "\nValor transferido R$ " + getValorEnviarCPF() +
                        " - Transferido dia " + dataFormatada.format(LocalDateTime.now());
                System.out.println(hitoricoTransferCPF);
                transferir.saque(transferir, transferir.getValorEnviarCPF());
                transferir.saldo(transferir);
            }else {
                System.out.println(notificaSaldo);
            }

        }else if(matcher.matches() & transferir.getChaveCPFCNPJ().length() == 18 & !sabado & !domingo) {
            System.out.println("O CNPJ " + cpfCNPValido + " é válido");
            transferir.saldo(transferir);
            System.out.print("Digite o valor para transferir para o CNPJ " + cpfCNPValido + ": R$ ");
            Scanner valorEntradaCNPJ = new Scanner(System.in);
            transferir.setValorEnviarCNPJ(valorEntradaCNPJ.nextDouble());

            if(transferir.getValorEnviarCNPJ() < (transferir.getSaldo() + transferir.getChequeEspecial())) {
                historicoTransferCNPJ = "\nValor transferido R$ " + getValorEnviarCNPJ() +
                        " - Transferido dia " + dataFormatada.format(LocalDateTime.now());
                System.out.println(historicoTransferCNPJ);

                transferir.saque(transferir, transferir.getValorEnviarCNPJ());
                transferir.saldo(transferir);
            }else {
                System.out.println(notificaSaldo);
            }


        }else if(Objects.equals(transferir.getChaveCPFCNPJ(), transferir.getCPF())) {
                System.out.println(notificaCPF);
        }else {
            System.out.println(notificaDiasUTeis);
            System.out.println("\nO CPF/CNPJ " + cpfCNPJInvalido + " não é válido");
            System.out.println("O CPF deve conter as pontuações, Exemplo: 000.000.000-00");
            System.out.println("O CNPJ deve conter as pontuações, Exemplo: 00.000.000/0000-00");
        }
    }

    public void transferencia(Conta transferir, int tipo) {
        if(tipo == 1) {
            // Tipo PIX
            System.out.println("Você selecionou transferência via " + pix);
            metodoTransferencia(transferir);

        }
        if(tipo == 2) {
            // Tipo TED
            System.out.println("Você selecionou transferência via " + ted);
            metodoTransferencia(transferir);
        }
    }

    // Método Transferir -> END


    // Método Alterar dados cadastrais — START
    // * Alterar Nome
    public void alteraNome(Conta nome, String novoNome) {
        nome.setNome(novoNome);
    }

    // * Altera Renda Mensal
    public void alteraRenda(Conta renda, Double novaRenda) {
        renda.setRendaMensal(novaRenda);
    }

    // * Altera Agência
    public String agencia(Conta agencia, int numero) {
        return (numero == 1) ? agencia.getAg1() : agencia.getAg2();
    }

    // Método Alterar dados cadastrais — END

    // Método Cheque Especial - OK
    public void chequeEspecial(Conta cheque, double valor) {
        //cheque.setChequeEspecial(cheque.getChequeEspecial() + 1000);

        if(valor <= 5000) {
            cheque.setChequeEspecial(cheque.getChequeEspecial() + 1000);
            System.out.println("Seu saldo é de R$ " +
                    new DecimalFormat("#,##0.##").format(cheque.getSaldo()) +
                            " | Seu limite de cheque especial é de R$ " +
                            new DecimalFormat("#,##0.##").format(cheque.getChequeEspecial())
                    );
        }else if(valor >= 5000 & valor <= 10000) {
            cheque.setChequeEspecial(cheque.getChequeEspecial() + 2500);
            System.out.println("Seu saldo é de R$ " +
                    new DecimalFormat("#,##0.##").format(cheque.getSaldo()) +
                    " | Seu limite de cheque especial é de R$ " +
                    new DecimalFormat("#,##0.##").format(cheque.getChequeEspecial())
            );
        }else if(valor > 10000) {
            cheque.setChequeEspecial(cheque.getChequeEspecial() + 3500);
            System.out.println("Seu saldo é de R$ " +
                    new DecimalFormat("#,##0.##").format(cheque.getSaldo()) +
                    " | Seu limite de cheque especial é de R$ " +
                    new DecimalFormat("#,##0.##").format(cheque.getChequeEspecial())
            );
        }

    }


}
