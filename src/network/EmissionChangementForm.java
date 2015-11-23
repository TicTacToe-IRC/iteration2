package network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import vue.ParamOnlineGUI;

public class EmissionChangementForm implements Runnable {
	PrintWriter out;
	ParamOnlineGUI panel;
	
	public EmissionChangementForm(ParamOnlineGUI panel, Socket s){
		this.panel=panel;
		try {
			out = new PrintWriter(s.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(!panel.isStart()){
			String r = panel.getModif();
			if(r!=null){
				out.println("maj<<double_point>>"+r);
				out.flush();
			}
		}
	}

}
