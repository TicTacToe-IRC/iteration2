package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	Socket socket;
	
	public Client(){
	}
	
	public void init(){
	    BufferedReader in;
	    PrintWriter out;
	
	    try {
	    
	        //socket = new Socket(InetAddress.getLocalHost(),2009);   
	            System.out.println("Demande de connexion");
	
	            in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
	            String message_distant = in.readLine();
	            System.out.println(message_distant);
	            
	    }catch (UnknownHostException e) {
	        
	        e.printStackTrace();
	    }catch (IOException e) {
	        
	        e.printStackTrace();
	    }
	}
	
	public boolean connexion(){
		BufferedReader in;
	    PrintWriter out;
	    boolean r = false;
	    try {
	    
	        socket = new Socket(InetAddress.getLocalHost(),2009); 
	
	            in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
	            String message_distant = in.readLine();
	            System.out.println(message_distant);
	            if(message_distant.equals("connected")){
	            	r=true;
	            }
	           
	    }catch (UnknownHostException e) {
	        
	        e.printStackTrace();
	    }catch (IOException e) {
	        
	        e.printStackTrace();
	    }
	    return r;
	}
	
	public void close(){
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String s){
    	PrintWriter out;
		try {
			out = new PrintWriter(socket.getOutputStream());
	        out.println(s);
	        out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public String receiveMessage(){
		String r=null;
		BufferedReader in;
		try {
			in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
			if(in!=null){
	            String message = in.readLine();
	            r=message;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
    }
    
    public Socket getSocket(){
    	return socket;
    }
}
