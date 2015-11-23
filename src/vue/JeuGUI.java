package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Couleur;
import model.StatRecord;
import controler.TicTacToeControler;

public class JeuGUI extends JPanel implements java.util.Observer{
	private TicTacToeControler controler;
	private JFrame parent;
	private boolean monTour = true;
	
	private static int compteTour = 0;
	
	JPanel 	panelJ1, panelJ2;
	
	JButton bFinTour1 = new JButton("Fin du tour");
	JButton bGagner1 = new JButton("J'ai gagné");
	JButton bFinTour2 = new JButton("Fin du tour");
	JButton bGagner2 = new JButton("J'ai gagné");
	
	JButton boutonMenu;
	JButton boutonRejouer;
	JButton boutonNew;
	
	private PlateauGUI pgui;
	
	public JeuGUI(JFrame jf, TicTacToeControler controler){
		this.parent = jf;
		this.controler = controler;


		pgui = new PlateauGUI(parent, this, this.controler);
		this.controler.initPlateau();
		if((compteTour%2!=0 && this.controler.getIdJoueur()==1) || (compteTour%2==0 && this.controler.getIdJoueur()!=1)){
			this.controler.switchJoueur();
		}
		init();
		compteTour++;
	}
	
	
	public void init(){
		setLayout(new FlowLayout(0 , 250, 250));

		JPanel 	jp_east = new JPanel();
				jp_east.setPreferredSize(new Dimension(200,400));
				jp_east.setLayout(new BoxLayout(jp_east, BoxLayout.PAGE_AXIS));
		
		boutonMenu = new JButton("Menu");
				boutonMenu.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						((MainFrame) parent).goTo("menu");
					}
				});
		
		boutonRejouer = new JButton("Revanche");
				boutonRejouer.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						((MainFrame) parent).goTo("jeu");
					}
				});
			//boutonRejouer.addActionListener(new Revanche());
				
		boutonNew = new JButton("Changer de joueurs");
				boutonNew.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						((MainFrame) parent).goTo("param");
					}
				});
				
		JPanel 	panelMenu = new JPanel();
				panelMenu.add(boutonMenu, BorderLayout.CENTER);
				panelMenu.setPreferredSize(boutonMenu.getSize());
		panelJ1 = new JPanel();
				panelJ1.setLayout(new BorderLayout());
				panelJ1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(),controler.getNomJoueur1() ));
		panelJ2 = new JPanel();
				panelJ2.setLayout(new BorderLayout());
				panelJ2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),controler.getNomJoueur2()));
				
				
				if(controler.getIdJoueur()==1){
					panelJ1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(),controler.getNomJoueur1()));
					panelJ2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),controler.getNomJoueur2() ));
						bFinTour1.setEnabled(false);
						bGagner1.setEnabled(false);
						bFinTour2.setEnabled(false);
						bGagner2.setEnabled(false);
				} else {
					panelJ1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),controler.getNomJoueur1() ));
					panelJ2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(),controler.getNomJoueur2()));
						bFinTour1.setEnabled(false);
						bGagner1.setEnabled(false);
						bFinTour2.setEnabled(false);
						bGagner2.setEnabled(false);
				}
				
		JLabel devise1 = new JLabel("<html><i>\""+controler.getDeviseJoueur1()+"\"</i></html>");
		JLabel devise2 = new JLabel("<html><i>\""+controler.getDeviseJoueur2()+"\"</i></html>");
				
		
			
		Couleur cj1 = controler.getCouleurJoueur1();
		Couleur cj2 = controler.getCouleurJoueur2();
		
		JPanel couleurJ1, couleurJ2;
		couleurJ1 = new JPanel();
		couleurJ1.setBackground(new Color(cj1.getR(),cj1.getG(),cj1.getB()));
		couleurJ1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		couleurJ2 = new JPanel();
		couleurJ2.setBackground(new Color(cj2.getR(),cj2.getG(),cj2.getB()));
		couleurJ2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		JLabel lcolor1 = new JLabel(cj1.getNom()+"  ");
		lcolor1.setOpaque(false);
		JLabel lcolor2 = new JLabel(cj2.getNom()+"  ");
		lcolor2.setOpaque(false);
		
		JPanel pcolor1 = new JPanel();
		pcolor1.setLayout(new BoxLayout(pcolor1, BoxLayout.LINE_AXIS));
		pcolor1.add(lcolor1);
		pcolor1.add(couleurJ1);
		JPanel pcolor2 = new JPanel();
		pcolor2.setLayout(new BoxLayout(pcolor2, BoxLayout.LINE_AXIS));
		pcolor2.add(lcolor2);
		pcolor2.add(couleurJ2);
		

		bFinTour1.addActionListener(new FinTour());
		bGagner1.addActionListener(new Gagner());
		bFinTour2.addActionListener(new FinTour());
		bGagner2.addActionListener(new Gagner());
		
		JPanel panelAnnonce1 = new JPanel();
		panelAnnonce1.setBackground(null);
		panelAnnonce1.setLayout(new BoxLayout(panelAnnonce1, BoxLayout.PAGE_AXIS));
		panelAnnonce1.add(bFinTour1);
		panelAnnonce1.add(bGagner1);
		
		JPanel panelAnnonce2 = new JPanel();
		panelAnnonce2.setBackground(null);
		panelAnnonce2.setLayout(new BoxLayout(panelAnnonce2, BoxLayout.PAGE_AXIS));
		panelAnnonce2.add(bFinTour2);
		panelAnnonce2.add(bGagner2);
		
		panelJ1.add(pcolor1, BorderLayout.NORTH);
		panelJ2.add(pcolor2, BorderLayout.NORTH);	
		
		if(controler.isAnnonce()){
			panelJ1.add(panelAnnonce1, BorderLayout.CENTER);
			panelJ2.add(panelAnnonce2, BorderLayout.CENTER);	
		}
		
		panelJ1.add(devise1, BorderLayout.SOUTH);
		panelJ2.add(devise2, BorderLayout.SOUTH);
		
		jp_east.add(boutonMenu);
		jp_east.add(boutonRejouer);
		jp_east.add(boutonNew);
		jp_east.add(panelJ1);
		jp_east.add(panelJ2);
		
		this.setLayout(new BorderLayout());
		this.add(pgui,BorderLayout.CENTER);
		this.add(jp_east,BorderLayout.EAST);
		

		boutonSupVisible(false);
	}
	
	public void switchPanel(){
		if(controler.getIdJoueur()==1){
			panelJ1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(),controler.getNomJoueur1()));
			panelJ2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),controler.getNomJoueur2() ));
				bFinTour1.setEnabled(false);
				bGagner1.setEnabled(false);
				bFinTour2.setEnabled(false);
				bGagner2.setEnabled(false);
			panelJ1.repaint();
			panelJ1.revalidate();
			panelJ2.repaint();
			panelJ2.revalidate();
		} else {
			panelJ1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),controler.getNomJoueur1() ));
			panelJ2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(),controler.getNomJoueur2()));
				bFinTour1.setEnabled(false);
				bGagner1.setEnabled(false);
				bFinTour2.setEnabled(false);
				bGagner2.setEnabled(false);
			panelJ1.repaint();
			panelJ1.revalidate();
			panelJ2.repaint();
			panelJ2.revalidate();
		}
	}
	
	public boolean isMonTour(){
		return monTour;
	}
	
	public void setMonTour(boolean b){
		monTour = b;
	}
	
	public void pionPoser(){
		if(controler.getIdJoueur()==1){
			bFinTour1.setEnabled(true);
			bGagner1.setEnabled(true);
			bFinTour2.setEnabled(false);
			bGagner2.setEnabled(false);
		} else {
			bFinTour1.setEnabled(false);
			bGagner1.setEnabled(false);
			bFinTour2.setEnabled(true);
			bGagner2.setEnabled(true);
		}
	}
	
	public class FinTour implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			controler.switchJoueur();
			switchPanel();
			pgui.hideCone();
			monTour=true;
		}
	}
	
	/*public class Revanche implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			((MainFrame) parent).goTo("jeu");
		}
	}*/
	
	public class Gagner implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			bFinTour1.setEnabled(false);
			bGagner1.setEnabled(false);
			bFinTour2.setEnabled(false);
			bGagner2.setEnabled(false);
			
			pgui.setFinJeu(true);
			
			boutonSupVisible(true);
			int r = controler.partieFinie(pgui.getLast_x(), pgui.getLast_z(), pgui.getLast_y(), pgui.getLast_id());
			try {
				StatRecord stat = StatRecord.getInstance();
				stat.putResultatPartie(controler.getNomJoueur1(), controler.getNomJoueur2(), r);
				stat.store();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
					
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String txtPopup =null;
		   if(r==pgui.getLast_id()){
			   if(r==1){
				   txtPopup="Victoire de "+controler.getNomJoueur1()+" !";
			   } else {
				   txtPopup="Victoire de "+controler.getNomJoueur2()+" !";
			   }
		   } else {
			   if(pgui.getLast_id()==1){
				   txtPopup="<html>Victoire de "+controler.getNomJoueur2()+" !<br/>Mauvaise annonce de "+controler.getNomJoueur1()+"...</html>";
			   } else {
				   txtPopup="<html>Victoire de "+controler.getNomJoueur1()+" !<br/>Mauvaise annonce de "+controler.getNomJoueur2()+"...</html>";
			   }
		   }
		   Popup jd = new Popup(parent,"Partie finie",txtPopup,true);
		   jd.showPopup();
		   //controler.initPlateau();
		}
	}
	
	public void boutonSupVisible(boolean b){
		boutonRejouer.setEnabled(b);
		boutonNew.setEnabled(b);
	}
	
	public int getCompteTour(){
		return this.compteTour;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub	
	}
}
