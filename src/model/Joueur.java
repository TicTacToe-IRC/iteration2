package model;

public class Joueur {
	private String nom;
	private Couleur couleur;
	private String devise;
	private int id;
	
	public Joueur(String nom, Couleur couleur, int id) {
		super();
		this.nom = nom;
		this.couleur = couleur;
		this.id = id;
	}

	public String getNom() {
		return nom;
	}
	
	public int getId() {
		return id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Couleur getCouleur() {
		return couleur;
	}

	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}

	public String getDevise() {
		return devise;
	}

	public void setDevise(String s) {
		this.devise = s;
	}

	@Override
	public String toString() {
		return "Je suis le Joueur "+ id + ", je m'appelle " + nom + ", je joue avec les pions " + couleur.getNom() ;
	}
}
