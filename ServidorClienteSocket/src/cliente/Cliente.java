package cliente;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 6000);

        System.out.println("Conectado ao servidor!");
        System.out.println("1 - Hora");
        System.out.println("2 - Data");
        System.out.println("3 - Me fale algo legal");
        System.out.println("4 - Sair");

      
        new ClienteThread(socket).start();

        PrintStream saida = new PrintStream(socket.getOutputStream());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String msg = scanner.nextLine();
            saida.println(msg);

            if (msg.equals("4")) {
                break;
            }
        }

        socket.close();
    }
}
