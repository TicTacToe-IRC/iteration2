package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controler.TicTacToeControler;
import model.Couleur;

public class ParamGUI extends JPanel {
	private JLabel jl, jl1, jl2, jl3, jl4;
	private JTextField nom;
	private JPanel jp1 = new JPanel(), jp2 = new JPanel(), jp3 = new JPanel();  
	private JTextField nom1 = new JTextField("Joueur 1"), 
			nom2 = new JTextField("Joueur 2"); 
	private JComboBox colorList1 = new JComboBox();
	private JComboBox colorList2 = new JComboBox();
	private Object lastItem1, lastItem2;
	private int lastPosition1, lastPosition2;
	private JPanel couleur1 = new JPanel(), couleur2 = new JPanel();
	private JTextField attaque1 = new JTextField(), attaque2 = new JTextField();
	JCheckBox 	gagner;
	private Image background;
	
	private JFrame parent;
	private TicTacToeControler controler;
	
	private List<Couleur> couleurs = 
		Arrays.asList(
			new Couleur("Blanc",255,255,255),
			new Couleur("Noir",0,0,0),
			new Couleur("Rouge",255,0,0),
			new Couleur("Vert",0,255,0),
			new Couleur("Bleu",0,0,255),
			new Couleur("Marron",88,41,0),
			new Couleur("Gris",96,96,96),
			new Couleur("Jaune",255,255,0),
			new Couleur("Orange",237,127,16),
			new Couleur("Rose",253,108,158),
			new Couleur("Violet",102,0,153)
		);
	
	public ParamGUI(JFrame parent, TicTacToeControler controler) {
		this.parent = parent;
		this.controler = controler;
		init();
	}
	
	public void init() {
		
       this.background = ResourceLoader.getImage("Gris.jpg");
		
		jl = new JLabel("");
		jl1 = new JLabel("");
		jl2 = new JLabel("");
		jl3 = new JLabel("");
		
		this.add(jl);
		
		//this.setLayout(new BorderLayout());
		
		/* Joueur 1 */
	
		nom1.setPreferredSize(new Dimension(150,30));
		/*
		JPanel b1 = new JPanel();
		 b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
		 b1.add(nom1);
		 b1.add(valider);
		 
		JPanel b2 = new JPanel();
		 b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
		 jl = jl1;
		 b2.add(jl);*/
		 
		//jp1.setLayout(new BoxLayout(jp1, BoxLayout.PAGE_AXIS));
		jl = jl1;
		nom = nom1;
		
		jp1.setLayout(new BoxLayout(jp1,BoxLayout.PAGE_AXIS));
		jp2.setLayout(new BoxLayout(jp2,BoxLayout.PAGE_AXIS));

		jp1.add(nom1);
		jp1.add(jl);
		
		/* Joueur 2 */
		nom2.setPreferredSize(new Dimension(150,30));
		
		jl = jl2;
		nom = nom2;
		jp2.add(nom2);
		jp2.add(jl);
		
		Collections.sort(couleurs);
		
		addColors(colorList1);
		addColors(colorList2);
		
		colorList1.setSelectedIndex(0);
		lastItem2 = colorList2.getItemAt(0);
		lastPosition2 = 0;
		colorList2.removeItemAt(0);
		colorList2.setSelectedIndex(0);
		lastItem1 = colorList1.getItemAt(1);
		lastPosition1 = 1;
		colorList1.removeItemAt(1);
		
		couleur1.setBackground(getColorAt(0));
		couleur2.setBackground(getColorAt(1));
		
		//couleur1.setText(couleurs.get(0).getNom());
		//couleur2.setText(couleurs.get(1).getNom());
		
		colorList1.addActionListener(new PickList(1));
		colorList2.addActionListener(new PickList(2));
		
		jl3 = new JLabel("Phrase perso :");
		jl4 = new JLabel("Phrase perso :");
		
		jp1.add(colorList1);
		jp1.add(couleur1);
		jp1.add(jl3);
		jp1.add(attaque1);
		
		jp2.add(colorList2);
		jp2.add(couleur2);
		jp2.add(jl4);
		jp2.add(attaque2);

		JPanel 	config = new JPanel();
				config.setLayout(new BoxLayout(config,BoxLayout.LINE_AXIS));
				config.add(jp1);
				config.add(jp2);
		
		
		/* Validation */
		
		//JButton valider = new JButton("Valider");
				JButton valider = BoutonValider.getInstance();
				valider.setPreferredSize(new Dimension(100, 30));
				if(valider.getActionListeners().length != 0){
					valider.removeActionListener(valider.getActionListeners()[0]);
					valider.addActionListener(new Valider());
				} else {
					valider.addActionListener(new Valider());
				}
		
		//JButton retour = new JButton("Retour");
				JButton retour = BoutonRetour.getInstance();
				retour.setPreferredSize(new Dimension(100, 30));
				if(retour.getActionListeners().length == 0){
					retour.addActionListener(new Retour());
				}
		
		/* Victoire */
		
		gagner = new JCheckBox ("Option : \"J'ai gagné\"");
					//gagner.setPreferredSize(new Dimension(200, 30));
					gagner.addActionListener(new Gagner());
					gagner.setToolTipText("Si coché, les joueurs devront annoncer qu'ils ont gagné pour gagner.");

		JPanel 	gbouton = new JPanel();
				gbouton.add(gagner);
		
		
		JPanel 	jpbouton = new JPanel();
				jpbouton.setLayout(new GridBagLayout());
				   
			    GridBagConstraints gbc = new GridBagConstraints();
			    
			    JPanel esp = new JPanel();
			    esp.setOpaque(false);
			    
			    gbc.gridx = 0;
			    gbc.gridy = 0;
			    gbc.ipady = 10;
			    gbc.ipadx = 50;
				jpbouton.add(retour,gbc);
				gbc.gridx = 1;
			    gbc.gridy = 0;
				jpbouton.add(esp,gbc);
				gbc.gridx = 2;
			    gbc.gridy = 0;
			    gbc.ipady = 10;
			    gbc.ipadx = 50;
				jpbouton.add(valider,gbc);
		
		jp3.setLayout(new BoxLayout(jp3, BoxLayout.PAGE_AXIS));
		jp3.add(gbouton);
		jp3.add(jpbouton);
		
		
		/* On cache le background de tous les JPanels */
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		config.setOpaque(false);
		gbouton.setOpaque(false);
		jpbouton.setOpaque(false);
		gagner.setOpaque(false);
		
		//this.add(config, BorderLayout.CENTER);
		//this.add(jp3, BorderLayout.SOUTH);
		JPanel  general = new JPanel();
				general.setLayout(new BoxLayout(general, BoxLayout.PAGE_AXIS));
				general.add(config);
				general.add(jp3);
				general.setOpaque(false);
		this.add(general, BorderLayout.CENTER);
		
	}
	
