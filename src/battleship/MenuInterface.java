package battleship;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")

public class MenuInterface extends JFrame
{
	JPanel menuPanel, westPanel, southPanel, eastPanel;
	JLabel menuTitle;
	JButton newGame, loadGame, exit;
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

		menuPanel = new JPanel();
		menuPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, colors[2]));
		menuPanel.setBackground(colors[3]);
		contentPane.add(menuPanel, BorderLayout.CENTER);

		BoxLayout boxlayout = new BoxLayout(menuPanel, BoxLayout.Y_AXIS);
		menuPanel.setLayout(boxlayout);
// Buttons
		menuPanel.add(Box.createRigidArea(new Dimension(10, 20)));

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

		menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		exit = new JButton("Exit");
		exit.setFocusable(false);
		exit.setOpaque(true);
		exit.setFont(buttonFont);
		exit.setBackground(colors[0]);
		exit.setForeground(colors[1]);
		menuPanel.add(exit);
	}
}
