package itts_chatSocket;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BarraStrumentiClient extends JPanel implements ActionListener {
	
	private JButton btnInvio; 
	private JTextField mess; 
	private TextAreaPanel tap; 
	String username;
	String m;
	Client cl;
	BufferedReader bf;
	BufferedWriter bw;
	Socket s;
	
	public BarraStrumentiClient(String username) {
		
		this.username=username;
	
		try {
			s = new Socket("localhost",2000);
			cl = new Client(s,username);
			bf=new BufferedReader(new InputStreamReader(s.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			//cl.riceviMessaggio();
			//cl.mandaMessaggi();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		btnInvio = new JButton("INVIA"); 
		btnInvio.addActionListener(this); 
		
		mess = new JTextField(); 
		Dimension d= mess.getPreferredSize(); 
		d.width = 400;  
		mess.setPreferredSize(d);
		
		setLayout(new FlowLayout()); 
		
		add(mess); 
		add(btnInvio);
		
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				
				String msg;
				while(s.isConnected()) {
					try {
						msg = bf.readLine();
						tap.appendiTesto(msg + "\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
		}).start();
				
	}
	
	public void setTextAreaPanel(TextAreaPanel tap) {
		
		this.tap = tap; 
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
        try {
        	m = mess.getText() + "\n";
    		tap.appendiTesto(m + "\n");
			bw.write(username + " : " + m);
			bw.newLine();
	        bw.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
		
	} 

}
