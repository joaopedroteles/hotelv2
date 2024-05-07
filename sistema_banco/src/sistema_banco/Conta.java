package sistema_banco;

public class Conta {
    private double saldo;

    public Conta(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void depositar(double valor) {
        this.saldo += valor;
    }

    public void sacar(double valor) {
        if (valor <= this.saldo) {
            this.saldo -= valor;
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public void transferir(Conta destino, double valor) {
        if (valor <= this.saldo) {
            this.sacar(valor);
            destino.depositar(valor);
            System.out.printf("Transferencia de R$%.2f foi feita com sucesso.%n", valor);
        } else {
            System.out.println("Saldo insuficiente para transferir o valor.");
        }
    }

    public void exibirSaldo() {
        System.out.printf("Saldo atual: R$%.2f%n", this.saldo);
    }

	public double getSaldo() {
		return saldo;
	}
}
