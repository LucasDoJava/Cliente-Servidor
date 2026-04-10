package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServidorSocket {

    private static int clientesAtivos = 0;
    private static final int MAX_CLIENTES = 5;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(6000);
        System.out.println("Servidor aguardando conexão...");

        while (true) {
            Socket socket = serverSocket.accept();
            
                if (clientesAtivos >= MAX_CLIENTES) {
                    System.out.println("Limite de clientes atingido!");
                    socket.close();
                    continue;
                }
                clientesAtivos++;
            

            System.out.println("Cliente conectado!");
            
            BlockingQueue<String> fila = new LinkedBlockingQueue<>();

            new ThreadServidor(socket, fila).start();
            new ThreadServidorEscrita(socket, fila).start();
        }
    }

    public static synchronized void clienteSaiu() {
        clientesAtivos--;
    }
}