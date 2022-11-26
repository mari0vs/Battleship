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

	public GameInterface (SizeSetter sis, Assets ass)
	{
//------------------------------ Frame ------------------------------
		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

//------------------------------ Menu ------------------------------
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(BorderFactory.createMatteBorder(5, 10, 5, 10, ass.colors[2]));
		menuBar.setBackground(ass.colors[3]);
		menuBar.setForeground(ass.colors[1]);
		setJMenuBar(menuBar);

		menu = new JMenu("Options");
		menu.setOpaque(true);
		menu.setFont(ass.fonts[2]);
		menu.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, ass.colors[3]));
		menu.setBackground(ass.colors[0]);
		menu.setForeground(ass.colors[1]);
		menuBar.add(menu);

		newGame = new JMenuItem("New Game");
		newGame.setFont(ass.fonts[2]);
		Border newGameB = BorderFactory.createMatteBorder(2, 2, 0, 2, ass.colors[2]);
		Border newGameM = BorderFactory.createMatteBorder(5, 5, 5, 5, ass.colors[3]);
		newGame.setBorder(new CompoundBorder(newGameB, newGameM));
		newGame.setBackground(ass.colors[0]);
		newGame.setForeground(ass.colors[1]);
		menu.add(newGame);

		loadGame = new JMenu("Load Game");
		loadGame.setFont(ass.fonts[2]);
		Border loadGameB = BorderFactory.createMatteBorder(0, 2, 0, 2, ass.colors[2]);
		Border loadGameM = BorderFactory.createMatteBorder(5, 5, 5, 5, ass.colors[3]);
		loadGame.setBorder(new CompoundBorder(loadGameB, loadGameM));
		loadGame.setBackground(ass.colors[0]);
		loadGame.setForeground(ass.colors[1]);
		menu.add(loadGame);

		loadSave1 = new JMenuItem("Save 1");
		loadSave1.setFont(ass.fonts[2]);
		Border loadSave1B = BorderFactory.createMatteBorder(0, 2, 0, 2, ass.colors[2]);
		Border loadSave1M = BorderFactory.createMatteBorder(5, 5, 5, 5, ass.colors[3]);
		loadSave1.setBorder(new CompoundBorder(loadSave1B, loadSave1M));
		loadSave1.setBackground(ass.colors[0]);
		loadSave1.setForeground(ass.colors[1]);
		loadGame.add(loadSave1);

		loadSave2 = new JMenuItem("Save 2");
		loadSave2.setFont(ass.fonts[2]);
		Border loadSave2B = BorderFactory.createMatteBorder(0, 2, 0, 2, ass.colors[2]);
		Border loadSave2M = BorderFactory.createMatteBorder(5, 5, 5, 5, ass.colors[3]);
		loadSave2.setBorder(new CompoundBorder(loadSave2B, loadSave2M));
		loadSave2.setBackground(ass.colors[0]);
		loadSave2.setForeground(ass.colors[1]);
		loadGame.add(loadSave2);

		loadSave3 = new JMenuItem("Save 3");
		loadSave3.setFont(ass.fonts[2]);
		Border loadSave3B = BorderFactory.createMatteBorder(0, 2, 0, 2, ass.colors[2]);
		Border loadSave3M = BorderFactory.createMatteBorder(5, 5, 5, 5, ass.colors[3]);
		loadSave3.setBorder(new CompoundBorder(loadSave3B, loadSave3M));
		loadSave3.setBackground(ass.colors[0]);
		loadSave3.setForeground(ass.colors[1]);
		loadGame.add(loadSave3);

		saveGame = new JMenu("Save Game");
		saveGame.setFont(ass.fonts[2]);
		Border saveGameB = BorderFactory.createMatteBorder(0, 2, 0, 2, ass.colors[2]);
		Border saveGameM = BorderFactory.createMatteBorder(5, 5, 5, 5, ass.colors[3]);
		saveGame.setBorder(new CompoundBorder(saveGameB, saveGameM));
		saveGame.setBackground(ass.colors[0]);
		saveGame.setForeground(ass.colors[1]);
		menu.add(saveGame);

		saveSave1 = new JMenuItem("Save 1");
		saveSave1.setFont(ass.fonts[2]);
		Border saveSave1B = BorderFactory.createMatteBorder(0, 2, 0, 2, ass.colors[2]);
		Border saveSave1M = BorderFactory.createMatteBorder(5, 5, 5, 5, ass.colors[3]);
		saveSave1.setBorder(new CompoundBorder(saveSave1B, saveSave1M));
		saveSave1.setBackground(ass.colors[0]);
		saveSave1.setForeground(ass.colors[1]);
		saveGame.add(saveSave1);

		saveSave2 = new JMenuItem("Save 2");
		saveSave2.setFont(ass.fonts[2]);
		Border saveSave2B = BorderFactory.createMatteBorder(0, 2, 0, 2, ass.colors[2]);
		Border saveSave2M = BorderFactory.createMatteBorder(5, 5, 5, 5, ass.colors[3]);
		saveSave2.setBorder(new CompoundBorder(saveSave2B, saveSave2M));
		saveSave2.setBackground(ass.colors[0]);
		saveSave2.setForeground(ass.colors[1]);
		saveGame.add(saveSave2);

		saveSave3 = new JMenuItem("Save 3");
		saveSave3.setFont(ass.fonts[2]);
		Border saveSave3B = BorderFactory.createMatteBorder(0, 2, 0, 2, ass.colors[2]);
		Border saveSave3M = BorderFactory.createMatteBorder(5, 5, 5, 5, ass.colors[3]);
		saveSave3.setBorder(new CompoundBorder(saveSave3B, saveSave3M));
		saveSave3.setBackground(ass.colors[0]);
		saveSave3.setForeground(ass.colors[1]);
		saveGame.add(saveSave3);

		exit = new JMenuItem("Exit");
		exit.setFont(ass.fonts[2]);
		Border exitB = BorderFactory.createMatteBorder(0, 2, 2, 2, ass.colors[2]);
		Border exitM = BorderFactory.createMatteBorder(5, 5, 5, 5, ass.colors[3]);
		exit.setBorder(new CompoundBorder(exitB, exitM));
		exit.setBackground(ass.colors[0]);
		exit.setForeground(ass.colors[1]);
		menu.add(exit);

