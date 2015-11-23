package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Loading extends JPanel {
	private Loading(){
		this.setBackground(Color.BLACK);
		File g = new File("");
		String path = g.getAbsolutePath();
		this.setLayout(new BorderLayout());
		String imageURL = path+"/images/loading.gif";
		ImageIcon image = new ImageIcon(imageURL);
		JLabel labelImage = new JLabel(image);
		this.add(labelImage, BorderLayout.CENTER);
	}
	
	private static Loading INSTANCE = null;
	 
	public static Loading getInstance(){			
		if (INSTANCE == null){ 	
			INSTANCE = new Loading();	
		}
		return INSTANCE;
	}
}
