package vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Popup extends JDialog {
	private JFrame parent;
	
	public Popup(JFrame parent, String title, String txt, boolean modal){
		super(parent, title, modal);
		this.parent = parent;
		this.setSize(500, 100);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	    this.initComponent(txt);
	}
	
	private void initComponent(String txt){
		JLabel jl = new JLabel("<html><br/><b>"+txt+"</b></html>");
		JButton brejouer = new JButton("Revanche");
		brejouer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((MainFrame)parent).goTo("jeu");
				closePopup();
			}
		});
		JButton bnew = new JButton("Changer de joueurs");
		bnew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((MainFrame)parent).goTo("param");
				closePopup();
			}
		});
		JButton bmenu = new JButton("Menu");
		bmenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((MainFrame)parent).goTo("menu");
				closePopup();
			}
		});
		JButton bclose = new JButton("Continuer");
		bclose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closePopup();
			}
		});
		
		JPanel 	panelLabel = new JPanel();
				panelLabel.setLayout(new BorderLayout());
		
		JPanel 	panelBouton = new JPanel();
				panelBouton.setLayout(new BoxLayout(panelBouton, BoxLayout.LINE_AXIS));

		panelLabel.add(jl, BorderLayout.CENTER);
		panelBouton.add(brejouer);
		panelBouton.add(bnew);
		panelBouton.add(bmenu);
		panelBouton.add(bclose);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(jl, BorderLayout.CENTER);
		this.getContentPane().add(panelBouton, BorderLayout.SOUTH);
	}
	
	public void showPopup(){
		this.setVisible(true);   
	}
	
	public void closePopup(){
		this.dispose();  
	}
}
