package battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Init implements ActionListener, MouseListener
{
	GraphicsDevice graphicsDevice;
	int screenWidth, screenHeight;
	SizeSetter sizeSetter;
	int[] sizes;
	Colors coolors;
	Color[] colors;
	Font[] fonts;
	MenuInterface menuInterface;
	Ship[] shipsA, shipsB;
	GameInterface gameInterface;
	ShipPlacement shipPlacementA, shipPlacementB;
	Shooting shootingA, shootingB;
	Winner winner;

	public Init ()
	{
		graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		screenWidth = graphicsDevice.getDisplayMode().getWidth();
		screenHeight = graphicsDevice.getDisplayMode().getHeight();

		sizeSetter = new SizeSetter();
		sizes = sizeSetter.sizes;

		coolors = new Colors(new String[] {"retro", "dark"});
		colors = coolors.colors;

		menuInterface = new MenuInterface(sizes, colors);
		menuInterface.newGame.addActionListener(this);
		menuInterface.loadGame.addActionListener(this);
		menuInterface.exit.addActionListener(this);
		menuInterface.setVisible(true);

	}

	public static void main (String[] args)
	{
		new Init();
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
		boolean w = true;
		boolean hit = false;

		for (Ship ship: ss)
		{
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
			gameInterface.cardPanel.add(winner, "6");

			gameInterface.currentCard = 6;
			gameInterface.card.show(gameInterface.cardPanel, "6");
		}

		return hit;
	}

	public void actionPerformed(ActionEvent e)
	{

		if (e.getSource() instanceof JButton)
		{
			JButton clickedButton = (JButton) e.getSource();
			String buttonText = clickedButton.getText();

			if (buttonText == "New Game")
			{
				shipsA = setShips(shipsA);
				shipsB = setShips(shipsB);

				gameInterface = new GameInterface(sizes, colors);
				gameInterface.newGame.addActionListener(this);
				gameInterface.loadGame.addActionListener(this);
				gameInterface.exit.addActionListener(this);
				gameInterface.nextTurn.addActionListener(this);
				// gameInterface.ng.addActionListener(this);

				shipPlacementA = new ShipPlacement(shipsA, "PLAYER 1", colors);
				shipPlacementA.done.addActionListener(this);
				gameInterface.cardPanel.add(shipPlacementA, "1");

				shipPlacementB = new ShipPlacement(shipsB, "PLAYER 2", colors);
				shipPlacementB.done.addActionListener(this);
				gameInterface.cardPanel.add(shipPlacementB, "2");

				gameInterface.card.show(gameInterface.cardPanel, "1");
				gameInterface.currentCard = 1;

				gameInterface.setVisible(true);
				menuInterface.setVisible(false);
			}

			else if (buttonText == "Load Game")
			{
				gameInterface = new GameInterface(sizes, colors);
				gameInterface.newGame.addActionListener(this);
				gameInterface.loadGame.addActionListener(this);
				gameInterface.exit.addActionListener(this);
				gameInterface.nextTurn.addActionListener(this);
				// gameInterface.ng.addActionListener(this);
/*
				for (Ship ship: gameInterface.shipsA)
				{
					int rand = (int) Math.floor(Math.random() * (55 - 0 + 1) + 0);

					for (int i = 0; i < ship.size; i++)
					{
						ship.position[i] = rand + i;
					}
				}

				for (Ship ship: gameInterface.shipsB)
				{
					int rand = (int) Math.floor(Math.random() * (55 - 0 + 1) + 0);

					for (int i = 0; i < ship.size; i++)
					{
						ship.position[i] = rand + i;
					}
				}
*/
				gameInterface.setVisible(true);
				menuInterface.setVisible(false);
			}

			else if (buttonText == "Exit")
			{
				System.exit(0);
			}

			else if (buttonText == "Done")
			{
				if (gameInterface.currentCard == 1)
				{
					gameInterface.currentCard = 2;
					gameInterface.card.show(gameInterface.cardPanel, "2");
				}

				else if (gameInterface.currentCard == 2)
				{
					shootingA = new Shooting(shipsA, "PLAYER 1", colors, screenWidth, screenHeight);

					for (int i = 0; i < shootingA.shootingButtons.length; i++)
					{
						shootingA.shootingButtons[i].addActionListener(this);
					}

					gameInterface.cardPanel.add(shootingA, "4");

					shootingB = new Shooting(shipsB, "PLAYER 2", colors, screenWidth, screenHeight);

					for (int i = 0; i < shootingB.shootingButtons.length; i++)
					{
						shootingB.shootingButtons[i].addActionListener(this);
					}

					gameInterface.cardPanel.add(shootingB, "5");

					gameInterface.currentCard = 3;
					gameInterface.card.show(gameInterface.cardPanel, "3");
				}
			}

			else if (gameInterface.currentCard == 3)
			{
				String buttonName = clickedButton.getName();

				if (buttonName == "A")
				{
					gameInterface.currentCard = 4;
					gameInterface.card.show(gameInterface.cardPanel, "4");
					clickedButton.setText("PLAYER 2'S TURN");
					clickedButton.setName("B");
				}

				if (buttonName == "B")
				{
					gameInterface.currentCard = 5;
					gameInterface.card.show(gameInterface.cardPanel, "5");
					clickedButton.setText("PLAYER 1'S TURN");
					clickedButton.setName("A");
				}
			}

			else if (gameInterface.currentCard == 4 || gameInterface.currentCard == 5)
			{
				int coordinates = Integer.parseInt(clickedButton.getName());

				if (gameInterface.currentCard == 4)
				{
					for (Ship enemyShip: shipsB)
					{
						for (int position: enemyShip.position)
						{
							if (coordinates == position)
							{
								shootingA.shootingButtons[coordinates].setBackground(colors[3]);
								shootingB.playerShipsLabels[coordinates].setBackground(colors[3]);
							}

							else if (shootingB.playerShipsLabels[coordinates].getBackground() == colors[5])
							{
								shootingA.shootingButtons[coordinates].setBackground(colors[4]);
								shootingB.playerShipsLabels[coordinates].setBackground(colors[4]);
							}

							shootingA.shootingButtons[coordinates].setEnabled(false);
						}
					}

					if (!checkWinner(coordinates,
									"PLAYER 1",
									shipsB,
									shootingA.playerShipsPanel,
									shootingB.playerShipsPanel))
					{
						gameInterface.currentCard = 3;
						gameInterface.card.show(gameInterface.cardPanel, "3");
					}
				}


				else if (gameInterface.currentCard == 5)
				{
					for (Ship enemyShip: shipsA)
					{
						for (int position: enemyShip.position)
						{
							if (coordinates == position)
							{
								shootingB.shootingButtons[coordinates].setBackground(colors[3]);
								shootingA.playerShipsLabels[coordinates].setBackground(colors[3]);
							}

							else if (shootingA.playerShipsLabels[coordinates].getBackground() == colors[5])
							{
								shootingB.shootingButtons[coordinates].setBackground(colors[4]);
								shootingA.playerShipsLabels[coordinates].setBackground(colors[4]);
							}

							shootingB.shootingButtons[coordinates].setEnabled(false);
						}
					}

					if (!checkWinner(coordinates,
									"PLAYER 2",
									shipsA,
									shootingA.playerShipsPanel,
									shootingB.playerShipsPanel))
					{
						gameInterface.currentCard = 3;
						gameInterface.card.show(gameInterface.cardPanel, "3");
					}
				}
			}

			else if (gameInterface.currentCard == 6)
			{
				if (buttonText == "Main Menu")
				{
					gameInterface.setVisible(false);
					gameInterface = null;
					System.gc();
					menuInterface.setVisible(true);
				}
			}
		}

		else
		{
			JMenuItem clickedItem = (JMenuItem) e.getSource();
			String clickedItemText = clickedItem.getText();

			if (clickedItemText == "New Game")
			{
				gameInterface.setVisible(false);
				gameInterface = new GameInterface(sizes, colors);
				gameInterface.newGame.addActionListener(this);
				gameInterface.loadGame.addActionListener(this);
				gameInterface.exit.addActionListener(this);
				gameInterface.nextTurn.addActionListener(this);
				// gameInterface.ng.addActionListener(this);

				gameInterface.setVisible(true);
			}

			else if (clickedItemText == "Sava Game")
			{}

			else if (clickedItemText == "Load Game")
			{
				gameInterface.setVisible(false);
				gameInterface = new GameInterface(sizes, colors);
				gameInterface.newGame.addActionListener(this);
				gameInterface.loadGame.addActionListener(this);
				gameInterface.exit.addActionListener(this);
				gameInterface.nextTurn.addActionListener(this);
				// gameInterface.ng.addActionListener(this);
/*
				for (Ship ship: gameInterface.shipsA)
				{
					int rand = (int) Math.floor(Math.random() * (55 - 0 + 1) + 0);

					for (int i = 0; i < ship.size; i++)
					{
						ship.position[i] = rand + i;
					}
				}

				for (Ship ship: gameInterface.shipsB)
				{
					int rand = (int) Math.floor(Math.random() * (55 - 0 + 1) + 0);

					for (int i = 0; i < ship.size; i++)
					{
						ship.position[i] = rand + i;
					}
				}
*/
				gameInterface.setVisible(true);
			}

			else if (clickedItemText == "Exit")
			{
				gameInterface.setVisible(false);
				gameInterface = null;
				System.gc();
				menuInterface.setVisible(true);
			}
		}
	}

	public void mouseClicked(MouseEvent e)
	{}

	public void mouseEntered(MouseEvent e)
	{}

	public void mouseExited(MouseEvent e)
	{}

	public void mousePressed(MouseEvent e)
	{}

	public void mouseReleased(MouseEvent e)
	{}
}
