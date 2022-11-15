package battleship;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")

public class MenuInterface extends JFrame implements ActionListener
{
	JPanel menuPanel, westPanel, southPanel, eastPanel;
	JLabel menuTitle;
	JButton newGame, loadGame, exit;
	int screenWidth, screenHeight;
	Colors coolors;
	Color[] colors;
	GameInterface gi;

	public MenuInterface ()
	{
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		screenWidth = gd.getDisplayMode().getWidth();
		screenHeight = gd.getDisplayMode().getHeight();

		Font titleFont = new Font("Comic Sans", Font.BOLD, 75);
		Font buttonFont = new Font("Comic Sans", Font.BOLD, 50);

		coolors = new Colors(new String[] {"retro", "dark"});
		colors = coolors.colors;

		/*
		background 			0
		foreground 			1
		highlight			2
		menu				3

		!placeable & hit 	4
		placeable & !hit	5
		board				6
		ships				7
		*/

		//Frame
		setTitle("Menu");
		setSize((int) Math.round(screenWidth*0.5), (int) Math.round(screenHeight*0.5));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Content Pane
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout(20, 20));
		contentPane.setBackground(colors[0]);

		menuTitle = new JLabel("Battleship", SwingConstants.CENTER);
		menuTitle.setFont(titleFont);
		menuTitle.setForeground(colors[1]);
		contentPane.add(menuTitle, BorderLayout.NORTH);

		//Panel
		menuPanel = new JPanel();
		menuPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, colors[2]));
		menuPanel.setBackground(colors[5]);
		contentPane.add(menuPanel, BorderLayout.CENTER);

		BoxLayout boxlayout = new BoxLayout(menuPanel, BoxLayout.Y_AXIS);
		menuPanel.setLayout(boxlayout);

		menuPanel.add(Box.createRigidArea(new Dimension(10, 20)));

		newGame = new JButton("New Game");
		newGame.setFocusable(false);
		newGame.setOpaque(true);
		newGame.setFont(buttonFont);
		newGame.setBackground(colors[0]);
		newGame.setForeground(colors[1]);
		newGame.addActionListener(this);
		menuPanel.add(newGame);

		menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		loadGame = new JButton("Load Game");
		loadGame.setFocusable(false);
		loadGame.setOpaque(true);
		loadGame.setFont(buttonFont);
		loadGame.setBackground(colors[0]);
		loadGame.setForeground(colors[1]);
		loadGame.addActionListener(this);
		menuPanel.add(loadGame);

		menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		exit = new JButton("Exit");
		exit.setFocusable(false);
		exit.setOpaque(true);
		exit.setFont(buttonFont);
		exit.setBackground(colors[0]);
		exit.setForeground(colors[1]);
		exit.addActionListener(this);
		menuPanel.add(exit);

		westPanel = new JPanel();
		westPanel.setOpaque(false);
		contentPane.add(westPanel, BorderLayout.WEST);

		southPanel = new JPanel();
		southPanel.setOpaque(false);
		contentPane.add(southPanel, BorderLayout.SOUTH);

		eastPanel = new JPanel();
		eastPanel.setOpaque(false);
		contentPane.add(eastPanel, BorderLayout.EAST);
	}

	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource() instanceof JButton)
		{
			JButton clickedButton = (JButton) e.getSource();
			String buttonText = clickedButton.getText();

			if (buttonText == "New Game")
			{
				gi = new GameInterface(colors);
				gi.exit.addActionListener(this);
				gi.newGame.addActionListener(this);
				// gi.ng.addActionListener(this);
				gi.setVisible(true);
				setVisible(false);
			}

			else if (buttonText == "Load Game")
			{
				gi = new GameInterface(colors);
				gi.exit.addActionListener(this);
				gi.newGame.addActionListener(this);
				// gi.ng.addActionListener(this);

				for (Ship ship: gi.shipsA)
				{
					int rand = (int) Math.floor(Math.random() * (55 - 0 + 1) + 0);

					for (int i = 0; i < ship.size; i++)
					{
						ship.position[i] = rand + i;
					}
				}

				for (Ship ship: gi.shipsB)
				{
					int rand = (int) Math.floor(Math.random() * (55 - 0 + 1) + 0);

					for (int i = 0; i < ship.size; i++)
					{
						ship.position[i] = rand + i;
					}
				}

				gi.setVisible(true);
				setVisible(false);
			}

			else
			{
				System.exit(0);
			}
		}

		else
		{
			JMenuItem clickedItem = (JMenuItem) e.getSource();
			String clickedItemText = clickedItem.getText();

			if (clickedItemText == "New Game")
			{
				gi = new GameInterface(colors);
				gi.exit.addActionListener(this);
				gi.newGame.addActionListener(this);
				// gi.ng.addActionListener(this);
			}

			else if (clickedItemText == "Load Game")
			{
				gi = new GameInterface(colors);
				gi.exit.addActionListener(this);
				gi.newGame.addActionListener(this);
				// gi.ng.addActionListener(this);

				/*for (Ship ship: gi.shipsA)
				{
					int rand = (int) Math.floor(Math.random() * (55 - 0 + 1) + 0);

					for (int i = 0; i < ship.size; i++)
					{
						ship.position[i] = rand + i;
					}
				}

				for (Ship ship: gi.shipsB)
				{
					int rand = (int) Math.floor(Math.random() * (55 - 0 + 1) + 0);

					for (int i = 0; i < ship.size; i++)
					{
						ship.position[i] = rand + i;
					}
				}*/
			}

			else if (clickedItemText == "Exit")
			{
				gi = null;
				System.gc();
				setVisible(true);
			}
		}
	}

	public static void main (String[] args)
	{
		MenuInterface mi = new MenuInterface();
		mi.setVisible(true);
	}
}
