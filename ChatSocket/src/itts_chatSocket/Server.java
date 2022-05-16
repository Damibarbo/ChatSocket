package itts_chatSocket;

import java.io.DataOutputStream;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	ServerSocket serverSocket; 
	Socket socket; 	
	int porta; 
	
	public Server(int porta) {
		this.porta=porta; 
	}
	
public void connessione() {
	try {
		serverSocket = new ServerSocket(porta);
		serverSocket.setReuseAddress(true);
		while(true) {
			socket=serverSocket.accept();
			System.out.println("Un nuovo client si è connesso" + socket.getInetAddress().getHostAddress());
			ClientHandler client = new ClientHandler(socket);
			new Thread(client).start();
		}	
	} catch (IOException e) {
		System.out.println(e); 
	}
	finally {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

public void chiusura() {
	
	try {
		serverSocket.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
	
}