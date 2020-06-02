package server;

import java.io.DataInputStream;
import java.net.*;

public class Receiver implements Runnable{

    Socket socket;
    DataInputStream in;
    String name;
    ServerPanel panel;
    int i = 0;

    public Receiver(Socket socket) throws Exception
    {
    	this.socket = socket;
    	in = new DataInputStream(socket.getInputStream());
    }

    public void run()
    {
        try
        {
            while(true)
            {
            	if(i == 0) {
            		String msg = in.readUTF();
                	String ip_addr = this.socket.getInetAddress().toString();
                	String ip_address = this.socket.getLocalAddress().toString();
                	System.out.println(msg + ", " + ip_address + ", " + ip_addr);
                	Server.first.dispose();
                	panel = new ServerPanel(ip_address);
                	i++;
            	}
            	else {
                	String msg = in.readUTF();
                	String ip_addr = this.socket.getInetAddress().toString();
                	String ip_address = this.socket.getLocalAddress().toString();
                	System.out.println(msg + ", " + ip_address + ", " + ip_addr);
                	panel = new ServerPanel(ip_address, panel);
            	}
            }
        }catch(Exception e) {
        }        
    }
}