package sistema_banco;

public class Loja {
    private String nome;
    private Conta conta;

    public Loja(String nome, Conta conta) {
        this.nome = nome;
        this.conta = conta;
    }

    public void receberPagamento(double valor) {
        conta.depositar(valor);
        System.out.printf("Recebido pagamento de R$%.2f na loja %s.%n", valor, nome);
    }

    public void pagarFuncionarios(double salarioFuncionario) {
        double totalSalarios = salarioFuncionario * 2;
        if (totalSalarios <= conta.getSaldo()) {
            conta.sacar(totalSalarios);
            System.out.printf("Funcionarios da loja %s receberam o pagamento com sucesso.%n", nome);
        } else {
            System.out.println("Saldo insuficiente pra pagar os funcionarios da loja.");
        }
    }

	public Object getNome() {
		return nome;
	}
}