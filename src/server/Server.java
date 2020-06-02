package server;

import java.net.*;

public class Server {
	public static ServerPanel first;
	
    public static void main(String arg[])
    {
        //접속한 Client와 통신하기 위한 Socket
        Socket socket = null;           
        //Client 접속을 받기 위한 ServerSocket            
        ServerSocket server_socket = null;              
        
        int count = 0;                            
        Thread thread[]= new Thread[10];             
        
        first = new ServerPanel();
        
        try {
            server_socket = new ServerSocket(8888);
            //Server의 메인쓰레드는 게속해서 사용자의 접속을 받음
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