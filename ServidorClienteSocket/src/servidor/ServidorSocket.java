package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {

    private static int clientesAtivos = 0;
    private static final int MAX_CLIENTES = 5;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(6000);
        System.out.println("Servidor aguardando conexão...");

        while (true) {
            Socket socket = serverSocket.accept();

            synchronized (ServidorSocket.class) {
                if (clientesAtivos >= MAX_CLIENTES) {
                    System.out.println("Limite de clientes atingido!");
                    socket.close();
                    continue;
                }
                clientesAtivos++;
            }

            System.out.println("Cliente conectado!");

            new ThreadServidor(socket).start();
        }
    }

    public static synchronized void clienteSaiu() {
        clientesAtivos--;
    }
}