package battleship;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")

public class GameInterface extends JFrame implements ActionListener
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

	public GameInterface (Color[] c)
	{
		colors = c;
		//------------------------------ Frame ------------------------------

		setTitle("Menu");
		//setBounds(10, 100, 1200, 1000);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//------------------------------ Menu ------------------------------

		Font font = new Font("Comic Sans", Font.BOLD, 15);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menu = new JMenu("Options");
		menu.setFont(font);
		menuBar.add(menu);

		newGame = new JMenuItem("New Game");
		newGame.setFont(font);
		newGame.addActionListener(this);
		menu.add(newGame);

		loadGame = new JMenuItem("Load Game");
		loadGame.setFont(font);
		loadGame.addActionListener(this);
		menu.add(loadGame);

		saveGame = new JMenuItem("Save Game");
		saveGame.setFont(font);
		saveGame.addActionListener(this);
		menu.add(saveGame);

		exit = new JMenuItem("Exit");
		exit.setFont(font);
		exit.addActionListener(this);
		menu.add(exit);

		//------------------------------ Containers ------------------------------

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		card = new CardLayout();
		cardPanel = new JPanel(card);
		contentPane.add(cardPanel, BorderLayout.CENTER);


		//-------------------- Content --------------------

		shipsA = setShips(shipsA);
		shipsB = setShips(shipsB);

		/*for (Ship s: shipsA)
		{
			s.print();
		}*/

		shipPlacementA = new ShipPlacement(shipsA, "PLAYER 1", colors);
		shipPlacementA.done.addActionListener(this);
		cardPanel.add(shipPlacementA, "1");

		shipPlacementB = new ShipPlacement(shipsB, "PLAYER 2", colors);
		shipPlacementB.done.addActionListener(this);
		cardPanel.add(shipPlacementB, "2");

		nextTurnPanel = new JPanel();
		nextTurnPanel.setSize(new Dimension(1000, 800));
		Border border = BorderFactory.createMatteBorder(400, 700, 400, 700, colors[0]);
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
		nextTurn.setBorder(new EmptyBorder(100, 100, 100, 100));
		nextTurn.setBackground(colors[0]);
		nextTurn.setForeground(colors[1]);
		nextTurn.setAlignmentX(CENTER_ALIGNMENT);
		nextTurn.addActionListener(this);
		nextTurnPanel.add(nextTurn, BorderLayout.CENTER);

		nextTurnPanel.add(Box.createVerticalGlue());

		card.first(cardPanel);
		currentCard = 1;
	}

	public Ship[] setShips (Ship[] ss)
	{
		ss = new Ship[5];

		ss[0] = new Ship(2);
		ss[1] = new Ship(2);
		ss[2] = new Ship(3);
		ss[3] = new Ship(3);
		ss[4] = new Ship(4);

		return ss;
	}

	public boolean checkWinner (int coordinates, String player, Ship[] ss, JPanel pPanel, JPanel ePanel)
	{
		boolean w = false;
		boolean hit = false;

		for (Ship ship: ss)
		{
			w = true;
			ship.sunk = true;

			for (int i = 0; i < ship.size; i++)
			{
				if (coordinates == ship.position[i])
				{
					ship.hits[i] = true;
					hit = ship.hits[i];
				}

				ship.sunk = ship.sunk && ship.hits[i];
			}
			w = w && ship.sunk;
		}

		if (w)
		{
			winner = new Winner(player, pPanel, ePanel, colors);
			winner.mm.addActionListener(this);
			cardPanel.add(winner, "6");

			currentCard = 6;
			card.show(cardPanel, "6");
		}

		return hit;
	}

	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource() instanceof JButton)
		{
			JButton clickedButton = (JButton) e.getSource();
			String buttonText = clickedButton.getText();

			if (buttonText == "Done")
			{
				if (currentCard == 1)
				{
					currentCard = 2;
					card.show(cardPanel, "2");
				}

				else if (currentCard == 2)
				{
					shootingA = new Shooting(shipsA, shipsB, "PLAYER 1", colors);

					for (int i = 0; i < shootingA.shootingButtons.length; i++)
					{
						shootingA.shootingButtons[i].addActionListener(this);
					}

					cardPanel.add(shootingA, "4");

					shootingB = new Shooting(shipsB, shipsA, "PLAYER 2", colors);

					for (int i = 0; i < shootingB.shootingButtons.length; i++)
					{
						shootingB.shootingButtons[i].addActionListener(this);
					}

					cardPanel.add(shootingB, "5");

					currentCard = 3;
					card.show(cardPanel, "3");
				}
			}

			else if (currentCard == 3)
			{
				String buttonName = clickedButton.getName();

				if (buttonName == "A")
				{
					currentCard = 4;
					card.show(cardPanel, "4");
					clickedButton.setText("PLAYER 2'S TURN");
					clickedButton.setName("B");
				}

				if (buttonName == "B")
				{
					currentCard = 5;
					card.show(cardPanel, "5");
					clickedButton.setText("PLAYER 1'S TURN");
					clickedButton.setName("A");
				}
			}

			else if (currentCard == 4 || currentCard == 5)
			{
				int coordinates = Integer.parseInt(clickedButton.getName());

				if (currentCard == 4)
				{
					for (Ship enemyShip: shipsB)
					{
						for (int position: enemyShip.position)
						{
							if (coordinates == position)
							{
								shootingB.playerShipsLabels[coordinates].setBackground(colors[3]);
							}

							else if (shootingB.playerShipsLabels[coordinates].getBackground() == colors[5])
							{
								shootingB.playerShipsLabels[coordinates].setBackground(colors[4]);
							}
						}
					}

					if (!checkWinner(coordinates,
									"PLAYER 1",
									shipsB,
									shootingA.playerShipsPanel,
									shootingB.playerShipsPanel))
					{
						currentCard = 3;
						card.show(cardPanel, "3");
					}
				}


				else if (currentCard == 5)
				{
					for (Ship enemyShip: shipsA)
					{
						for (int position: enemyShip.position)
						{
							if (coordinates == position)
							{
								shootingA.playerShipsLabels[coordinates].setBackground(colors[3]);
							}

							else if (shootingA.playerShipsLabels[coordinates].getBackground() == colors[5])
							{
								shootingA.playerShipsLabels[coordinates].setBackground(colors[4]);
							}
						}
					}

					if (!checkWinner(coordinates,
									"PLAYER 2",
									shipsA,
									shootingB.playerShipsPanel,
									shootingA.playerShipsPanel))
					{
						currentCard = 3;
						card.show(cardPanel, "3");
					}
				}
			}

			else
			{
				if (buttonText == "Main Menu")
				{
					exit.doClick();
				}
			}
		}

		else// (e.getSource() instanceof JMenuItem)
		{
			JMenuItem clickedItem = (JMenuItem) e.getSource();
			String clickedItemText = clickedItem.getText();

			if (clickedItemText == "Sava Game")
			{}

			else
			{
				setVisible(false);
			}
		}
	}
}
