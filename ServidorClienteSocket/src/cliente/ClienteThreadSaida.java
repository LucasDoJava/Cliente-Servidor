package cliente;

import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import objeto.Pessoa;

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
                
                if (msg.equals("5")) {
                	saida.println("5");
                	
                	Pessoa p = new Pessoa("Lucas", 22);
                	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    out.writeObject(p);
                    out.flush();
                    
                    continue;
                }
                
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