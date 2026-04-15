package cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.ObjectInputStream;

public class ClienteThread extends Thread{
	private Socket socket;
	
	public ClienteThread(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
		InputStreamReader inputReader = new InputStreamReader(socket.getInputStream());
		BufferedReader reader = new BufferedReader(inputReader);
		String x;
		ObjectInputStream objIn = null;
		while((x = reader.readLine()) != null) {
			
			if (x.equals("OBJETO")) {
			    String json = reader.readLine();
			    System.out.println("Objeto recebido: " + json);
			    continue;
			}
			
			System.out.println("Servidor: " + x);
		}}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	}

