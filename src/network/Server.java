package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	ServerSocket socketserver  ;
    Socket socketduserveur ;
    
    
    public Server(){
    	init();
    }
    
    public void init(){
	    /*BufferedReader in;
	    PrintWriter out;*/
	    
	    try {
	    
	       socketserver = new ServerSocket(2009,1);
	       /* System.out.println("Le serveur est à l'écoute du port "+socketserver.getLocalPort());
	        socketduserveur = socketserver.accept(); 
	            System.out.println("Un zéro s'est connecté");
	        out = new PrintWriter(socketduserveur.getOutputStream());
	            out.println("Vous êtes connecté zéro !");
	            out.flush();
	                    
	            socketduserveur.close();
	            socketserver.close();*/
	            
	    }catch (IOException e) {
	        
	        e.printStackTrace();
	    }
    }
    
    public boolean waitClient(){
    	boolean r = false;
    	try {
			socketduserveur = socketserver.accept();
			r = true;
			PrintWriter out;
			out = new PrintWriter(socketduserveur.getOutputStream());
            out.println("connected");
            out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return r;
    }
    
    public void close(){
        try {
        	socketduserveur.close();
			socketserver.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void sendMessage(String s){
    	PrintWriter out;
		try {
			out = new PrintWriter(socketduserveur.getOutputStream());
	        out.println(s);
	        out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public Socket getSocket(){
    	return socketduserveur;
    }
}
