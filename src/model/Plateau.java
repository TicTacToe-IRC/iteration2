package model;

public class Plateau {

	private int lignes;
	private int colonnes;
	private int hauteur;
	private int[][][] plateauJeu;
	private Joueur joueurCourant;
	private Couleur couleur;
	private Joueur joueur1 = new Joueur("Joueur1", couleur, 1);
	private Joueur joueur2 = new Joueur("Joueur2", couleur, 2);
	private boolean annonce = false;


	public Plateau(int l, int c, int h) {
		super();
		lignes = l;
		colonnes = c;
		hauteur = h;
		plateauJeu = new int[l][c][h];

		init();
	}
	
	public void init() {
		for(int i=0; i< plateauJeu.length; i++){
			for(int j=0; j< plateauJeu.length; j++)
				for(int k=0; k< plateauJeu.length; k++)
					plateauJeu[i][j][k] = 0;
		}
		joueurCourant = joueur1;
	}
	
	public void reset() {
		for(int i=0; i< plateauJeu.length; i++){
			for(int j=0; j< plateauJeu.length; j++)
				for(int k=0; k< plateauJeu.length; k++)
					plateauJeu[i][j][k] = 0;
		}
	}

	public void afficherPlateau(){
		/* Lignes = horizontales
		 * Colonnes = verticales */
		for(int z=0; z< lignes; z++){
			for(int y=0; y< hauteur; y++){
				for(int x=0; x< colonnes; x++){
					System.out.print(" | " + plateauJeu[x][y][z]);
				}
				System.out.println(" | hauteur" + y);
			}	
			System.out.println("");
		}
		System.out.println("");
	}

	public void placerPion(int x, int y, int z){
		plateauJeu[x][y][z] = joueurCourant.getId();
		//switchJoueur();
	}

	public int placementHauteur(int x, int z){

		/*Si on tente de mettre un pion hors du plateau OU que le batonnet sur 
		lequel on tente de placer le pion est rempli */
		if ((x<0 || x>3 || z<0 || z>3)){
			return -1;
		}
		else{
			int y=0;
			while(y<4 && plateauJeu[x][y][z]!=0){
				y++;
			}
			if(y<4){
				return y;
			}
			else{
				return -1;
			}
		}
	}

	public void switchJoueur(){
		if(joueurCourant == joueur1){
			joueurCourant = joueur2;
		}
		else{
			joueurCourant = joueur1;
		}
	}
/*
	public int gagner(int x, int y, int z, int idJoueur){
			
			if((plateauJeu[0][y][z]== idJoueur && plateauJeu[1][y][z]== idJoueur
			&& plateauJeu[2][y][z]== idJoueur && plateauJeu[3][y][z]== idJoueur)
			|| (plateauJeu[x][0][z]== idJoueur && plateauJeu[x][1][z]== idJoueur
					&& plateauJeu[x][2][z]== idJoueur && plateauJeu[x][3][z]== idJoueur)){
				return idJoueur;
			}
			else 
				return 0;	
	}*/
	
	public int partieFinie(int x, int y, int z, int idJoueur) {
		boolean full = true;
		boolean gagne = false;

		int xT = 0, zT =0;
		
		while (full && xT<4) {
			zT = 0;
			while (full && zT<4) {
				if (plateauJeu[xT][3][zT] == 0) {
					//System.out.println(xT + " " + zT);
					full = false;
				}
				zT++;
			}
			xT++;
		}
		
		if (full) {
			return -1;
		}

		for (int xDif = -1; xDif<=1 && !gagne; xDif++) {
			for (int yDif = -1; yDif<=1 && !gagne; yDif++) {
				for (int zDif = -1; zDif<=1 && !gagne; zDif++) {
					int xN = x, yN = y, zN = z;
					int xDepl = xDif, yDepl = yDif, zDepl = zDif;
					int countVoisinsMemeId = 0;
					boolean continuer = (xDepl!=0 || yDepl!=0 || zDepl!=0);
					boolean invert = false;
					while (continuer) {
						if ((xDepl == -1 && xN == 0) || (xDepl == 1 && xN == 3) || (yDepl == -1 && yN == 0) || (yDepl == 1 && yN == 3) || (zDepl == -1 && zN == 0) || (zDepl == 1 && zN == 3)) {
							if (!invert) {
								invert = true;
								xDepl = -xDepl;
								yDepl = -yDepl;
								zDepl = -zDepl;
								xN = x;
								yN = y;
								zN = z;
							}
							else {
								continuer = false;
							}
						}
						xN = xN+xDepl;
						yN = yN+yDepl;
						zN = zN+zDepl;

						if (xN<0 || xN>3 || yN<0 || yN>3 || zN<0 || zN>3) {
							continuer = false;
						}
						else {
							if (plateauJeu[xN][yN][zN] == idJoueur) {
								countVoisinsMemeId++;
								if (countVoisinsMemeId == 3) {
									continuer = false;
									gagne = true;

								}
							}
							else {
								continuer = false;
							}
						}
					}
				}
			}
		}

		if (gagne) {
			return idJoueur;
		}
		else {
			return 0;
		}
	}
	
	public int getIdJoueurCourant() {
		return joueurCourant.getId();
	}
	
	public String getNomJoueur1() {
		return joueur1.getNom();
	}
	
	public String getNomJoueur2() {
		return joueur2.getNom();
	}
	
	public void setNomJoueur1(String s) {
		joueur1.setNom(s);
	}
	
	public void setNomJoueur2(String s) {
		joueur2.setNom(s);
	}
	
	public Couleur getCouleurJoueur1() {
		return joueur1.getCouleur();
	}
	
	public Couleur getCouleurJoueur2() {
		return joueur2.getCouleur();
	}
	
	public void setCouleurJoueur1(Couleur c) {
		joueur1.setCouleur(c);
	}
	
	public void setCouleurJoueur2(Couleur c) {
		joueur2.setCouleur(c);
	}
	
	public String getDeviseJoueur1() {
		return joueur1.getDevise();
	}
	
	public String getDeviseJoueur2() {
		return joueur2.getDevise();
	}
	
	public void setDeviseJoueur1(String s) {
		joueur1.setDevise(s);
	}
	
	public void setDeviseJoueur2(String s) {
		joueur2.setDevise(s);
	}

	public boolean isAnnonce() {
		return annonce;
	}
	
	public void setAnnonce(boolean b) {
		annonce = b;
	}
}
