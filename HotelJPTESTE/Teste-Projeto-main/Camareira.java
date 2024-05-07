import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Camareira extends Thread {
    private final Hotel hotel;

    public Camareira(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void run() {
        while (true) {
            Quarto quartoParaLimpar = null;
            synchronized (hotel) {
                while ((quartoParaLimpar = encontrarQuartoParaLimpar()) == null) {
                    try {
                        hotel.wait(); // Espera até ser notificado de que um quarto precisa de limpeza
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (quartoParaLimpar != null) {
                limparQuarto(quartoParaLimpar);
            }
        }
    }

    private Quarto encontrarQuartoParaLimpar() {
        for (Quarto quarto : hotel.getQuartos()) {
            synchronized (quarto) {
                if (quarto.isChaveNaRecepcao()) {
                    return quarto;
                }
            }
        }
        return null;
    }

    public void limparQuarto(Quarto quarto) {
        synchronized (quarto) {
            System.out.println("Camareira está limpando o quarto " + quarto.getNumero());
            try {
                Thread.sleep(800); // Tempo para limpar o quarto
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Camareira terminou de limpar o quarto " + quarto.getNumero());
            quarto.setChaveNaRecepcao(false); // Marcar o quarto como limpo
        }

        synchronized (hotel) {
            hotel.notifyAll(); // Notifica que a limpeza do quarto foi concluída
        }
    }
}
