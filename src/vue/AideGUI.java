package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import model.StatRecord;

public class AideGUI extends JPanel {
	private Image background;
	Panel panel;
	private JFrame parent;
		
	public AideGUI(JFrame parent){		
		super();
		this.parent = parent;
		init();
	}
	public class Retour implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			((MainFrame) parent).goTo("menu");
		}
	}
	public void init(){
        this.background = ResourceLoader.getImage("background-stat.jpg");
        repaint();
        JPanel texte = new JPanel();
        //texte.setPreferredSize(new Dimension(300, 300));
        texte.setOpaque(false);
        
		JPanel regles = new JPanel();
		regles.setLayout(new GridBagLayout());
		
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=0;
		c.gridy=0;
		
		 JScrollPane jsp = new JScrollPane(regles);
			jsp.setOpaque(false);
			jsp.getViewport().setOpaque(false);
		
	
		  Font police = new Font("Tahoma", Font.BOLD, 23);
		  Font police2 = new Font("Tahoma", Font.BOLD, 20);
				
				JPanel title = new JPanel();
				title.setLayout(new GridBagLayout());
				JLabel titre = new JLabel("Tic Tac Toe 3D");
				JLabel titre2 = new JLabel("Règles du Jeu");
				titre.setFont(police);
				titre2.setFont(police2);
				title.add(titre, c);
				c.gridy++;
				title.add(titre2, c);
				title.setOpaque(false);

				
				JLabel ligne1 = new JLabel("Le Tic Tac Toe 3D est un jeu reprenant le "
						+ "principe du puissance 4, qui est d'aligner 4 pions horizontalement,"
						);
				JLabel ligne2 = new JLabel("verticalement ou en diagonale.");
				JLabel ligne3 = new JLabel("");
				JLabel ligne4 = new JLabel("Règle 3");

				c.ipadx = 30;
				
				regles.add(title, c);
				c.gridy++;
				regles.add(ligne1, c);
				c.gridy++;
				regles.add(ligne2, c);
				c.gridy++;
				regles.add(ligne3, c);
				c.gridy++;
				regles.add(ligne4, c);
				
				
				texte.add(regles, BorderLayout.NORTH);
				
	JButton retour = new JButton("Retour");
			retour.setPreferredSize(new Dimension(100, 30));
			retour.addActionListener(new Retour());
			
		this.setLayout(new BorderLayout());
		this.add(texte, BorderLayout.NORTH);
		this.add(retour, BorderLayout.SOUTH);
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
