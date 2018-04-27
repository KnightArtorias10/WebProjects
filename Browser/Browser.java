/*
 * @author KnightArtorias10
 * Java-Browser with GUI
 */
package Browser;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Browser extends JFrame {
	
	public Browser() {
		/* GUI */
		super("JBrowser v0.1");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JLabel l_message = new JLabel("Enter an URL: ");
		/* Textfield where you enter the URL */
		JTextField tf_browse = new JTextField(20);
		/* Browse-button */
		JButton b_browse = new JButton("Browse");
		/* Search-bar */
		JPanel p = new JPanel();
		p.add(l_message);
		p.add(tf_browse);
		p.add(b_browse);
		add(p, BorderLayout.CENTER);
		
		TextArea ta_response = new TextArea();
		ta_response.setEditable(false);
		p.add(ta_response, BorderLayout.SOUTH);
		setVisible(true);
		b_browse.addActionListener(new ActionListener() {
			
			/* Searches the wanted URL an prints it in the text area */
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = browse(tf_browse.getText());
				ta_response.setText(str);
				validate();
			}
			
		});
	}

	/* Browse a specific URL with HTTP */
	public String browse(String url) {
		StringBuilder response = new StringBuilder();
		try {
			
			/* The wanted URL + HTTP port */
			Socket socket = new Socket(url, 80);
			InetAddress inet = socket.getInetAddress();
			
			System.out.println("Connecting to server " +inet.getHostName() +" at " +inet.getHostAddress());
			
			/* GET-request */
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			writer.print("GET / HTTP/1.1" +System.lineSeparator());
			writer.print("Host: " +url +System.lineSeparator() +System.lineSeparator());
			writer.flush();
			
			/* Reads the server's response */
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String str = null;
			
			/* Saves the response from the Bufferedreader into a String */
			while((str = reader.readLine()) != null) {
				/* Control */
				System.out.println(str);
				response.append(str);
			}
			reader.close();
			
		} catch (IOException ex) {
			System.out.println("IO-Error!");
		}
		return response.toString();
	}
	
}
