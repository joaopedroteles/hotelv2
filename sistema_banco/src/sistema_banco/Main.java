package sistema_banco;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    	Conta contaBanco = new Conta(0);
        Conta contaLoja1 = new Conta(0);
        Conta contaLoja2 = new Conta(0);
        Conta contaCliente1 = new Conta(1000);
        Conta contaCliente2 = new Conta(1000);
        Conta contaCliente3 = new Conta(1000);
        Conta contaCliente4 = new Conta(1000);
        Conta contaCliente5 = new Conta(1000);
        
        List<Conta> contasLojas = List.of(contaLoja1, contaLoja2);
        List<Conta> contasClientes = List.of(contaCliente1, contaCliente2, contaCliente3, contaCliente4, contaCliente5);
        
        Banco banco = new Banco();
        
        Loja loja1 = new Loja("Loja 1", contaLoja1);
        Loja loja2 = new Loja("Loja 2", contaLoja2);
        
        Cliente cliente1 = new Cliente("Cliente 1", contaCliente1, List.of(loja1, loja2));
        Cliente cliente2 = new Cliente("Cliente 2", contaCliente2, List.of(loja1, loja2));
        Cliente cliente3 = new Cliente("Cliente 3", contaCliente3, List.of(loja1, loja2));
        Cliente cliente4 = new Cliente("Cliente 4", contaCliente4, List.of(loja1, loja2));
        Cliente cliente5 = new Cliente("Cliente 5", contaCliente5, List.of(loja1, loja2));
        
        
        Funcionario funcionario1Loja = new Funcionario("Funcionario 1 - Loja 1", contaLoja1, contaBanco);
        Funcionario funcionario2Loja = new Funcionario("Funcionario 2 - Loja 1", contaLoja2, contaBanco);
        Funcionario funcionario3Loja = new Funcionario("Funcionario 1 - Loja 2", contaLoja1, contaBanco);
        Funcionario funcionario4Loja = new Funcionario("Funcionario 2 - Loja 2", contaLoja2, contaBanco);
        
        Thread threadCliente1 = new Thread(cliente1);
        Thread threadCliente2 = new Thread(cliente2);
        Thread threadCliente3 = new Thread(cliente3);
        Thread threadCliente4 = new Thread(cliente4);
        Thread threadCliente5 = new Thread(cliente5);
        
        Thread threadFuncionario1Loja = new Thread(funcionario1Loja);
        Thread threadFuncionario2Loja = new Thread(funcionario2Loja);
        Thread threadFuncionario3Loja = new Thread(funcionario3Loja);
        Thread threadFuncionario4Loja = new Thread(funcionario4Loja);
        
        threadCliente1.start();
        threadCliente2.start();
        threadCliente3.start();
        threadCliente4.start();
        threadCliente5.start();
        threadFuncionario1Loja.start();
        threadFuncionario2Loja.start();
        threadFuncionario3Loja.start();
        threadFuncionario4Loja.start();
        
        try {
            threadCliente1.join();
            threadCliente2.join();
            threadCliente3.join();
            threadCliente4.join();
            threadCliente5.join();
            
            threadFuncionario1Loja.join();
            threadFuncionario2Loja.join();
            threadFuncionario3Loja.join();
            threadFuncionario4Loja.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Saldos finais das contas: Banco, Loja1, Loja2, Cliente1, Cliente2, Cliente3, Cliente4, Cliente5, Respectivamente");
        banco.exibirSaldosFinais(new Conta[] { contaBanco, contaLoja1, contaLoja2, contaCliente1, contaCliente2, contaCliente3, contaCliente4, contaCliente5 });
    }
}
