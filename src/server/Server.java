package server;

import java.net.*;

public class Server {
	public static ServerPanel first;
	
    public static void main(String arg[])
    {
        Socket socket = null;                      
        ServerSocket server_socket = null;              
        
        int count = 0;                            
        Thread thread[]= new Thread[10];             
        
        first = new ServerPanel();
        
        try {
            server_socket = new ServerSocket(8888);

            while(true)
            {
                socket = server_socket.accept();

                thread[count] = new Thread(new Receiver(socket));
                thread[count].start();
                count++;
            }
        } catch(Exception e) {};
    }
}