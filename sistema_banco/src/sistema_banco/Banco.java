package sistema_banco;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banco {
    private final Lock lock = new ReentrantLock();

    public void transferir(Conta origem, Conta destino, double valor) {
        lock.lock();
        try {
            if (origem.getSaldo() >= valor) {
                origem.sacar(valor);
                destino.depositar(valor);
                System.out.printf("Transferencia de R$%.2f de %s para %s realizada com sucesso.%n", valor, origem, destino);
            } else {
                System.out.printf("Transferencia de R$%.2f de %s para %s nao foi realizada: saldo insuficiente.%n", valor, origem, destino);
            }
        } finally {
            lock.unlock();
        }
    }

    public void exibirSaldosFinais(Conta[] contas) {
        for (Conta conta : contas) {
            conta.exibirSaldo();
        }
    }
}