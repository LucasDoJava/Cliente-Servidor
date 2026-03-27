package servidor;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;

public class ThreadServidor extends Thread {

    private Socket socket;

    public ThreadServidor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            PrintStream saida = new PrintStream(socket.getOutputStream());

            String msg;

            while ((msg = reader.readLine()) != null) {

                switch (msg) {
                    case "1":
                        saida.println("Hora: " + LocalTime.now());
                        break;
                    case "2":
                        saida.println("Data: " + LocalDate.now());
                        break;
                    case "3":
                        saida.println("Por que o milho verde é amarelo?");
                        break;
                    case "4":
                        saida.println("Encerrando conexão...");
                        socket.close();
                        ServidorSocket.clienteSaiu();
                        return;
                    default:
                        saida.println("Opção inválida");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}