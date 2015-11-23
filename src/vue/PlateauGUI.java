package vue;



//Etape 1 :
//Importation des packages Java 2
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Switch;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.Color4f;
import javax.vecmath.Vector3f;









import model.StatRecord;



//Etape 2 :
//Importation des packages Java 3D
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.picking.PickResult;
import com.sun.j3d.utils.picking.behaviors.PickRotateBehavior;
import com.sun.j3d.utils.picking.behaviors.PickTranslateBehavior;
import com.sun.j3d.utils.picking.behaviors.PickZoomBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;

import controler.TicTacToeControler;

public class PlateauGUI extends JPanel implements MouseListener, MouseMotionListener  {
	TicTacToeControler controler;
	JFrame parent;
	
	private PickCanvas pickCanvas;
	private LinkedList<LinkedList> cylinderList;
	private LinkedList<LinkedList> coneList1;
	private LinkedList<LinkedList> coneList2;
	private Map<Sphere,Switch> mapSS;
	private Switch[][][] tabSphere1;
	private Switch[][][] tabSphere2;
	private Canvas3D canvas3D;
	private SimpleUniverse simpleU;
	private JPanel jeuGui;
	
	private int last_x;
	private int last_y;
	private int last_z;
	private int last_id;
	
	Appearance appPlateau = new Appearance();
	Appearance appBaton = new Appearance();
	 Appearance appFond = new Appearance();
	 
