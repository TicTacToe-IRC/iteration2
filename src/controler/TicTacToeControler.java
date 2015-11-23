package controler;

import model.Couleur;
import model.Plateau;

public class TicTacToeControler {
	private Plateau plateau;
	
	public TicTacToeControler(Plateau p){
		this.plateau = p;
	}
	
	public int placementOk(int x, int y){
		return plateau.placementHauteur(x,y);
	}
	
	public void placerPion(int x, int y, int z){
		plateau.placerPion(x, y, z);
	}
	
	public int getIdJoueur(){
		return plateau.getIdJoueurCourant();
	}
	
	public String getNomJoueur1() {
		return plateau.getNomJoueur1();
	}
	
	public String getNomJoueur2() {
		return plateau.getNomJoueur2();
	}
	
	public void setNomJoueur1(String s) {
		plateau.setNomJoueur1(s);
	}
	
	public void setNomJoueur2(String s) {
		plateau.setNomJoueur2(s);
	}
	
	public Couleur getCouleurJoueur1() {
		return plateau.getCouleurJoueur1();
	}
	
	public Couleur getCouleurJoueur2() {
		return plateau.getCouleurJoueur2();
	}
	
	public void setCouleurJoueur1(Couleur c) {
		plateau.setCouleurJoueur1(c);
	}
	
	public void setCouleurJoueur2(Couleur c) {
		plateau.setCouleurJoueur2(c);
	}
	
	public String getDeviseJoueur1() {
		return plateau.getDeviseJoueur1();
	}
	
	public String getDeviseJoueur2() {
		return plateau.getDeviseJoueur2();
	}
	
	public void setDeviseJoueur1(String s) {
		plateau.setDeviseJoueur1(s);
	}
	
	public void setDeviseJoueur2(String s) {
		plateau.setDeviseJoueur2(s);
	}

	public boolean isAnnonce() {
		return plateau.isAnnonce();
	}
	
	public void setAnnonce(boolean b) {
		plateau.setAnnonce(b);
	}
	
	public int partieFinie(int x, int y, int z, int idJoueur){
		return plateau.partieFinie(x, y, z, idJoueur);
	}
	
	public void initPlateau(){
		plateau.init();
	}
	
	public void switchJoueur(){
		plateau.switchJoueur();
	}
	
	public void resetPlateau(){
		plateau.reset();
	}
	
}
