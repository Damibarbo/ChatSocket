package itts_chatSocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientHandler implements Runnable{
	
	private Socket socket; 
	BufferedReader bf;
	BufferedWriter bw;
	String username; 
	private static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
	
	public ClientHandler(Socket socket) {
		try {
			this.socket=socket;
			this.bf=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.username=bf.readLine();
			clientHandlers.add(this);
			broadcastMessage("SERVER: " + username + "si è unito alla chat");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		try {
			
			String messaggio;
			
			while((messaggio=bf.readLine())!= null) {
				broadcastMessage(messaggio);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (bf != null) {
                    bf.close();
                    socket.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	public void broadcastMessage(String s) {
				try {
					for (ClientHandler ch: clientHandlers) {
						if(!ch.username.equals(username)) {
							ch.bw.write(s+"\n");
							ch.bw.newLine();
							ch.bw.flush();
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public void removeClient() {
		
		clientHandlers.remove(this);
		broadcastMessage("SERVER : "+username+ "ha lasciato la chat");
		
	}
}
	
