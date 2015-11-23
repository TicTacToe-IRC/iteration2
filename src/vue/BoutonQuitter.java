package vue;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class BoutonQuitter extends JButton implements MouseListener {

	private Image background;
	private Image background_hover;

	private BoutonQuitter() {
		super();
		init();
	}
	
	private static BoutonQuitter Instance = new BoutonQuitter(); 

	public void init(){
       this.background = ResourceLoader.getImage("boutons/Quitter.png");
       this.background_hover = ResourceLoader.getImage("boutons/Quitter_.png");
		this.addMouseListener(this);
	}

	@Override
	public void paintComponent(Graphics g){
	    if(background!=null){
	        Graphics2D g2d = (Graphics2D)g;
	        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        g2d.drawImage(background, 0, 0, getWidth(), getHeight(), null);
	    }
	}
	
	public static BoutonQuitter getInstance(){
		return Instance;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Graphics2D g2d = (Graphics2D)this.getGraphics();
		g2d.clearRect( 0, 0, getWidth(), getHeight());
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(background_hover, 0, 0, getWidth(), getHeight(), null);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Graphics2D g2d = (Graphics2D)this.getGraphics();
		g2d.clearRect( 0, 0, getWidth(), getHeight());
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(background, 0, 0, getWidth(), getHeight(), null);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}