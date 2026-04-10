package servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.time.LocalDate;
import java.time.LocalTime;

public class ThreadServidor extends Thread {

    private Socket socket;
    private BlockingQueue<String> fila;

    public ThreadServidor(Socket socket, BlockingQueue<String> fila) {
        this.socket = socket;
        this.fila = fila;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            String msg;

            while ((msg = reader.readLine()) != null) {

                String resposta;

                switch (msg) {
                    case "1":
                        resposta = "Hora: " + LocalTime.now();
                        break;
                    case "2":
                        resposta = "Data: " + LocalDate.now();
                        break;
                    case "3":
                        resposta = "Por que o milho verde é amarelo?";
                        break;
                    case "4":
                        resposta = "Encerrando conexão...";
                        fila.put(resposta);
                        socket.close();
                        return;
                    default:
                        resposta = "Opção inválida";
                }

                fila.put(resposta); 
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}