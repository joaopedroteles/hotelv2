import java.util.Random;

public class Recepcionista extends Thread {
    private Hotel hotel;

    public Recepcionista(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            try {
                Thread.sleep(random.nextInt(5000)); // Tempo de espera aleatório antes de atender um novo hóspede
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (hotel) {
                Hospede proximoHospede = hotel.proximoFilaEspera();
                if (proximoHospede != null) {
                    System.out.println("Recepcionista está fazendo check-in para: " + proximoHospede.getNome());
                    if (hotel.checkIn(proximoHospede)) {
                        System.out.println("Recepcionista alocou um quarto para " + proximoHospede.getNome());
                    } else {
                        System.out.println("Não foi possível alocar um quarto para " + proximoHospede.getNome());
                    }
                }
            }
        }
    }
}