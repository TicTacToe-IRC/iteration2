/* Classe de gestion des statistiques pour un adversaire
 * A utiliser au travers des méthodes de Statistics (pas directement)
 * 
 * Auteur : Robin
 */

package model;

import java.io.Serializable;

public class AdversaireStats implements Serializable {
	/**
	 * 
	 */
	//Attributs
	private int nbPartiesGagnees;
	private int nbPartiesPerdues;
	private int nbPartiesNulles;
	
	//Méthodes publiques
	
	/* Read */
		//nombre de parties gagnées, perdues, nulles, jouées
	
	public int getNbPartiesGagnees() {
		return nbPartiesGagnees;
	}
	
	public int getNbPartiesJouees() {
		return getNbPartiesGagnees() + getNbPartiesPerdues() + getNbPartiesNulles();
	}
	
	public int getNbPartiesPerdues() {
		return nbPartiesPerdues;
	}
	
	public int getNbPartiesNulles() {
		return nbPartiesNulles;
	}

	/* Update */
		//Ajoute parties gagnées, perdues, nulles aux stats
	
	public void addPartieGagnee() {
		this.nbPartiesGagnees ++;
	}

	public void addPartiePerdue() {
		this.nbPartiesPerdues ++;
	}

	public void addPartieNulle() {
		this.nbPartiesNulles ++;
	}
	
	/* Update */
		//Reset statistiques
	
	public void resetPartiesGagnees() {
		nbPartiesGagnees = 0;
	}
	
	public void resetPartiesPerdues() {
		nbPartiesPerdues = 0;
	}
	
	public void resetPartiesNulles() {
		nbPartiesNulles = 0;
	}
	
	public void resetAllStatsParties() {
		resetPartiesGagnees();
		resetPartiesPerdues();
		resetPartiesNulles();
	}
	
	public void resetAll() {
		resetAllStatsParties();
	}
}