	 private boolean finJeu = false;
	

public PlateauGUI(JFrame parent, JPanel jeuGui, TicTacToeControler controler) {
	this.parent = parent;
	this.jeuGui = jeuGui;
	this.controler = controler;
	this.finJeu = false;
	
 this.setLayout(new BorderLayout());

 // Etape 3 :
 // Creation du Canvas 3D
canvas3D =
     new Canvas3D(SimpleUniverse.getPreferredConfiguration());

 canvas3D.addMouseListener(this);
 canvas3D.addMouseMotionListener(this);

 this.add(canvas3D, BorderLayout.CENTER);

 simpleU = new SimpleUniverse(canvas3D);
 simpleU.getViewingPlatform().setNominalViewingTransform();
 BranchGroup scene = createSceneGraph(canvas3D);
 scene.compile();
	
 simpleU.addBranchGraph(scene);
}

public BranchGroup createSceneGraph(Canvas3D canvas) {
	 BranchGroup parent = new BranchGroup();
	 pickCanvas = new PickCanvas(canvas, parent);
	 pickCanvas.setMode(PickCanvas.BOUNDS);
	 
	 
	
	 // Creation du comportement de rotation une fois l'objet selectionne
	 PickRotateBehavior pickRotate =
	     new PickRotateBehavior(parent,
	                            canvas,
	                            new BoundingSphere());
	 parent.addChild(pickRotate);
	
	 // Creation du comportement de translation une fois l'objet selectionne
	 PickTranslateBehavior pickTranslate =
	     new PickTranslateBehavior(parent,
	                               canvas,
	                               new BoundingSphere());
	 //parent.addChild(pickTranslate);
	
	 // Creation du comportement de zoom une fois l'objet selectionne
	 PickZoomBehavior pickZoom =
	     new PickZoomBehavior(parent,
	                          canvas,
	                          new BoundingSphere());
	 //parent.addChild(pickZoom);
	 
	 
	 // Apparence

	 File g = new File("");
	 String path = g.getAbsolutePath();
	 
	 File fichier_bois1 = new File(path+"/images/bois1.jpg");
	 File fichier_bois2 = new File(path+"/images/bois2.jpg");
	 File fichier_bois3 = new File(path+"/images/bois3.jpg");
	 File fichier_joueur1 = new File(path+"/images/"+controler.getCouleurJoueur1().getNom()+".jpg");
	 File fichier_joueur2 = new File(path+"/images/"+controler.getCouleurJoueur2().getNom()+".jpg");
	 
	 BufferedImage image_bois1 = null;
	 BufferedImage image_bois2 = null;
	 BufferedImage image_bois3 = null;
	 BufferedImage image_joueur1 = null;
	 BufferedImage image_joueur2 = null;

	    	image_bois1 = ResourceLoader.getImage("bois1.jpg");
	    	image_bois2 = ResourceLoader.getImage("bois2.jpg");
	    	image_bois3 = ResourceLoader.getImage("bois3.jpg");
	    	image_joueur1 = ResourceLoader.getImage(controler.getCouleurJoueur1().getNom()+".jpg");
	    	image_joueur2 = ResourceLoader.getImage(controler.getCouleurJoueur2().getNom()+".jpg");
	 
	 
	 Appearance appBoule_j1 = new Appearance();
	 			appBoule_j1.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
	 			appBoule_j1.setCapability(Appearance.ALLOW_TEXTURE_READ);
	 Appearance appBoule_j2 = new Appearance();
		 		appBoule_j2.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
		 		appBoule_j2.setCapability(Appearance.ALLOW_TEXTURE_READ);

	
	 Texture texture_bois1 = new TextureLoader(image_bois1).getTexture();
			 texture_bois1.setBoundaryModeS(Texture.WRAP);
			 texture_bois1.setBoundaryModeT(Texture.WRAP);
			 texture_bois1.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.0f, 0.0f));
			 
	 
	 Texture texture_bois2 = new TextureLoader(image_bois2).getTexture();
			 texture_bois2.setBoundaryModeS(Texture.WRAP);
			 texture_bois2.setBoundaryModeT(Texture.WRAP);
			 texture_bois2.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.0f, 0.0f));
			 
			 
	Texture texture_bois3 = new TextureLoader(image_bois3).getTexture();
			texture_bois3.setBoundaryModeS(Texture.WRAP);
			texture_bois3.setBoundaryModeT(Texture.WRAP);
			texture_bois3.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.0f, 0.0f));
			
			
	Texture texture_j1 = new TextureLoader(image_joueur1).getTexture();
			texture_j1.setBoundaryModeS(Texture.WRAP);
			texture_j1.setBoundaryModeT(Texture.WRAP);
			texture_j1.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.0f, 0.0f));
			

	Texture texture_j2 = new TextureLoader(image_joueur2).getTexture();
			texture_j2.setBoundaryModeS(Texture.WRAP);
			texture_j2.setBoundaryModeT(Texture.WRAP);
			texture_j2.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.0f, 0.0f));
			

	TextureAttributes texAttr = new TextureAttributes();
	texAttr.setTextureMode(TextureAttributes.MODULATE);
	
	appPlateau.setTexture(texture_bois1);
	appPlateau.setTextureAttributes(texAttr);
	
	appBaton.setTexture(texture_bois2);
	appBaton.setTextureAttributes(texAttr);
	
	appBoule_j1.setTexture(texture_j1);
	appBoule_j1.setTextureAttributes(texAttr);
	
	appBoule_j2.setTexture(texture_j2);
	appBoule_j2.setTextureAttributes(texAttr);
	
	appFond.setTexture(texture_bois3);
	appFond.setTextureAttributes(texAttr);
	 
	 // Plateau
	TransformGroup generalTG = new TransformGroup();
	generalTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	generalTG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	generalTG.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
	
	Transform3D transformGeneral = new Transform3D();
	Vector3f vectorGeneral = new Vector3f( 0.0f, 0.0f, -2.0f);
	transformGeneral.setTranslation(vectorGeneral);
	generalTG.setTransform(transformGeneral);
	
	 TransformGroup plateauTG = new TransformGroup();
	 
	 Float x_box = 0.6f;
	 Float y_box = 0.025f;
	 Float z_box = 0.6f;//0.6
	 
	 Transform3D transform = new Transform3D();
	 Vector3f vector = new Vector3f( 0.0f, -0.45f, 0.0f);
	 //Vector3f vector = new Vector3f( 0.0f, -0.45f, -4.0f);
	 transform.setTranslation(vector);
	 plateauTG.setTransform(transform);
	 
	 Box boxPlateau = new Box(x_box, y_box, z_box, Box.GENERATE_TEXTURE_COORDS, appPlateau);
	 boxPlateau.setPickable(false);
	 plateauTG.addChild(boxPlateau);
	 

	 
	 cylinderList = new LinkedList<LinkedList>();
	 coneList1 = new LinkedList<LinkedList>();
	 coneList2 = new LinkedList<LinkedList>();
	 
	 for(int i=0; i<4; i++){
		 LinkedList<Cylinder> list = new LinkedList<Cylinder>();
		 LinkedList<Switch> list21 = new LinkedList<Switch>();
		 LinkedList<Switch> list22 = new LinkedList<Switch>();
		 for(int j=0; j<4; j++){
			 Cylinder baton = new Cylinder(0.05f,1.2f, Cylinder.GENERATE_TEXTURE_COORDS,appBaton);
			 list.add(baton);
		      TransformGroup tg = new TransformGroup();
		      transform = new Transform3D();
		      Float x_cyl = i*0.4f - x_box;
		 	  Float y_cyl = 0.50f;
		 	  Float z_cyl = j*0.4f - z_box;
		      vector = new Vector3f( x_cyl, y_cyl, z_cyl);
		      transform.setTranslation(vector);
		      tg.setTransform(transform);
		      tg.addChild(baton);
		      plateauTG.addChild(tg);
		      
		      Cone cone1 = new Cone(0.05f,-0.05f, Cone.GENERATE_TEXTURE_COORDS,appBoule_j1);
		      cone1.setPickable(false);
		      Switch visibleCone1 = new Switch();
		      visibleCone1.addChild(cone1);
		      visibleCone1.setCapability(Switch.ALLOW_SWITCH_READ);
		      visibleCone1.setCapability(Switch.ALLOW_SWITCH_WRITE);
		      visibleCone1.setCapability(Switch.ALLOW_CHILDREN_READ);
		      visibleCone1.setCapability(Switch.ALLOW_CHILDREN_WRITE);
		      visibleCone1.setWhichChild(Switch.CHILD_NONE);
		      
		      Cone cone2 = new Cone(0.05f,-0.05f, Cone.GENERATE_TEXTURE_COORDS,appBoule_j2);
		      cone2.setPickable(false);
		      Switch visibleCone2 = new Switch();
		      visibleCone2.addChild(cone2);
		      visibleCone2.setCapability(Switch.ALLOW_SWITCH_READ);
		      visibleCone2.setCapability(Switch.ALLOW_SWITCH_WRITE);
		      visibleCone2.setCapability(Switch.ALLOW_CHILDREN_READ);
		      visibleCone2.setCapability(Switch.ALLOW_CHILDREN_WRITE);
		      visibleCone2.setWhichChild(Switch.CHILD_NONE);
		      
		      list21.add(visibleCone1);
		      list22.add(visibleCone2);
		      TransformGroup tg2 = new TransformGroup();
			  transform = new Transform3D();
			  Float x_cone = x_cyl;
		 	  Float y_cone = 1.25f;
		 	  Float z_cone = z_cyl;
		      vector = new Vector3f( x_cone, y_cone, z_cone);
		      transform.setTranslation(vector);
		      tg2.setTransform(transform);
		      tg2.addChild(visibleCone1);
		      tg2.addChild(visibleCone2);
		      plateauTG.addChild(tg2);
		 }
		 cylinderList.add(list);
		 coneList1.add(list21);
		 coneList2.add(list22);
	 }
	 
	 
	 
	 TransformGroup sphereTG = new TransformGroup();
	 transform = new Transform3D();
	 vector = new Vector3f( 0.0f, 0.15f, 0.0f);
	 transform.setTranslation(vector);
	 sphereTG.setTransform(transform);
	 
	 mapSS = new HashMap<Sphere, Switch>();
	 tabSphere1 = new Switch[4][4][4];
	 tabSphere2 = new Switch[4][4][4];
	 
	 for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			for (int k = 0; k < 4; k++) {
				
				Sphere sphere = new Sphere(0.14f, Sphere.GENERATE_TEXTURE_COORDS, appBoule_j1);
				sphere.setPickable(false);
				Switch visibleSphere = new Switch();
				visibleSphere.addChild(sphere);
				visibleSphere.setCapability(Switch.ALLOW_SWITCH_READ);
				visibleSphere.setCapability(Switch.ALLOW_SWITCH_WRITE);
				visibleSphere.setCapability(Switch.ALLOW_CHILDREN_READ);
				visibleSphere.setCapability(Switch.ALLOW_CHILDREN_WRITE);
				visibleSphere.setWhichChild(Switch.CHILD_NONE);
				//visibleSphere.setWhichChild(Switch.CHILD_ALL);
				tabSphere1[i][j][k]=visibleSphere;
				
				Sphere sphere2 = new Sphere(0.14f, Sphere.GENERATE_TEXTURE_COORDS, appBoule_j2);
				sphere2.setPickable(false);
				Switch visibleSphere2 = new Switch();
				visibleSphere2.addChild(sphere2);
				visibleSphere2.setCapability(Switch.ALLOW_SWITCH_READ);
				visibleSphere2.setCapability(Switch.ALLOW_SWITCH_WRITE);
				visibleSphere2.setCapability(Switch.ALLOW_CHILDREN_READ);
				visibleSphere2.setCapability(Switch.ALLOW_CHILDREN_WRITE);
				visibleSphere2.setWhichChild(Switch.CHILD_NONE);
				tabSphere2[i][j][k]=visibleSphere2;
				
				//if(i==0 && j==3 && k==0){
				//	System.out.println(sphere);
				//}
				TransformGroup tg = new TransformGroup();
				transform = new Transform3D();
				vector = new Vector3f(i * 0.4f-0.6f, k*0.25f,
						j * 0.4f-0.6f);
				transform.setTranslation(vector);
				tg.setTransform(transform);
				tg.addChild(visibleSphere);
				tg.addChild(visibleSphere2);
				sphereTG.addChild(tg);
			}
		}
	}
		plateauTG.addChild(sphereTG);

	 generalTG.addChild(plateauTG);
	 parent.addChild(generalTG);  
	 
	 // Background
	 Box boxBackground = new Box(15f, 15f, 0.1f, Box.GENERATE_TEXTURE_COORDS, appFond);
	 boxBackground.setPickable(false);
	 TransformGroup tg = new TransformGroup();
	 transform = new Transform3D();
	 vector = new Vector3f(0f, 0f, -20f);
	 transform.setTranslation(vector);
		tg.setTransform(transform);
		tg.addChild(boxBackground);
	 parent.addChild(tg);	
	 
	
	 return parent;
	}

	public void actionPerformed(ActionEvent e ) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(isMonTour() && !finJeu){
			pickCanvas.setShapeLocation(e);
		    PickResult result = pickCanvas.pickClosest();
	
		    if (result == null) {
	
		       System.out.println("Nothing picked");
	
		    } else {
	
		       Primitive p = (Primitive)result.getNode(PickResult.PRIMITIVE);
		       Shape3D s = (Shape3D)result.getNode(PickResult.SHAPE3D);
	
		       if (p != null) {
		    	   if(p.getClass().getSimpleName().equals("Cylinder")){
		    		   int x=-1, y=-1;
		    		   int i = 0;
		    		   for(LinkedList<Cylinder> l : cylinderList){
		    			   if(l.indexOf(((Cylinder)p)) != -1){
		    				   x=i;
		    				   y=l.indexOf(((Cylinder)p));
		    			   }
		    			   i++;
		    		   }
		    		   //System.out.println("x: "+x+" y: "+y);
	
		    		   int z = controler.placementOk(x, y);
		    		   int idJoueur = controler.getIdJoueur();
		    		   if(z!=-1){

			    		   last_x = x;
			    		   last_y = y;
			    		   last_z = z;
			    		   last_id = idJoueur;
		    			   if(idJoueur==1){
		    				   tabSphere1[x][y][z].setWhichChild(Switch.CHILD_ALL);
		    			   } else {
		    				   tabSphere2[x][y][z].setWhichChild(Switch.CHILD_ALL);
		    			   }
		    			   controler.placerPion(x, z, y);
		    			   
		    			   if(!controler.isAnnonce()){
		    				   int r = controler.partieFinie(x, z, y, idJoueur);
		    				   if(r!=0){
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
		    					   System.out.println("Gagnant: "+r);
		    					   //JOptionPane.showConfirmDialog(this, "Test");
		    					   String txtPopup =null;
		    					   if(r==1){
		    						   txtPopup="Victoire de "+controler.getNomJoueur1()+" !";
		    					   } else if(r==2) {
		    						   txtPopup="Victoire de "+controler.getNomJoueur2()+" !";
		    					   } else if(r==-1) {
		    						   txtPopup="Match nul !";
		    					   }
		    					   finJeu = true;
		    					   ((JeuGUI) jeuGui).boutonSupVisible(true);
		    					   Popup jd = new Popup(parent,"Partie finie",txtPopup,true);
		    					   //Popup jd = ((JeuGUI) jeuGui).makePopup("Partie finie", txtPopup);
		    					   jd.showPopup();
		    					   //controler.resetPlateau();
		    					   /*controler.initPlateau();
		    					   if((((JeuGUI) jeuGui).getCompteTour()%2!=0 && this.controler.getIdJoueur()==1) || (((JeuGUI) jeuGui).getCompteTour()%2==0 && this.controler.getIdJoueur()!=1)){
		    							this.controler.switchJoueur();
		    						}*/
		    				   }
		    				   if(!finJeu){
		    					   controler.switchJoueur();
		    				   }
		    				   
		    				   switchPanel();
			    			   hideCone();
		    			   } else {
		    				   setMonTour(false);
		    				   pionPoser();
		    				   int r = controler.partieFinie(x, z, y, idJoueur);
		    				   if(r==-1){
		    					   finJeu = true;
		    					   ((JeuGUI) jeuGui).boutonSupVisible(true);
		    					   String txtPopup="Match nul !";
		    					   Popup jd = new Popup(parent,"Partie finie",txtPopup,true);
		    					   jd.showPopup();
		    					   //controler.initPlateau();
		    					   
		    					   //controler.resetPlateau();
		    					   //if((((JeuGUI) jeuGui).getCompteTour()%2!=0 && this.controler.getIdJoueur()==1) || (((JeuGUI) jeuGui).getCompteTour()%2==0 && this.controler.getIdJoueur()!=1)){
		    							//this.controler.switchJoueur();
		    						//}
				    			   //hideCone();
			    				   //switchPanel();
		    					   //this.controler.switchJoueur();
		    				   }
		    			   }
		    		   }
		    		   //((Sphere)tabSphere[x][y][0].getChild(0)).getAppearance().getName();
		    		   //((Sphere)tabSphere[x][y][0].getChild(0)).setAppearance(null);
		    		   //System.out.println(((Sphere)tabSphere1[x][y][0].getChild(0)));
		    	   }
	
		    	   if(p.getClass().getSimpleName().equals("Sphere")){
		    		  
		    	   }
		       }
		       /*else if (s != null) {
		    	   System.out.println("s: "+s.getClass().getSimpleName());
		       }*/
	
		    }
		}
		
	}
	
	public void hideCone(){
		for(LinkedList<Switch> cl : coneList1){
			for(Switch scl : cl){
				scl.setWhichChild(Switch.CHILD_NONE);
			}
		}
		for(LinkedList<Switch> cl : coneList2){
			for(Switch scl : cl){
				scl.setWhichChild(Switch.CHILD_NONE);
			}
		}
	}
	
	public void setFinJeu(boolean b){
		finJeu = b;
	}
	
	public void switchPanel(){
		((JeuGUI) jeuGui).switchPanel();
	}
	
	public boolean isMonTour(){
		return ((JeuGUI) jeuGui).isMonTour();
	}
	
	public void setMonTour(boolean b){
		((JeuGUI) jeuGui).setMonTour(b);
	}
	
	public void pionPoser(){
		((JeuGUI) jeuGui).pionPoser();
	}
	
	

	public int getLast_x() {
		return last_x;
	}

	public int getLast_y() {
		return last_y;
	}

	public int getLast_z() {
		return last_z;
	}

	public int getLast_id() {
		return last_id;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		pickCanvas.setShapeLocation(e);
	    PickResult result = pickCanvas.pickClosest();

	    if (result != null) {
	       Primitive p = (Primitive)result.getNode(PickResult.PRIMITIVE);
	       Shape3D s = (Shape3D)result.getNode(PickResult.SHAPE3D);
	       if (p != null) {
	    	   if(p.getClass().getSimpleName().equals("Cylinder")){
	    		   int x=-1, y=-1;
	    		   int i = 0;
	    		   for(LinkedList<Cylinder> l : cylinderList){
	    			   if(l.indexOf(((Cylinder)p)) != -1){
	    				   x=i;
	    				   y=l.indexOf(((Cylinder)p));
	    			   }
	    			   i++;
	    		   }

	    		   if(controler.getIdJoueur()==1){
		    		   for(LinkedList<Switch> cl : coneList1){
		    			   for(Switch scl : cl){
		    				   if(x==coneList1.indexOf(cl) && y==cl.indexOf(scl)){
		    					   if(scl.getWhichChild() == Switch.CHILD_NONE){
		    						   scl.setWhichChild(Switch.CHILD_ALL);
		    					   }
		    						   
		    				   } else {
		    					   if(scl.getWhichChild() == Switch.CHILD_ALL){
		    						   scl.setWhichChild(Switch.CHILD_NONE);
		    					   }
		    				   }
			    		   }
		    		   }
		    		   for(LinkedList<Switch> cl : coneList2){
		    			   for(Switch scl : cl){
		    				   scl.setWhichChild(Switch.CHILD_NONE);
			    		   }
		    		   }
	    		   } else {
	    			   for(LinkedList<Switch> cl : coneList1){
		    			   for(Switch scl : cl){
		    				   scl.setWhichChild(Switch.CHILD_NONE);
			    		   }
		    		   }
	    			   for(LinkedList<Switch> cl : coneList2){
		    			   for(Switch scl : cl){
		    				   if(x==coneList2.indexOf(cl) && y==cl.indexOf(scl)){
		    					   if(scl.getWhichChild() == Switch.CHILD_NONE){
		    						   scl.setWhichChild(Switch.CHILD_ALL);
		    					   }
		    						   
		    				   } else {
		    					   if(scl.getWhichChild() == Switch.CHILD_ALL){
		    						   scl.setWhichChild(Switch.CHILD_NONE);
		    					   }
		    				   }
			    		   }
		    		   }
	    		   }
	    		   /*if(x!=-1 && y!=-1){
	    			   ((Switch)coneList.get(x).get(y)).setWhichChild(Switch.CHILD_ALL);
	    		   }*/
	    	   }
	       }

	    }
	}
	
	public void switchJoueur(){
		controler.switchJoueur();
	}
}
