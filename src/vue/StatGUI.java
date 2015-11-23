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

public class StatGUI extends JPanel {
	private Image background;
	Panel panel;
	private JFrame parent;
		
	public StatGUI(JFrame parent){		
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

		
		JPanel jpStat = new JPanel();
		jpStat.setLayout(new GridBagLayout());
		jpStat.setOpaque(false);
		
		JScrollPane jsp = new JScrollPane(jpStat);
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false);
		//jpStat.setLayout(new BoxLayout(jpStat, BoxLayout.PAGE_AXIS));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=0;
		
		JPanel jpStatJoueur;
		
		StatRecord stat = null;
		try {
			stat = StatRecord.getInstance();
			for(String s : stat.getNomsJoueurs()){
				TitledBorder border = new TitledBorder(s);
				//border.setTitleFont( border.getTitleFont().deriveFont(Font.BOLD + Font.ITALIC) );
				
				jpStatJoueur = new JPanel();
				jpStatJoueur.setBorder(border);
				jpStatJoueur.setLayout(new GridLayout(0,2));
				
					JLabel labelNbParties = new JLabel("Nombre de parties jouées: ");
					JLabel labelNbPartiesG = new JLabel("Nombre de parties gagnées: ");
					JLabel labelNbPartiesP = new JLabel("Nombre de parties perdues: ");
					JLabel labelNbPartiesN = new JLabel("Nombre de parties nulles: ");
					
					JLabel valNbParties = new JLabel(""+stat.getNbPartiesJouees(s));
					JLabel valNbPartiesG = new JLabel(""+stat.getNbPartiesGagnees(s));
					JLabel valNbPartiesP = new JLabel(""+stat.getNbPartiesPerdues(s));
					JLabel valNbPartiesN = new JLabel(""+stat.getNbPartiesNulles(s));
					
				jpStatJoueur.add(labelNbParties);
				jpStatJoueur.add(valNbParties);
				jpStatJoueur.add(labelNbPartiesG);
				jpStatJoueur.add(valNbPartiesG);
				jpStatJoueur.add(labelNbPartiesP);
				jpStatJoueur.add(valNbPartiesP);
				jpStatJoueur.add(labelNbPartiesN);
				jpStatJoueur.add(valNbPartiesN);
				
				jpStat.add(jpStatJoueur,c);
				c.gridy++;
				JPanel esp = new JPanel();
				esp.setOpaque(false);
				jpStat.add(esp,c);
				c.gridy++;
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	JButton retour = new JButton("Retour");
			retour.setPreferredSize(new Dimension(100, 30));
			retour.addActionListener(new Retour());
			
		this.setLayout(new BorderLayout());
		this.add(jsp, BorderLayout.CENTER);
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
