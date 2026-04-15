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
                String descricao;

                switch (msg) {
                    case "1":
                    	descricao = "Hora";
                        resposta = "Hora: " + LocalTime.now();
                        System.out.println("===========================================");
                        System.out.println("Cliente " + socket.getInetAddress() + " pediu: " + descricao);
                        break;
                    case "2":
                    	descricao = "Data";
                        resposta = "Data: " + LocalDate.now();
                        System.out.println("===========================================");
                        System.out.println("Cliente " + socket.getInetAddress() + " pediu: " + descricao);
                        break;
                    case "3":
                    	descricao = "Me fale algo legal";
                        resposta = "Por que o milho verde é amarelo?";
                        System.out.println("===========================================");
                        System.out.println("Cliente " + socket.getInetAddress() + " pediu: " + descricao);
                        break;
                    case "4":
                    	descricao = "Sair";
                        resposta = "Encerrando conexão...";
                        
                        System.out.println("Cliente " + socket.getInetAddress() + " pediu: " + descricao);
                        fila.put(resposta);
                        System.out.println("Cliente desconectado: " + socket.getInetAddress());
                        socket.close();
                        ServidorSocket.clienteSaiu();
                        return;
                    case "5":
                        descricao = "Objeto JSON";

                        System.out.println("===========================================");
                        System.out.println("Cliente " + socket.getInetAddress() + " enviou um objeto");

                        String json = reader.readLine();

                        System.out.println("JSON recebido: " + json);

                        json = json.replace("\"idade\":22", "\"idade\":23");

                        fila.put("OBJETO");
                        fila.put(json);

                        continue;
                    default:
                    	descricao = "Opção inválida";
                        resposta = "Opção inválida";
                }
               
                fila.put(resposta); 
            }
        } catch (java.net.SocketException e) {
            System.out.println("Cliente desconectado: " + socket.getInetAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}