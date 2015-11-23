package model;

public interface Pions {
		/**
		 * @return int indice de la colonne où est positionnée le pion
		 */
		public int getX();
		
		/**
		 * @return int indice de la ligne où est positionnée le pion
		 */
		public int getY();
		
		/**
		 * @return int indice de la hauteur où est positionnée le pion
		 */
		public int getZ();
		
		/**
		 * @return Couleur couleur du pion
		 */
		public Couleur getCouleur();
		
		/**
		 * 
		 * @return String le nom du pion
		 */
		public String getNom();
		
		/**
		 * @param xFinal
		 * @param yFinal
		 * @param zFinal
		 * @return boolean true si déplacement légal
		 */
		public boolean isPositionOk(int xFinal, int yFinal, int zFinal);
		
}


