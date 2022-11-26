package battleship;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")

public class MenuInterface extends JFrame
{
	CardLayout card;
	JPanel cardPanel, menuPanel, loadGamePanel;
	JLabel menuTitle;
	JButton newGame, loadGame, exit, save1, save2, save3, back;

	public MenuInterface (Assets ass)
	{
// Frame
		setSize(400, 600);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

// Content Pane
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(ass.colors[0]);

// Label
		menuTitle = new JLabel("Battleship", SwingConstants.CENTER);
		menuTitle.setFont(ass.fonts[0]);
		menuTitle.setForeground(ass.colors[1]);
		contentPane.add(menuTitle, BorderLayout.NORTH);

// Panel
		card = new CardLayout();
		cardPanel = new JPanel(card);
		contentPane.add(cardPanel, BorderLayout.CENTER);

		menuPanel = new JPanel();
		Border menuPanelB = BorderFactory.createMatteBorder(20, 20, 20, 20, ass.colors[0]);
		Border menuPanelM = BorderFactory.createMatteBorder(10, 10, 10, 10, ass.colors[2]);
		menuPanel.setBorder(new CompoundBorder(menuPanelB, menuPanelM));
		menuPanel.setBackground(ass.colors[3]);
		cardPanel.add(menuPanel, "1");

		BoxLayout mpboxlayout = new BoxLayout(menuPanel, BoxLayout.Y_AXIS);
		menuPanel.setLayout(mpboxlayout);

		loadGamePanel = new JPanel();
		Border loadGamePanelB = BorderFactory.createMatteBorder(20, 20, 20, 20, ass.colors[0]);
		Border loadGamePanelM = BorderFactory.createMatteBorder(10, 10, 10, 10, ass.colors[2]);
		loadGamePanel.setBorder(new CompoundBorder(loadGamePanelB, loadGamePanelM));
		loadGamePanel.setBackground(ass.colors[3]);
		cardPanel.add(loadGamePanel, "2");

		BoxLayout lgpboxlayout = new BoxLayout(loadGamePanel, BoxLayout.Y_AXIS);
		loadGamePanel.setLayout(lgpboxlayout);

// Buttons
		menuPanel.add(Box.createRigidArea(new Dimension(15, 10)));

		newGame = new JButton("New Game");
		newGame.setFocusable(false);
		newGame.setOpaque(true);
		newGame.setFont(ass.fonts[1]);
		newGame.setBackground(ass.colors[0]);
		newGame.setForeground(ass.colors[1]);
		menuPanel.add(newGame);

		menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		loadGame = new JButton("Load Game");
		loadGame.setFocusable(false);
		loadGame.setOpaque(true);
		loadGame.setFont(ass.fonts[1]);
		loadGame.setBackground(ass.colors[0]);
		loadGame.setForeground(ass.colors[1]);
		menuPanel.add(loadGame);

		menuPanel.add(Box.createVerticalGlue());

		exit = new JButton("Exit");
		exit.setFocusable(false);
		exit.setOpaque(true);
		exit.setFont(ass.fonts[1]);
		exit.setBackground(ass.colors[0]);
		exit.setForeground(ass.colors[1]);
		menuPanel.add(exit);

		menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		loadGamePanel.add(Box.createRigidArea(new Dimension(15, 10)));

		save1 = new JButton("Save 1");
		save1.setFocusable(false);
		save1.setOpaque(true);
		save1.setFont(ass.fonts[1]);
		save1.setBackground(ass.colors[0]);
		save1.setForeground(ass.colors[1]);
		loadGamePanel.add(save1);

		loadGamePanel.add(Box.createRigidArea(new Dimension(0, 10)));

		save2 = new JButton("Save 2");
		save2.setFocusable(false);
		save2.setOpaque(true);
		save2.setFont(ass.fonts[1]);
		save2.setBackground(ass.colors[0]);
		save2.setForeground(ass.colors[1]);
		loadGamePanel.add(save2);

		loadGamePanel.add(Box.createRigidArea(new Dimension(0, 10)));

		save3 = new JButton("Save 3");
		save3.setFocusable(false);
		save3.setOpaque(true);
		save3.setFont(ass.fonts[1]);
		save3.setBackground(ass.colors[0]);
		save3.setForeground(ass.colors[1]);
		loadGamePanel.add(save3);

		loadGamePanel.add(Box.createVerticalGlue());

		back = new JButton("Back");
		back.setFocusable(false);
		back.setOpaque(true);
		back.setFont(ass.fonts[1]);
		back.setBackground(ass.colors[0]);
		back.setForeground(ass.colors[1]);
		loadGamePanel.add(back);

		loadGamePanel.add(Box.createRigidArea(new Dimension(0, 10)));
	}
}
