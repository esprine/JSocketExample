package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Client extends JFrame {
	static final long serialVersionUID = 0;
	static JButton btn = new JButton("Send");
	
	public Client() {
		this.setSize(300, 300);
	    this.setTitle("Client Button");
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.add(btn);
	    this.setVisible(true);
	}
	
    public static void main(String[] arg)
    {
        Socket socket = null;                 
        
        new Client();
        
        try {
            socket = new Socket("192.168.0.21", 8888); 
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            btn.addActionListener(new ActionListener(){
    			public void actionPerformed(ActionEvent e){
    				String data = "Send";
					try {
						out.writeUTF(data);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    			}
    		});
        } catch(IOException e) {}
    }
}