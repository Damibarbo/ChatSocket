package itts_chatSocket;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;



public class Client {
	Socket socket;
	BufferedReader bf;
	BufferedWriter bw;
	String username;
	
	public Client(Socket socket,String username) {
		
			this.socket=socket;
			//this.bf=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//this.bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); 
			this.username=username;
		
	}
	
	/*public void mandaMessaggi(BufferedReader bf,BufferedWriter bw) {
		try {
			bw.write(username);
			bw.newLine();
			bw.flush();
			while(true) {
            	messaggio +="\n";
                bw.write(username + " : " + messaggio);
                bw.newLine();
                bw.flush();
            	
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public void riceviMessaggio(BufferedReader bf,BufferedWriter bw) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				
				String msg;
				while(socket.isConnected()) {
					try {
						msg = bf.readLine();
						System.out.println(msg);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
		}).start();
	}
	
	
	
	public void chiusura() {
		try {
			socket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}