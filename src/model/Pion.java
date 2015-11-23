package model;

public class Pion implements Pions{
	private String name;
	private Couleur couleur;
	private Coord coord;
	
	
	public Pion(String name, Couleur couleur, Coord coord) {
		this.name = name;
		this.couleur = couleur;
		this.coord = coord;
	}


	@Override
	public boolean isPositionOk(int xFinal, int yFinal, int zFinal){
			if (this.getX()<=3 && this.getY()<=3 && this.getZ() <=3){
				System.out.println("OK : Déplacement simple");
				return true;
		}
			else{
				System.out.println("KO : La position finale ne correspond pas à l'algo"
						+ "de déplacement légal de la pièce");
				return false;
			}
	}
	
	public int getX(){
		return this.coord.x;
	}

	public int getY(){
		return this.coord.y;
	}
	
	public int getZ(){
		return this.coord.z;
	}
	
	public Couleur getCouleur(){
		return this.couleur;
	}

	public String getName(){
		return this.name;
	}
	
	@Override
	public String toString() {
		return "Pion "+getName()+"[X: " + getX() + ", Y: " + getY() + "Z : " + getZ() + "]";
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {/*
		Joueur joueur1 = new Joueur("Dan", new Couleur("gris", 120, 120, 120));
		Pion monPion = new Pion("Pion1_J1", joueur1.getCouleur(), new Coord(1, 1, 1));
		System.out.println(monPion);
		
		Joueur joueur2 = new Joueur("Nico", new Couleur("noir", 0, 0, 0));
		Pion monPion2 = new Pion("Pion1_J2", joueur2.getCouleur(), new Coord(5, 1, 1));
		System.out.println(monPion2);*/
	}
        
}
