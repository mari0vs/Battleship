package battleship;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")

public class GameInterface extends JFrame
{
	JMenu menu;
	JMenuItem newGame, loadGame, saveGame, exit;
	CardLayout card;
	JPanel cardPanel, nextTurnPanel;
	int currentCard;
	JButton nextTurn;
	Color[] colors;
	ShipPlacement shipPlacementA, shipPlacementB;
	Shooting shootingA, shootingB;
	Winner winner;
	Ship[] shipsA, shipsB;

	public GameInterface (int[] ss, Color[] c)
	{
		colors = c;
		//------------------------------ Frame ------------------------------

		setTitle("Menu");
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//------------------------------ Menu ------------------------------

		Font font = new Font("Comic Sans", Font.BOLD, 15);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(BorderFactory.createMatteBorder(5, 10, 5, 10, colors[2]));
		menuBar.setBackground(colors[5]);
		menuBar.setForeground(colors[1]);
		setJMenuBar(menuBar);

		menu = new JMenu("Options");
		menu.setOpaque(true);
		menu.setFont(font);
		menu.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, colors[5]));
		menu.setBackground(colors[0]);
		menu.setForeground(colors[1]);
		menuBar.add(menu);

		newGame = new JMenuItem("New Game");
		newGame.setFont(font);
		Border newGameB = BorderFactory.createMatteBorder(2, 2, 0, 2, colors[2]);
		Border newGameM = BorderFactory.createMatteBorder(5, 5, 5, 5, colors[5]);
		newGame.setBorder(new CompoundBorder(newGameB, newGameM));
		newGame.setBackground(colors[0]);
		newGame.setForeground(colors[1]);
		menu.add(newGame);

		loadGame = new JMenuItem("Load Game");
		loadGame.setFont(font);
		Border loadGameB = BorderFactory.createMatteBorder(0, 2, 0, 2, colors[2]);
		Border loadGameM = BorderFactory.createMatteBorder(5, 5, 5, 5, colors[5]);
		loadGame.setBorder(new CompoundBorder(loadGameB, loadGameM));
		loadGame.setBackground(colors[0]);
		loadGame.setForeground(colors[1]);
		menu.add(loadGame);

		saveGame = new JMenuItem("Save Game");
		saveGame.setFont(font);
		Border saveGameB = BorderFactory.createMatteBorder(0, 2, 0, 2, colors[2]);
		Border saveGameM = BorderFactory.createMatteBorder(5, 5, 5, 5, colors[5]);
		saveGame.setBorder(new CompoundBorder(saveGameB, saveGameM));
		saveGame.setBackground(colors[0]);
		saveGame.setForeground(colors[1]);
		menu.add(saveGame);

		exit = new JMenuItem("Exit");
		exit.setFont(font);
		Border exitB = BorderFactory.createMatteBorder(0, 2, 2, 2, colors[2]);
		Border exitM = BorderFactory.createMatteBorder(5, 5, 5, 5, colors[5]);
		exit.setBorder(new CompoundBorder(exitB, exitM));
		exit.setBackground(colors[0]);
		exit.setForeground(colors[1]);
		menu.add(exit);

		//------------------------------ Containers ------------------------------

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		card = new CardLayout();
		cardPanel = new JPanel(card);
		contentPane.add(cardPanel, BorderLayout.CENTER);


		//-------------------- Content --------------------
		nextTurnPanel = new JPanel();
		Border border = BorderFactory.createMatteBorder(ss[2], ss[3], ss[2], ss[3], colors[0]);
		Border margin = BorderFactory.createMatteBorder(10, 10, 10, 10, colors[2]);
		nextTurnPanel.setBorder(new CompoundBorder(border, margin));
		nextTurnPanel.setBackground(colors[5]);
		cardPanel.add(nextTurnPanel, "3");

		BoxLayout boxLayout = new BoxLayout(nextTurnPanel, BoxLayout.Y_AXIS);
		nextTurnPanel.setLayout(boxLayout);

		nextTurnPanel.add(Box.createVerticalGlue());

		nextTurn = new JButton("PLAYER 1'S TURN");
		nextTurn.setName("A");
		nextTurn.setFocusable(true);
		nextTurn.setEnabled(true);
		nextTurn.setOpaque(true);
		nextTurn.setFont(new Font("Comic Sans", Font.BOLD, 100));
		nextTurn.setBorder(new EmptyBorder(ss[4], ss[5], ss[4], ss[5]));
		nextTurn.setBackground(colors[0]);
		nextTurn.setForeground(colors[1]);
		nextTurn.setAlignmentX(CENTER_ALIGNMENT);
		nextTurnPanel.add(nextTurn, BorderLayout.CENTER);

		nextTurnPanel.add(Box.createVerticalGlue());
	}
}
