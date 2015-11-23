package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import vue.ParamOnlineGUI;

public class ReceptionChangementForm implements Runnable {
	BufferedReader in;
	ParamOnlineGUI panel;
	
	public ReceptionChangementForm(ParamOnlineGUI panel, Socket s){
		this.panel=panel;
		try {
			in = new BufferedReader (new InputStreamReader (s.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		String message;
		while(!panel.isStart()){
			try {
				message = in.readLine();
		        if(message!=null){
		        	if(message.split("<<double_point>>")[0].equals("maj")){
		        		if(message.split("<<double_point>>").length<=2){
		        			panel.majElement(message.split("<<double_point>>")[1],"");
		        		} else {
		        			panel.majElement(message.split("<<double_point>>")[1],message.split("<<double_point>>")[2]);
		        		}
		        	}
		        }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String[] splitMessage(String s){
		String[] r;
		String s_modif = "";
		char[] c_tab = s.toCharArray();
		
		for(int i=0;i<c_tab.length;i++){
			if(c_tab[i]=='\\' && c_tab[i+1]==':'){
				s_modif=s_modif+"<<double_point>>";
				i++;
			} else {
				s_modif=s_modif+c_tab[i];
			}
		}
		
		r = s_modif.split(":");
		
		return r;
	}

}
