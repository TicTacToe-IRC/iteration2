package model;

public class Couleur implements Comparable {
	private int r;
	private int g;
	private int b;
	private String nom;
	
	public Couleur(String nom, int r, int g, int b){
		this.nom = nom;
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public int getR() {
		return r;
	}

	public int getG() {
		return g;
	}

	public int getB() {
		return b;
	}

	public String getNom() {
		return nom;
	}

	@Override
	public int compareTo(Object o) {
		return this.getNom().compareTo(((Couleur)o).getNom());
	}
	
	
}