	public void addColors(JComboBox cb) {
		for (int i = 0; i<couleurs.size(); i++) {
			cb.addItem((String)couleurs.get(i).getNom());
		}
	}
	
	public Color getColorAt(int index) {
		Couleur c = couleurs.get(index);
		return new Color(c.getR(),c.getG(),c.getB());
	}
	
	public int getIndexOfColor(Object item) {
		String coul = (String) item;
		int i = 0;
		while (i<couleurs.size() && couleurs.get(i).getNom() != coul) {
			i++;
		}
		return i;
	}
	
	public class PickList implements ActionListener{
		private JComboBox cbA, cbO;
		private Integer lastPosition;
		private Object lastItem;
		private JPanel couleur;
		public PickList(int ind) {
			if (ind == 1) {
				this.cbA = colorList1;
				this.cbO = colorList2;
				this.lastItem = lastItem2;
				this.lastPosition = lastPosition2;
				this.couleur = couleur1;
			}
			else {
				this.cbA = colorList2;
				this.cbO = colorList1;
				this.lastItem = lastItem1;
				this.lastPosition = lastPosition1;
				this.couleur = couleur2;
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Object item = cbA.getSelectedItem();
			int index = getIndexOfColor(item);
		    couleur.setBackground(getColorAt(index));
		    //couleur.setText(couleurs.get(index).getNom());
		    cbO.insertItemAt((String)lastItem,lastPosition);
			cbO.removeItem(item);
			lastItem = item;
			lastPosition = index;
		}
		
	}
	
	public class Valider implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			controler.setNomJoueur1(nom1.getText());
			controler.setNomJoueur2(nom2.getText());
			controler.setCouleurJoueur1(couleurs.get(getIndexOfColor(colorList1.getSelectedItem())));
			controler.setCouleurJoueur2(couleurs.get(getIndexOfColor(colorList2.getSelectedItem())));
			controler.setDeviseJoueur1(attaque1.getText());
			controler.setDeviseJoueur2(attaque2.getText());
			controler.setAnnonce(gagner.isSelected());
			controler.initPlateau();
			((MainFrame) parent).goTo("jeu");
		}
	}
	
	public class Retour implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			((MainFrame) parent).goTo("menu");
		}
	}
	
	public class Gagner implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {

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