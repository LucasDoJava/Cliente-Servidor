package cliente;

import java.net.Socket;

public class Cliente {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 6000); //trocar pelo IP da rede Wifi

        System.out.println("Conectado ao servidor!");
        System.out.println("1 - Hora");
        System.out.println("2 - Data");
        System.out.println("3 - Me fale algo legal");
        System.out.println("4 - Sair");

      
        new ClienteThread(socket).start();
        
        new ClienteThreadSaida(socket).start();

    }
}
