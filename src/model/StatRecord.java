/* Classe de chargement, de gestion et d'enregistrement des statistiques 
 * A utiliser en singleton avec getInstance()
 * 
 * Auteur : Robin
 */

package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StatRecord {
	//Attributs
	private HashMap<String,Statistics> stats = new HashMap<String, Statistics>();
	private String filePath = "stats.data";
	
	//Constructeur privé
	private StatRecord() throws ClassNotFoundException, IOException {
		load();
	}
	
	//Singleton
	private static StatRecord INSTANCE = null;
	
	//Accesseur du singleton
	public static StatRecord getInstance() throws ClassNotFoundException, IOException {
		if (INSTANCE == null) {
			INSTANCE = new StatRecord();
		}
		
		return INSTANCE;
	}
	
	//Méthodes publiques
	
	/* Opérations chargement/stockage/suppression (fichier sur disque) */
	//load
	public void load() throws IOException, ClassNotFoundException {
		if ((new File(filePath)).exists()) {
			FileInputStream f_in = new FileInputStream(filePath);
			ObjectInputStream obj_in = new ObjectInputStream(f_in);
			stats = (HashMap<String,Statistics>) obj_in.readObject();
			f_in.close();
		}
		else {
			stats = new HashMap<String, Statistics>();
		}
	}
	
	//store
	public void store() throws IOException {
		FileOutputStream f_out = new FileOutputStream(filePath);
		ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
		obj_out.writeObject(stats);
		f_out.close();
	}
	
	//delete
	public void deleteFromDisk() {
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
	}
	
	/* Read */
		//nombre de parties gagnées, perdues, nulles, jouées
	
	public int getNbPartiesJouees(String nomJoueur) {
		int nbPartiesJouees = -1;
		
		Statistics statsFor = getStatsFor(nomJoueur);
		if (statsFor != null) {
			nbPartiesJouees = statsFor.getNbPartiesJouees();
		}
		
		return nbPartiesJouees;
	}
	
	public int getNbPartiesJouees(String nomJoueur, String nomAdversaire) {
		int nbPartiesJouees = -1;
		
		Statistics statsFor = getStatsFor(nomJoueur);
		if (statsFor != null) {
			nbPartiesJouees = statsFor.getNbPartiesJouees(nomAdversaire);
		}
		
		return nbPartiesJouees;
	}
	
	public int getNbPartiesGagnees(String nomJoueur) {
		int nbPartiesGagnees = -1;
		
		Statistics statsFor = getStatsFor(nomJoueur);
		if (statsFor != null) {
			nbPartiesGagnees = statsFor.getNbPartiesGagnees();
		}
		
		return nbPartiesGagnees;
	}
	
	public int getNbPartiesPerdues(String nomJoueur) {
		int nbPartiesPerdues = -1;
		
		Statistics statsFor = getStatsFor(nomJoueur);
		if (statsFor != null) {
			nbPartiesPerdues = statsFor.getNbPartiesPerdues();
		}
		
		return nbPartiesPerdues;
	}
	
	public int getNbPartiesNulles(String nomJoueur) {
		int nbPartiesNulles = -1;
		
		Statistics statsFor = getStatsFor(nomJoueur);
		if (statsFor != null) {
			nbPartiesNulles = statsFor.getNbPartiesNulles();
		}
		
		return nbPartiesNulles;
	}
	
	public int getNbPartiesNulles(String nomJoueur, String nomAdversaire) {
		int nbPartiesNulles = -1;
		
		Statistics statsFor = getStatsFor(nomJoueur);
		if (statsFor != null) {
			nbPartiesNulles = statsFor.getNbPartiesNulles(nomAdversaire);
		}
		
		return nbPartiesNulles;
	}
	
	public int getNbPartiesGagnees(String nomJoueur, String nomAdversaire) {
		int nbPartiesGagnees = -1;
		
		Statistics statsFor = getStatsFor(nomJoueur);
		if (statsFor != null) {
			nbPartiesGagnees = statsFor.getNbPartiesGagnees(nomAdversaire);
		}
		
		return nbPartiesGagnees;
	}
	
	public int getNbPartiesPerdues(String nomJoueur, String nomAdversaire) {
		int nbPartiesPerdues = -1;
		
		Statistics statsFor = getStatsFor(nomJoueur);
		if (statsFor != null) {
			nbPartiesPerdues = statsFor.getNbPartiesPerdues(nomAdversaire);
		}
		
		return nbPartiesPerdues;
	}
	
	/* Read */
		//noms des joueurs/adversaires enregistrés
	
	public ArrayList<String> getNomsJoueurs() {
		ArrayList<String> nomsJoueurs = new ArrayList<String>();
		for (Map.Entry<String, Statistics> entry : stats.entrySet()) {
			nomsJoueurs.add(entry.getKey());
		}
		
		return nomsJoueurs;
	}
	
	public ArrayList<String> getNomsAdversaires(String nomJoueur) {
		ArrayList<String> nomsAdversaires = null;
		if (joueurExiste(nomJoueur)) {
			nomsAdversaires = stats.get(nomJoueur).getNomsAdversaires();
		}
		
		return nomsAdversaires;
	}
	
	
	/* Read */
		//test d'existence des joueurs/adversaire
	
	public boolean joueurExiste(String nomJoueur) {
		return stats.containsKey(nomJoueur);
	}
	
	public boolean adversaireExiste(String nomJoueur, String nomAdversaire) {
		boolean exist = false;
		if (joueurExiste(nomJoueur)) {
			exist = stats.get(nomJoueur).adversaireExiste(nomAdversaire);
		}
		return exist;
	}
	
	/* Create/Update */
		//Enregistrement résultats partie
	
	public void putResultatPartie(String nomJoueur1, String nomJoueur2, int result) {
		switch(result) {
			case -1:
				addPartieNulle(nomJoueur1,nomJoueur2);
				addPartieNulle(nomJoueur2,nomJoueur1);
			break;
			case 1:
				addPartieGagnee(nomJoueur1,nomJoueur2);
				addPartiePerdue(nomJoueur2,nomJoueur1);
			break;
			case 2:
				addPartiePerdue(nomJoueur1,nomJoueur2);
				addPartieGagnee(nomJoueur2,nomJoueur1);
			break;
		}
	}
	
	/* Update */
		//Reset statistiques
	
	public void resetPartiesGagnees(String nomJoueur) {
		if (joueurExiste(nomJoueur)) {
			stats.get(nomJoueur).resetPartiesGagnees();
		}
	}
	
	public void resetPartiesPerdues(String nomJoueur) {
		if (joueurExiste(nomJoueur)) {
			stats.get(nomJoueur).resetPartiesPerdues();
		}
	}
	
	public void resetPartiesNulles(String nomJoueur) {
		if (joueurExiste(nomJoueur)) {
			stats.get(nomJoueur).resetPartiesNulles();
		}
	}
	
	public void resetPartiesGagnees(String nomJoueur, String nomAdversaire) {
		if (joueurExiste(nomJoueur)) {
			stats.get(nomJoueur).resetPartiesGagnees(nomAdversaire);
		}
	}
	
	public void resetPartiesPerdues(String nomJoueur, String nomAdversaire) {
		if (joueurExiste(nomJoueur)) {
			stats.get(nomJoueur).resetPartiesPerdues(nomAdversaire);
		}
	}
	
	public void resetPartiesNulles(String nomJoueur, String nomAdversaire) {
		if (joueurExiste(nomJoueur)) {
			stats.get(nomJoueur).resetPartiesNulles(nomAdversaire);
		}
	}
	
	public void resetAllStatsParties(String nomJoueur) {
		resetPartiesGagnees(nomJoueur);
		resetPartiesPerdues(nomJoueur);
		resetPartiesNulles(nomJoueur);
	}
	
	public void resetAllStatsParties(String nomJoueur, String nomAdversaire) {
		resetPartiesGagnees(nomJoueur, nomAdversaire);
		resetPartiesPerdues(nomJoueur, nomAdversaire);
		resetPartiesNulles(nomJoueur, nomAdversaire);
	}
	
	public void resetAll(String nomJoueur) {
		resetAllStatsParties(nomJoueur);
	}
	
	public void resetAll(String nomJoueur, String nomAdversaire) {
		resetAllStatsParties(nomJoueur, nomAdversaire);
	}
	
	/* Delete */
		//Suppression joueur/adversaire
	
	public void removeJoueur(String nomJoueur) {
		if (joueurExiste(nomJoueur)) {
			stats.remove(nomJoueur);
		}
	}
	
	public void removeAdversaire(String nomJoueur, String nomAdversaire) {
		if (joueurExiste(nomJoueur)) {
			stats.get(nomJoueur).removeAdversaire(nomAdversaire);
		}
	}
	
	//Méthodes privées
	
	/* Read */
		//Renvoie les stats pour un joueur
	
	private Statistics getStatsFor(String nomJoueur) {
		Statistics statsFor = null;
		
		if (joueurExiste(nomJoueur)) {
			statsFor = stats.get(nomJoueur);
		}
		
		return statsFor;
	}
	
	/* Create/Update */
		//Renvoie un joueur existant ou le crée s'il n'existe pas
	
	private Statistics createIfMissingAndReturnJoueur(String nomJoueur) {
		Statistics st = null;
		
		if (joueurExiste(nomJoueur)) {
			st = stats.get(nomJoueur);
		}
		else {
			st = new Statistics();
			stats.put(nomJoueur, st);
		}
		
		return st;
	}
	
	/* Create/Update */
		//Ajoute parties gagnées, perdues, nulles aux statistiques d'un joueur
	
	private void addPartieGagnee(String nomJoueur, String nomAdversaire) {
		Statistics st = createIfMissingAndReturnJoueur(nomJoueur);
		st.addPartieGagnee(nomAdversaire);
	}
	
	private void addPartiePerdue(String nomJoueur, String nomAdversaire) {
		Statistics st = createIfMissingAndReturnJoueur(nomJoueur);
		st.addPartiePerdue(nomAdversaire);
	}
	
	private void addPartieNulle(String nomJoueur, String nomAdversaire) {
		Statistics st = createIfMissingAndReturnJoueur(nomJoueur);
		st.addPartieNulle(nomAdversaire);
	}
}
