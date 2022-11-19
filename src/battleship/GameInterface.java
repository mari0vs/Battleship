package battleship;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")

public class GameInterface extends JFrame
{
	JMenu menu, loadGame, saveGame;
	JMenuItem newGame, loadSave1, loadSave2, loadSave3, saveSave1, saveSave2, saveSave3, exit;
	CardLayout card;
	JPanel cardPanel, nextTurnPanel;
	int currentCard;
	JButton nextTurn;
	Color[] colors;
	ShipPlacement shipPlacementA, shipPlacementB;
	Shooting shootingA, shootingB;
	Winner winner;
	Ship[] shipsA, shipsB;

	public GameInterface (int[] ss, Color[] c, int t)
	{
		colors = c;
		//------------------------------ Frame ------------------------------

		setTitle("Menu");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//------------------------------ Menu ------------------------------

		Font font = new Font("Comic Sans", Font.BOLD, 20);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(BorderFactory.createMatteBorder(5, 10, 5, 10, colors[2]));
		menuBar.setBackground(colors[3]);
		menuBar.setForeground(colors[1]);
		setJMenuBar(menuBar);

		menu = new JMenu("Options");
		menu.setOpaque(true);
		menu.setFont(font);
		menu.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, colors[3]));
		menu.setBackground(colors[0]);
		menu.setForeground(colors[1]);
		menuBar.add(menu);

		newGame = new JMenuItem("New Game");
		newGame.setFont(font);
		Border newGameB = BorderFactory.createMatteBorder(2, 2, 0, 2, colors[2]);
		Border newGameM = BorderFactory.createMatteBorder(5, 5, 5, 5, colors[3]);
		newGame.setBorder(new CompoundBorder(newGameB, newGameM));
		newGame.setBackground(colors[0]);
		newGame.setForeground(colors[1]);
		menu.add(newGame);

		loadGame = new JMenu("Load Game");
		loadGame.setFont(font);
		Border loadGameB = BorderFactory.createMatteBorder(0, 2, 0, 2, colors[2]);
		Border loadGameM = BorderFactory.createMatteBorder(5, 5, 5, 5, colors[3]);
		loadGame.setBorder(new CompoundBorder(loadGameB, loadGameM));
		loadGame.setBackground(colors[0]);
		loadGame.setForeground(colors[1]);
		menu.add(loadGame);

		loadSave1 = new JMenuItem("Save 1");
		loadSave1.setFont(font);
		Border loadSave1B = BorderFactory.createMatteBorder(0, 2, 0, 2, colors[2]);
		Border loadSave1M = BorderFactory.createMatteBorder(5, 5, 5, 5, colors[3]);
		loadSave1.setBorder(new CompoundBorder(loadSave1B, loadSave1M));
		loadSave1.setBackground(colors[0]);
		loadSave1.setForeground(colors[1]);
		loadGame.add(loadSave1);

		loadSave2 = new JMenuItem("Save 2");
		loadSave2.setFont(font);
		Border loadSave2B = BorderFactory.createMatteBorder(0, 2, 0, 2, colors[2]);
		Border loadSave2M = BorderFactory.createMatteBorder(5, 5, 5, 5, colors[3]);
		loadSave2.setBorder(new CompoundBorder(loadSave2B, loadSave2M));
		loadSave2.setBackground(colors[0]);
		loadSave2.setForeground(colors[1]);
		loadGame.add(loadSave2);

		loadSave3 = new JMenuItem("Save 3");
		loadSave3.setFont(font);
		Border loadSave3B = BorderFactory.createMatteBorder(0, 2, 0, 2, colors[2]);
		Border loadSave3M = BorderFactory.createMatteBorder(5, 5, 5, 5, colors[3]);
		loadSave3.setBorder(new CompoundBorder(loadSave3B, loadSave3M));
		loadSave3.setBackground(colors[0]);
		loadSave3.setForeground(colors[1]);
		loadGame.add(loadSave3);

		saveGame = new JMenu("Save Game");
		saveGame.setFont(font);
		Border saveGameB = BorderFactory.createMatteBorder(0, 2, 0, 2, colors[2]);
		Border saveGameM = BorderFactory.createMatteBorder(5, 5, 5, 5, colors[3]);
		saveGame.setBorder(new CompoundBorder(saveGameB, saveGameM));
		saveGame.setBackground(colors[0]);
		saveGame.setForeground(colors[1]);
		menu.add(saveGame);

		saveSave1 = new JMenuItem("Save 1");
		saveSave1.setFont(font);
		Border saveSave1B = BorderFactory.createMatteBorder(0, 2, 0, 2, colors[2]);
		Border saveSave1M = BorderFactory.createMatteBorder(5, 5, 5, 5, colors[3]);
		saveSave1.setBorder(new CompoundBorder(saveSave1B, saveSave1M));
		saveSave1.setBackground(colors[0]);
		saveSave1.setForeground(colors[1]);
		saveGame.add(saveSave1);

		saveSave2 = new JMenuItem("Save 2");
		saveSave2.setFont(font);
		Border saveSave2B = BorderFactory.createMatteBorder(0, 2, 0, 2, colors[2]);
		Border saveSave2M = BorderFactory.createMatteBorder(5, 5, 5, 5, colors[3]);
		saveSave2.setBorder(new CompoundBorder(saveSave2B, saveSave2M));
		saveSave2.setBackground(colors[0]);
		saveSave2.setForeground(colors[1]);
		saveGame.add(saveSave2);

		saveSave3 = new JMenuItem("Save 3");
		saveSave3.setFont(font);
		Border saveSave3B = BorderFactory.createMatteBorder(0, 2, 0, 2, colors[2]);
		Border saveSave3M = BorderFactory.createMatteBorder(5, 5, 5, 5, colors[3]);
		saveSave3.setBorder(new CompoundBorder(saveSave3B, saveSave3M));
		saveSave3.setBackground(colors[0]);
		saveSave3.setForeground(colors[1]);
		saveGame.add(saveSave3);

		exit = new JMenuItem("Exit");
		exit.setFont(font);
		Border exitB = BorderFactory.createMatteBorder(0, 2, 2, 2, colors[2]);
		Border exitM = BorderFactory.createMatteBorder(5, 5, 5, 5, colors[3]);
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
		Border border = BorderFactory.createMatteBorder(ss[0], ss[1], ss[0], ss[1], colors[0]);
		Border margin = BorderFactory.createMatteBorder(10, 10, 10, 10, colors[2]);
		nextTurnPanel.setBorder(new CompoundBorder(border, margin));
		nextTurnPanel.setBackground(colors[3]);
		cardPanel.add(nextTurnPanel, "3");

		BoxLayout boxLayout = new BoxLayout(nextTurnPanel, BoxLayout.Y_AXIS);
		nextTurnPanel.setLayout(boxLayout);

		nextTurnPanel.add(Box.createVerticalGlue());

		nextTurn = new JButton("PLAYER " + t + "'S TURN");

		if (t == 1)
		{
			nextTurn.setName("A");
		}

		else
		{
			nextTurn.setName("B");
		}

		nextTurn.setFocusable(false);
		nextTurn.setEnabled(true);
		nextTurn.setOpaque(true);
		nextTurn.setFont(new Font("Comic Sans", Font.BOLD, 100));
		nextTurn.setBorder(new EmptyBorder(ss[2], ss[3], ss[2], ss[3]));
		nextTurn.setBackground(colors[0]);
		nextTurn.setForeground(colors[1]);
		nextTurn.setAlignmentX(CENTER_ALIGNMENT);
		nextTurnPanel.add(nextTurn, BorderLayout.CENTER);

		nextTurnPanel.add(Box.createVerticalGlue());
	}
}
