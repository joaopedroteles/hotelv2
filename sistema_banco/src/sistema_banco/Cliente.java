package sistema_banco;

import java.util.List;

public class Cliente implements Runnable {
    private String nome;
    private Conta conta;
    private List<Loja> lojas;

    public Cliente(String nome, Conta conta, List<Loja> lojas) {
        this.nome = nome;
        this.conta = conta;
        this.lojas = lojas;
    }

    public void realizarCompras() {
        double saldoInicial = conta.getSaldo();
        int indexLoja = 0;
        while (conta.getSaldo() > 0) {
            Loja lojaAtual = lojas.get(indexLoja);
            double valorCompra = Math.random() < 0.5 ? 100.0 : 200.0;
            if (valorCompra <= conta.getSaldo()) {
                conta.sacar(valorCompra);
                lojaAtual.receberPagamento(valorCompra);
                System.out.printf(" %s fez uma compra de R$%.2f na loja %s.%n", nome, valorCompra, lojaAtual.getNome());
            } else {
                break;
            }
            indexLoja = (indexLoja + 1) % lojas.size();
        }
        System.out.printf(" %s gastou R$%.2f e sobrou R$%.2f na conta.%n", nome, saldoInicial - conta.getSaldo(), conta.getSaldo());
    }

    @Override
    public void run() {
        realizarCompras();
    }
}