/* Classe de gestion des statistiques pour un joueur 
 * A utiliser au travers des méthodes de StatRecord (pas directement) 
 * 
 * Auteur : Robin
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Statistics implements Serializable {
	//Attributs
	private HashMap<String, AdversaireStats> advStats = new HashMap<String, AdversaireStats>();
	
	//Méthodes publiques
	
	/* Read */
		//nombre de parties gagnées, perdues, nulles, jouées
	
	public int getNbPartiesGagnees() {
		ArrayList<String> nomsAdversaires = getNomsAdversaires();
		int nbPartiesGagneesTotal = 0;
		
		for (String nom : nomsAdversaires) {
			int nbPartiesGagnees = getNbPartiesGagnees(nom);
			if (nbPartiesGagnees != -1) {
				nbPartiesGagneesTotal += nbPartiesGagnees;
			}
		}
		
		return nbPartiesGagneesTotal;
	}
	
	public int getNbPartiesJouees() {
		ArrayList<String> nomsAdversaires = getNomsAdversaires();
		int nbPartiesJoueesTotal = 0;
		
		for (String nom : nomsAdversaires) {
			int nbPartiesJouees = getNbPartiesJouees(nom);
			if (nbPartiesJouees != -1) {
				nbPartiesJoueesTotal += nbPartiesJouees;
			}
		}
		
		return nbPartiesJoueesTotal;
	}
	
	public int getNbPartiesJouees(String nomAdversaire) {
		int nbPartiesJouees = -1;
		AdversaireStats statsFor = getStatsFor(nomAdversaire);
		
		if (statsFor != null) {
			nbPartiesJouees = statsFor.getNbPartiesJouees();
		}
		
		return nbPartiesJouees;
	}
	
	public int getNbPartiesPerdues() {
		ArrayList<String> nomsAdversaires = getNomsAdversaires();
		int nbPartiesPerduesTotal = 0;
		
		for (String nom : nomsAdversaires) {
			int nbPartiesPerdues = getNbPartiesPerdues(nom);
			if (nbPartiesPerdues != -1) {
				nbPartiesPerduesTotal += nbPartiesPerdues;
			}
		}
		
		return nbPartiesPerduesTotal;
	}
	
	public int getNbPartiesPerdues(String nomAdversaire) {
		int nbPartiesPerdues = -1;
		AdversaireStats statsFor = getStatsFor(nomAdversaire);
		
		if (statsFor != null) {
			nbPartiesPerdues = statsFor.getNbPartiesPerdues();
		}
		
		return nbPartiesPerdues;
	}
	
	public int getNbPartiesGagnees(String nomAdversaire) {
		int nbPartiesGagnees = -1;
		AdversaireStats statsFor = getStatsFor(nomAdversaire);
		
		if (statsFor != null) {
			nbPartiesGagnees = statsFor.getNbPartiesGagnees();
		}
		
		return nbPartiesGagnees;
	}
	
	public int getNbPartiesNulles() {
		ArrayList<String> nomsAdversaires = getNomsAdversaires();
		int nbPartiesNullesTotal = 0;
		
		for (String nom : nomsAdversaires) {
			int nbPartiesNulles = getNbPartiesNulles(nom);
			if (nbPartiesNulles != -1) {
				nbPartiesNullesTotal += nbPartiesNulles;
			}
		}
		
		return nbPartiesNullesTotal;
	}
	
	public int getNbPartiesNulles(String nomAdversaire) {
		int nbPartiesNulles = -1;
		AdversaireStats statsFor = getStatsFor(nomAdversaire);
		
		if (statsFor != null) {
			nbPartiesNulles = statsFor.getNbPartiesNulles();
		}
		
		return nbPartiesNulles;
	}
	
	/* Read */
		//Test d'existence de l'adversaire pour le joueur
	
	public boolean adversaireExiste(String nomAdversaire) {
		return (advStats.containsKey(nomAdversaire));
	}
	
	/* Read */
		//Noms des adversaires enregistrés pour le joueur
	
	public ArrayList<String> getNomsAdversaires() {
		ArrayList<String> nomsAdversaires;
		nomsAdversaires = new ArrayList<String>();
		
		for (Map.Entry<String, AdversaireStats> entry : advStats.entrySet()) {
			nomsAdversaires.add(entry.getKey());
		}
		
		return nomsAdversaires;
	}

	/* Create/Update */
		//Ajoute parties gagnées, perdues, nulles aux statistiques du joueur
	
	public void addPartieGagnee(String nomAdversaire) {
		AdversaireStats st = createIfMissingAndReturnAdversaire(nomAdversaire);
		st.addPartieGagnee();
	}

	public void addPartiePerdue(String nomAdversaire) {
		AdversaireStats st = createIfMissingAndReturnAdversaire(nomAdversaire);
		st.addPartiePerdue();
	}

	public void addPartieNulle(String nomAdversaire) {
		AdversaireStats st = createIfMissingAndReturnAdversaire(nomAdversaire);
		st.addPartieNulle();
	}
	
	/* Update */
		//Reset statistiques
	
	public void resetPartiesGagnees() {
		ArrayList<String> nomsAdversaires = getNomsAdversaires();
		for (String nom : nomsAdversaires) {
			resetPartiesGagnees(nom);
		}
	}
	
	public void resetPartiesPerdues() {
		ArrayList<String> nomsAdversaires = getNomsAdversaires();
		for (String nom : nomsAdversaires) {
			resetPartiesPerdues(nom);
		}
	}
	
	public void resetPartiesNulles() {
		ArrayList<String> nomsAdversaires = getNomsAdversaires();
		for (String nom : nomsAdversaires) {
			resetPartiesNulles(nom);
		}
	}
	
	public void resetPartiesGagnees(String nomAdversaire) {
		AdversaireStats st = null;
		if (adversaireExiste(nomAdversaire)) {
			st = advStats.get(nomAdversaire);
			st.resetPartiesGagnees();
		}
	}
	
	public void resetPartiesPerdues(String nomAdversaire) {
		AdversaireStats st = null;
		if (adversaireExiste(nomAdversaire)) {
			st = advStats.get(nomAdversaire);
			st.resetPartiesPerdues();
		}
	}
	
	public void resetPartiesNulles(String nomAdversaire) {
		AdversaireStats st = null;
		if (adversaireExiste(nomAdversaire)) {
			st = advStats.get(nomAdversaire);
			st.resetPartiesNulles();
		}
	}
	
	public void resetAllStatsParties() {
		resetPartiesGagnees();
		resetPartiesPerdues();
		resetPartiesNulles();
	}
	
	public void resetAllStatsParties(String nomAdversaire) {
		resetPartiesGagnees(nomAdversaire);
		resetPartiesPerdues(nomAdversaire);
		resetPartiesNulles(nomAdversaire);
	}
	
	public void resetAll() {
		resetAllStatsParties();
	}
	
	public void resetAll(String nomAdversaire) {
		resetAllStatsParties(nomAdversaire);
	}
	
	/* Delete */
		//Suppression joueur
	
	public void removeAdversaire(String nomAdversaire) {
		if (adversaireExiste(nomAdversaire)) {
			advStats.remove(nomAdversaire);
		}
	}
	
	//Méthodes privées
	
	/* Read */
		//Renvoie les stats pour un adversaire
	
	private AdversaireStats getStatsFor(String nomAdversaire) {
		AdversaireStats statsFor = null;
		
		if (adversaireExiste(nomAdversaire)) {
			statsFor = advStats.get(nomAdversaire);
		}
		
		return statsFor;
	}
	
	/* Create/Update */
		//Renvoie un adversaire existant ou le crée s'il n'existe pas
	
	private AdversaireStats createIfMissingAndReturnAdversaire(String nomAdversaire) {
		AdversaireStats st;
		if (adversaireExiste(nomAdversaire)) {
			st = advStats.get(nomAdversaire);
		}
		else {
			st = new AdversaireStats();
			advStats.put(nomAdversaire, st);
		}
		
		return st;
	}
}
