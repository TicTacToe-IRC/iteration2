package vue;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuGUI extends JPanel{
	private Image background;
	private JButton boutonJouer = BoutonJouer.getInstance();
	private BoutonQuitter boutonQuitter = BoutonQuitter.getInstance();
	private BoutonStat boutonStat = BoutonStat.getInstance();
	private BoutonApropos boutonPropos = BoutonApropos.getInstance();
	private JButton boutonJouerEnLigne = new JButton("Jouer en ligne");
	private JButton boutonAide = new JButton("Aide");
	private JFrame parent;
	
	Panel panel;
	
	public MenuGUI(JFrame parent){
		super();
		this.parent = parent;
		init();
	}
	
	public void init(){
        this.background = ResourceLoader.getImage("background-menu.jpg");
			
		this.setLayout(new GridBagLayout());
		   
	    GridBagConstraints gbc = new GridBagConstraints();
	    
	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    
	    gbc.insets = new Insets(5, 5, 5, 5);
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    gbc.ipady = 15;
	    gbc.ipadx = 70;
	    this.add(boutonJouer, gbc);
	    //---------------------------------------------
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.gridx = 1;	
	    gbc.gridy = 1;
	    gbc.ipady = 15;
	    gbc.ipadx = 70;
	    this.add(boutonJouerEnLigne, gbc);
	    //---------------------------------------------
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.gridx = 1;
	    gbc.gridy = 2;
	    gbc.ipady = 15;
	    gbc.ipadx = 70;
	    this.add(boutonStat, gbc);
	    //---------------------------------------------
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.gridx = 1;	
	    gbc.gridy = 3;
	    gbc.ipady = 15;
	    gbc.ipadx = 70;
	    this.add(boutonAide, gbc);
	    //---------------------------------------------
	    //Cette instruction informe le layout que c'est une fin de ligne
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.gridx = 1;	
	    gbc.gridy = 4;
	    gbc.ipady = 15;
	    gbc.ipadx = 70;
	    this.add(boutonPropos, gbc);
	    //---------------------------------------------
	    gbc.gridx = 1;
	    gbc.gridy = 5;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 2;
	    gbc.ipady = 15;
	    gbc.ipadx = 70;
	    //Celle-ci indique que la cellule se réplique de façon verticale
	    gbc.fill = GridBagConstraints.VERTICAL;
	    this.add(boutonQuitter, gbc);
	    
	    boutonJouerEnLigne.addActionListener(new JouerEnLigne());
	    
	    if(boutonJouer.getActionListeners().length == 0){
	    	boutonJouer.addActionListener(new Jouer());
	    }
		if(boutonQuitter.getActionListeners().length == 0){
			boutonQuitter.addActionListener(new Quitter());
		}
		if(boutonStat.getActionListeners().length == 0){
			boutonStat.addActionListener(new Stat());
		}
		
		if(boutonPropos.getActionListeners().length == 0){
			boutonPropos.addActionListener(new Propos());
		}
		
		if(boutonAide.getActionListeners().length == 0){
			boutonAide.addActionListener(new Propos());
		}
		
		//this.add(panel, BorderLayout.PAGE_END);
		
	}
	
	public class Jouer implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			((MainFrame) parent).goTo("param");
		}
	}
	
	public class JouerEnLigne implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			((MainFrame) parent).goTo("paramOnline");
		}
	}
	
	public class Quitter implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}
	}
	
	public class Stat implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			((MainFrame) parent).goTo("stat");
		}
	}
	
	public class Propos implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			((MainFrame) parent).goTo("propos");
		}
	}	
	
	public class Aide implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			((MainFrame) parent).goTo("aide");
		}	
	}
	
	@Override
	public void paintComponent(Graphics g){
        if(background!=null){
            Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        }
    }

}
