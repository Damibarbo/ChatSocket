package itts_chatSocket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TextAreaPanel extends JPanel {
	
	private JTextArea txt; 
	
	public TextAreaPanel() {
		
		txt = new JTextArea(); 
		txt.setEditable(false);
		txt.setAutoscrolls(true); 
		setLayout(new BorderLayout()); 
		add(txt,BorderLayout.CENTER); 
	}
	
	public void appendiTesto(String s) {
		
		txt.append(s);
		
	}
	
	

}
