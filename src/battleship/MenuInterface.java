package battleship;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")

public class MenuInterface extends JFrame implements ActionListener
{
	JPanel menuPanel, westPanel, southPanel, eastPanel;
	JLabel menuTitle, blankLabel;
	JButton newGame, loadGame, exit;
	Color[] colors;
	GameInterface gi;

	public MenuInterface ()
	{
		Font titleFont = new Font("Comic Sans", Font.BOLD, 75);
		Font buttonFont = new Font("Comic Sans", Font.BOLD, 50);
		colors = new Color[7];

		/*
		background 			0
		foreground 			1
		highlight			2

		!placeable & hit 	3
		placeable & !hit	4
		board & menu		5
		ships				6
		*/

		colors[0] = new Color(0, 48, 73);//dark blue
		colors[1] = new Color(241, 227, 211);//cream
		colors[2] = new Color(242, 208, 169);//orange

		colors[3] = new Color(216, 140, 154);//red
		colors[4] = new Color(153, 193, 185);//green
		colors[5] = new Color(102, 155, 188);//light blue
		colors[6] = new Color(142, 125, 190);//purple

		//Frame
		setTitle("Menu");
		setSize(400, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Content Pane
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout(10, 10));
		contentPane.setBackground(colors[0]);

		menuTitle = new JLabel("Battleship", SwingConstants.CENTER);
		menuTitle.setFont(titleFont);
		menuTitle.setForeground(colors[1]);
		contentPane.add(menuTitle, BorderLayout.NORTH);

		//Panel
		menuPanel = new JPanel();
		menuPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, colors[2]));
		// menuPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		menuPanel.setBackground(colors[5]);
		contentPane.add(menuPanel, BorderLayout.CENTER);

		BoxLayout boxlayout = new BoxLayout(menuPanel, BoxLayout.Y_AXIS);
		menuPanel.setLayout(boxlayout);

		westPanel = new JPanel();
		westPanel.setOpaque(false);
		contentPane.add(westPanel, BorderLayout.WEST);

		southPanel = new JPanel();
		southPanel.setOpaque(false);
		contentPane.add(southPanel, BorderLayout.SOUTH);

		eastPanel = new JPanel();
		eastPanel.setOpaque(false);
		contentPane.add(eastPanel, BorderLayout.EAST);

		menuPanel.add(Box.createRigidArea(new Dimension(20, 10)));

		newGame = new JButton("New Game");
		newGame.setFocusable(false);
		newGame.setOpaque(true);
		newGame.setFont(buttonFont);
		newGame.setBackground(colors[0]);
		newGame.setForeground(colors[1]);
		newGame.addActionListener(this);
		menuPanel.add(newGame);

		menuPanel.add(Box.createRigidArea(new Dimension(20, 10)));

		loadGame = new JButton("Load Game");
		loadGame.setFocusable(false);
		loadGame.setOpaque(true);
		loadGame.setFont(buttonFont);
		loadGame.setBackground(colors[0]);
		loadGame.setForeground(colors[1]);
		loadGame.addActionListener(this);
		menuPanel.add(loadGame);

		menuPanel.add(Box.createRigidArea(new Dimension(20, 10)));

		exit = new JButton("Exit");
		exit.setFocusable(false);
		exit.setOpaque(true);
		exit.setFont(buttonFont);
		exit.setBackground(colors[0]);
		exit.setForeground(colors[1]);
		exit.addActionListener(this);
		menuPanel.add(exit);

		blankLabel = new JLabel();
		menuPanel.add(blankLabel);
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
