package itts_chatSocket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GestoreClient {
	
public static void main(String[] args) {
	
	String user; 
	Scanner sc=new Scanner(System.in);
	System.out.println("Inserisci nome utente per entrare nella chat");
	user=sc.nextLine();
	new ClientFrame(user);
		
	}

}
