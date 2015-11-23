package launcher;

import java.awt.Dimension;

import javax.swing.JFrame;

import controler.TicTacToeControler;
import model.Plateau;
import vue.MainFrame;

public class LauncherGUI {
	public static void main(String[] args) {
	//	TicTacToe ticTacToe;	
		TicTacToeControler ticTacToeControler;
		JFrame frame;	
		Dimension dim;
		Plateau plateau = new Plateau(4, 4, 4);
	
		dim = new Dimension(700, 700);
		
	//	ticTacToe = new TicTacToe();	
		ticTacToeControler = new TicTacToeControler(plateau);
		
		frame = new MainFrame("Tic Tac Toe 3D",dim,ticTacToeControler);
		//frame.setPreferredSize(dim);
		//frame.getContentPane().add(new MenuGUI("Tic Tac Toe 3D", ticTacToeControler,  dim));
	//	ticTacToe.addObserver((Observer) frame);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(600, 10);
		frame.setPreferredSize(dim);
		frame.pack();
		frame.setVisible(true);
	}
}