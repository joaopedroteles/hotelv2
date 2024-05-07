package sistema_banco;

public class Funcionario implements Runnable {
    private String nome;
    private Conta contaSalario;
    private Conta contaInvestimento;

    public Funcionario(String nome, Conta contaSalario, Conta contaInvestimento) {
        this.nome = nome;
        this.contaSalario = contaSalario;
        this.contaInvestimento = contaInvestimento;
    }

    public void receberSalario(double salario) {
        contaSalario.depositar(salario);
        System.out.printf(" %s recebeu um salario de R$%.2f.%n", nome, salario);
        investirSalario(salario);
    }

    private void investirSalario(double salario) {
        double percentualInvestimento = 0.20;
        double valorInvestido = salario * percentualInvestimento;
        contaSalario.sacar(valorInvestido);
        contaInvestimento.depositar(valorInvestido);
        System.out.printf(" %s investiu R$%.2f na conta de investimento.%n", nome, valorInvestido);
    }

    @Override
    public void run() {
        receberSalario(1400.0);
    }
}
