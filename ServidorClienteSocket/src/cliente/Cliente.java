package cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 6000); //trocar pelo IP da rede Wifi
        
        socket.setSoTimeout(2000);
        BufferedReader reader = new BufferedReader(
        		new InputStreamReader(socket.getInputStream()));
        
        
        try {
            String resposta = reader.readLine();

            if (resposta != null && resposta.contains("Conexão não aceita")) {
                System.out.println(resposta);
                socket.close();
                return;
            }
        } catch (Exception e) {
       
        }

        socket.setSoTimeout(0);
        System.out.println("===========================================");
        System.out.println("Conectado ao servidor!");
        System.out.println("1 - Hora");
        System.out.println("2 - Data");
        System.out.println("3 - Me fale algo legal");
        System.out.println("4 - Sair");
        System.out.println("===========================================");
      
        new ClienteThread(socket).start();
        
        new ClienteThreadSaida(socket).start();

    }
}