//------------------------------ Containers ------------------------------
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		card = new CardLayout();
		cardPanel = new JPanel(card);
		contentPane.add(cardPanel, BorderLayout.CENTER);

		nextTurnPanel = new JPanel();
		Border border = BorderFactory.createMatteBorder(sis.sizes[0], sis.sizes[1], sis.sizes[0], sis.sizes[1], ass.colors[0]);
		Border margin = BorderFactory.createMatteBorder(10, 10, 10, 10, ass.colors[2]);
		nextTurnPanel.setBorder(new CompoundBorder(border, margin));
		nextTurnPanel.setBackground(ass.colors[3]);
		cardPanel.add(nextTurnPanel, "3");

		BoxLayout boxLayout = new BoxLayout(nextTurnPanel, BoxLayout.Y_AXIS);
		nextTurnPanel.setLayout(boxLayout);

//-------------------- Content --------------------
		nextTurnPanel.add(Box.createVerticalGlue());

		nextTurn = new JButton();
		nextTurn.setFocusable(false);
		nextTurn.setFont(ass.fonts[0]);
		nextTurn.setBorder(new EmptyBorder(sis.sizes[2], sis.sizes[3], sis.sizes[2], sis.sizes[3]));
		nextTurn.setBackground(ass.colors[0]);
		nextTurn.setForeground(ass.colors[1]);
		nextTurn.setAlignmentX(CENTER_ALIGNMENT);
		nextTurnPanel.add(nextTurn, BorderLayout.CENTER);

		nextTurnPanel.add(Box.createVerticalGlue());
	}

	public void setTurnButtonPlayer(Boolean t, String pA, String pB)
	{
		if (t)
		{
			nextTurn.setText(pA + "'S TURN");
		}

		else
		{
			nextTurn.setText(pB + "'S TURN");
		}
	}
}
