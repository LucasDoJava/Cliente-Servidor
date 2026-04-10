package cliente;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteThreadSaida extends Thread {

    private Socket socket;

    public ClienteThreadSaida(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            PrintStream saida = new PrintStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            while (true) {
                String msg = scanner.nextLine();
                saida.println(msg);

                if (msg.equals("4")) {
                    socket.close();
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}