package servidor;

import java.io.PrintStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class ThreadServidorEscrita extends Thread {

    private Socket socket;
    private BlockingQueue<String> fila;

    public ThreadServidorEscrita(Socket socket, BlockingQueue<String> fila) {
        this.socket = socket;
        this.fila = fila;
    }

    @Override
    public void run() {
        try {
            PrintStream saida = new PrintStream(socket.getOutputStream());

            while (!socket.isClosed()) {
                String msg = fila.take(); 
                saida.println(msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}