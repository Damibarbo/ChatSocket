package itts_chatSocket;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientFrame extends JFrame  {
	
	public TextAreaPanel chat; 
	public BarraStrumentiClient bs; 
	
	public ClientFrame(String nu) {
		
		super(nu);
		
		
		setLayout(new BorderLayout()); 
				
		chat=new TextAreaPanel(); 
		chat.setAutoscrolls(true);
		JScrollPane jp = new JScrollPane(chat);
		jp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		bs = new BarraStrumentiClient(nu);
		bs.setTextAreaPanel(chat);
		
		add(jp,BorderLayout.CENTER);
		add(bs,BorderLayout.PAGE_END); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		setVisible(true);
	}
	

}
