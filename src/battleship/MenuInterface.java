package battleship;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")

public class MenuInterface extends JFrame
{
	CardLayout card;
	JPanel cardPanel, menuPanel, loadGamePanel, westPanel, southPanel, eastPanel;
	JLabel menuTitle;
	JButton newGame, loadGame, exit, save1, save2, save3, back;
	Color[] colors;

	public MenuInterface (Color[] c)
	{
		Font titleFont = new Font("Comic Sans", Font.BOLD, 75);
		Font buttonFont = new Font("Comic Sans", Font.BOLD, 40);

		colors = c;

// Frame
		setTitle("Menu");
		setSize(400, 600);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

// Content Pane
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout(20, 20));
		contentPane.setBackground(colors[0]);
// Label
		menuTitle = new JLabel("Battleship", SwingConstants.CENTER);
		menuTitle.setFont(titleFont);
		menuTitle.setForeground(colors[1]);
		contentPane.add(menuTitle, BorderLayout.NORTH);

// Panel
		westPanel = new JPanel();
		westPanel.setOpaque(false);
		contentPane.add(westPanel, BorderLayout.WEST);

		southPanel = new JPanel();
		southPanel.setOpaque(false);
		contentPane.add(southPanel, BorderLayout.SOUTH);

		eastPanel = new JPanel();
		eastPanel.setOpaque(false);
		contentPane.add(eastPanel, BorderLayout.EAST);

		card = new CardLayout();
		cardPanel = new JPanel(card);
		contentPane.add(cardPanel, BorderLayout.CENTER);

		menuPanel = new JPanel();
		menuPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, colors[2]));
		menuPanel.setBackground(colors[3]);
		cardPanel.add(menuPanel, "1");

		BoxLayout mpboxlayout = new BoxLayout(menuPanel, BoxLayout.Y_AXIS);
		menuPanel.setLayout(mpboxlayout);

		loadGamePanel = new JPanel();
		loadGamePanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, colors[2]));
		loadGamePanel.setBackground(colors[3]);
		cardPanel.add(loadGamePanel, "2");

		BoxLayout lgpboxlayout = new BoxLayout(loadGamePanel, BoxLayout.Y_AXIS);
		loadGamePanel.setLayout(lgpboxlayout);
// Buttons
		menuPanel.add(Box.createRigidArea(new Dimension(15, 10)));

		newGame = new JButton("New Game");
		newGame.setFocusable(false);
		newGame.setOpaque(true);
		newGame.setFont(buttonFont);
		newGame.setBackground(colors[0]);
		newGame.setForeground(colors[1]);
		menuPanel.add(newGame);

		menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		loadGame = new JButton("Load Game");
		loadGame.setFocusable(false);
		loadGame.setOpaque(true);
		loadGame.setFont(buttonFont);
		loadGame.setBackground(colors[0]);
		loadGame.setForeground(colors[1]);
		menuPanel.add(loadGame);

		menuPanel.add(Box.createVerticalGlue());

		exit = new JButton("Exit");
		exit.setFocusable(false);
		exit.setOpaque(true);
		exit.setFont(buttonFont);
		exit.setBackground(colors[0]);
		exit.setForeground(colors[1]);
		menuPanel.add(exit);

		menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		loadGamePanel.add(Box.createRigidArea(new Dimension(15, 10)));

		save1 = new JButton("Save 1");
		save1.setFocusable(false);
		save1.setOpaque(true);
		save1.setFont(buttonFont);
		save1.setBackground(colors[0]);
		save1.setForeground(colors[1]);
		loadGamePanel.add(save1);

		loadGamePanel.add(Box.createRigidArea(new Dimension(0, 10)));

		save2 = new JButton("Save 2");
		save2.setFocusable(false);
		save2.setOpaque(true);
		save2.setFont(buttonFont);
		save2.setBackground(colors[0]);
		save2.setForeground(colors[1]);
		loadGamePanel.add(save2);

		loadGamePanel.add(Box.createRigidArea(new Dimension(0, 10)));

		save3 = new JButton("Save 3");
		save3.setFocusable(false);
		save3.setOpaque(true);
		save3.setFont(buttonFont);
		save3.setBackground(colors[0]);
		save3.setForeground(colors[1]);
		loadGamePanel.add(save3);

		loadGamePanel.add(Box.createVerticalGlue());

		back = new JButton("Back");
		back.setFocusable(false);
		back.setOpaque(true);
		back.setFont(buttonFont);
		back.setBackground(colors[0]);
		back.setForeground(colors[1]);
		loadGamePanel.add(back);

		loadGamePanel.add(Box.createRigidArea(new Dimension(0, 10)));
	}
}
