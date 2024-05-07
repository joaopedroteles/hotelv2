import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Hotel {
    private List<Quarto> quartos;
    private BlockingQueue<Hospede> filaEspera;
    private AtomicInteger hospedesAtivos = new AtomicInteger(0);
    private List<Camareira> camareiras;
    private List<Recepcionista> recepcionistas;

    public Hotel() {
        quartos = new ArrayList<>();
        filaEspera = new LinkedBlockingQueue<>();
        camareiras = new ArrayList<>();
        recepcionistas = new ArrayList<>();

        // Inicializar os quartos
        for (int i = 0; i < 10; i++) {
            quartos.add(new Quarto(i + 1));
        }

        // Inicializar as camareiras
        for (int i = 0; i < 10; i++) {
            camareiras.add(new Camareira(this));
        }

        // Inicializar os recepcionistas
        for (int i = 0; i < 5; i++) {
            recepcionistas.add(new Recepcionista(this));
        }
    }

    public synchronized boolean checkIn(Hospede hospede) {
        System.out.println("Iniciando check-in para: " + hospede.getNome() + ", Membros: " + hospede.getNumeroDeMembros());

        boolean checkInConcluido = false;

        for (Quarto quarto : quartos) {
            if (quarto.isVago() && hospede.getNumeroDeMembros() <= (4 - quarto.getOcupacaoAtual())) {
                quarto.adicionarHospede(hospede, hospede.getNumeroDeMembros());
                System.out.println("Membros alocados: " + hospede.getNumeroDeMembros() + " para o Quarto: " + quarto.getNumero());
                System.out.println("Check-in concluído para: " + hospede.getNome());
                checkInConcluido = true;
                break;
            }
        }

        if (!checkInConcluido) {
            System.out.println("Não há quartos disponíveis para alocar todos os membros restantes.");
            filaEspera.offer(hospede);
            System.out.println("Hóspede " + hospede.getNome() + " foi adicionado à lista de espera.");
        }

        return checkInConcluido;
    }

    public synchronized boolean checkOut(Hospede hospede) {
        System.out.println("Iniciando check-out para: " + hospede.getNome());

        boolean checkOutConcluido = false;

        for (Quarto quarto : quartos) {
            if (quarto.getHospedes().contains(hospede)) {
                quarto.removerHospede(hospede);
                System.out.println("Check-out concluído para: " + hospede.getNome());
                checkOutConcluido = true;
                // Realocar hóspedes esperando
                while (!filaEspera.isEmpty()) {
                    Hospede proximoHospede = filaEspera.poll();
                    if (checkIn(proximoHospede)) {
                        System.out.println("Hóspede " + proximoHospede.getNome() + " realocado.");
                    } else {
                        break;
                    }
                }
                break; // Sai do loop após realizar o check-out
            }
        }

        if (!checkOutConcluido) {
            System.out.println("Hóspede não encontrado para check-out: " + hospede.getNome());
        }

        return checkOutConcluido;
    }

    public synchronized boolean adicionarFilaEspera(Hospede hospede) {
        return filaEspera.offer(hospede);
    }

    public synchronized Hospede proximoFilaEspera() {
        return filaEspera.poll();
    }

    public synchronized List<Camareira> getCamareiras() {
        return camareiras;
    }

    public Quarto[] getQuartos() {
        return quartos.toArray(new Quarto[0]);
    }

    public List<Recepcionista> getRecepcionistas() {
        return recepcionistas;
    }
}



    // Método para encontrar o quarto de um hospede específico
//    public Quarto encontrarQuartoPorHospede(String nomeHospede) {
//        for (Quarto quarto : quartos) {
//            for (Hospede hospede : quarto.getHospedes()) {
//                if (hospede.getNome().equals(nomeHospede)) {
//                    return quarto;
//                }
//            }
//        }
//        return null; // Não encontrado
//    }

