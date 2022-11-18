package battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Init implements ActionListener, MouseListener
{
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
		sizeSetter = new SizeSetter();
		sizes = sizeSetter.sizes;

		coolors = new Colors(new String[] {"retro", "dark"});
		colors = coolors.colors;

		menuInterface = new MenuInterface(colors);
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

	public void shipPlacementSetSelectionPanel (JButton[] bs)
	{
		for (int i = 0; i < bs.length; i++)
		{
			bs[i].addMouseListener(this);
		}
	}

	public void shipPlacementSetPlacementPanel (JButton[] bs)
	{
		for (int i = 0; i < bs.length; i++)
		{
			bs[i].addMouseListener(this);
		}
	}

	public boolean shipPlacementCheckShipPlacement (Ship[] ss)
	{
		int counter = ss.length;
		boolean allShipsPlaced = false;

		for (Ship s: ss)
		{
			if (s.placed)
			{
				counter--;
			}
		}

		if (counter == 0)
		{
			allShipsPlaced = true;
		}

		return allShipsPlaced;
	}

	public boolean shipPlacementCheckEnabledButtonsX (Ship s, JButton[] b, int c)
	{
		int counter = s.size;
		boolean allButtonsEnabled = false;

		for (int i = 0; i < s.size; i++)
		{
			if (b[c + i].isEnabled())
			{
				counter--;
			}
		}

		if (counter == 0)
		{
			allButtonsEnabled = true;
		}

		return allButtonsEnabled;
	}

	public boolean shipPlacementCheckEnabledButtonsY (Ship s, JButton[] b, int c)
	{
		int counter = s.size;
		boolean allButtonsEnabled = false;

		for (int i = 0; i < s.size; i++)
		{
			if (b[c + (i * 10)].isEnabled())
			{
				counter--;
			}
		}

		if (counter == 0)
		{
			allButtonsEnabled = true;
		}

		return allButtonsEnabled;
	}

	public boolean shipPlacementCheckButtonsSameRow (Ship s, JButton[] b, int c)
	{
		int counter = s.size;
		int buttonRow = Integer.parseInt(String.valueOf(c).substring(0,1));
		boolean allButtonsSameRow = false;

		if (c < 10)
		{
			for (int i = 0; i < s.size; i++)
			{
				if ((c + i) < 10)
				{
					counter--;
				}
			}
		}

		else
		{
			for (int i = 0; i < s.size; i++)
			{
				if (buttonRow == Integer.parseInt(Integer.toString(c + i).substring(0,1)))
				{
					counter--;
				}
			}
		}

		if (counter == 0)
		{
			allButtonsSameRow = true;
		}

		return allButtonsSameRow;
	}

	public void shipPlacementMouseEntered (Ship[] ships, ShipPlacement shipPlacement, int coordinates)
	{
		for (Ship ship: ships)
		{
			if (ship.selected && !ship.placed)
			{
				if (ship.orientation &&
					0 <= (coordinates) &&
					(coordinates + ship.size - 1) < 100 &&
					shipPlacementCheckEnabledButtonsX(ship, shipPlacement.placementButtons, coordinates) &&
					shipPlacementCheckButtonsSameRow(ship, shipPlacement.placementButtons, coordinates))
				{
					for (int i = 0; i < ship.size; i++)
					{
						shipPlacement.placementButtons[coordinates + i].setBackground(colors[5]);
					}
				}

				else if (!ship.orientation &&
						0 <= (coordinates) &&
						(coordinates + ((ship.size - 1) * 10)) < 100 &&
						shipPlacementCheckEnabledButtonsY(ship, shipPlacement.placementButtons, coordinates))
				{
					for (int i = 0; i < ship.size; i++)
					{
						shipPlacement.placementButtons[coordinates + (i * 10)].setBackground(colors[5]);
					}
				}

				else if (shipPlacement.placementButtons[coordinates].isEnabled())
				{
					shipPlacement.placementButtons[coordinates].setBackground(colors[6]);
				}
			}
		}
	}

	public void shipPlacementMouseExited (Ship[] ships, ShipPlacement shipPlacement, int coordinates)
	{
		for (Ship ship: ships)
		{
			if (ship.selected == true && ship.placed == false)
			{
				if (ship.orientation &&
					0 <= (coordinates) &&
					(coordinates + ship.size - 1) < 100 &&
					shipPlacementCheckEnabledButtonsX(ship, shipPlacement.placementButtons, coordinates) &&
					shipPlacementCheckButtonsSameRow(ship, shipPlacement.placementButtons, coordinates))
				{
					for (int i = 0; i < ship.size; i++)
					{
						shipPlacement.placementButtons[coordinates + i].setBackground(colors[4]);
					}
				}

				else if (!ship.orientation &&
						0 <= (coordinates) &&
						(coordinates + ((ship.size - 1) * 10)) < 100 &&
						shipPlacementCheckEnabledButtonsY(ship, shipPlacement.placementButtons, coordinates))
				{
					for (int i = 0; i < ship.size; i++)
					{
						shipPlacement.placementButtons[coordinates + (i * 10)].setBackground(colors[4]);
					}
				}

				else if (shipPlacement.placementButtons[coordinates].isEnabled())
				{
					shipPlacement.placementButtons[coordinates].setBackground(colors[4]);
				}
			}
		}
	}

	public void shipPlacementMousePressedBUTTON3 (Ship[] ships, ShipPlacement shipPlacement, JButton placementButton, int coordinates)
	{
		for (Ship ship: ships)
		{
			if (ship.selected)
			{
				ship.orientation = !ship.orientation;

				if (ship.orientation &&
					0 <= (coordinates) &&
					(coordinates + ship.size - 1) < 100 &&
					shipPlacementCheckEnabledButtonsX(ship, shipPlacement.placementButtons, coordinates) &&
					shipPlacementCheckButtonsSameRow(ship, shipPlacement.placementButtons, coordinates))
				{
					for (int i = 0; i < ship.size; i++)
					{
						shipPlacement.placementButtons[coordinates + i].setBackground(colors[5]);
					}
				}

				else if (!ship.orientation &&
						0 <= (coordinates) &&
						(coordinates + ((ship.size - 1) * 10)) < 100 &&
						shipPlacementCheckEnabledButtonsY(ship, shipPlacement.placementButtons, coordinates))
				{
					for (int i = 0; i < ship.size; i++)
					{
						shipPlacement.placementButtons[coordinates + (i * 10)].setBackground(colors[5]);
					}
				}

				else if (placementButton.isEnabled())
				{
					placementButton.setBackground(colors[6]);
				}
			}
		}
	}

	public void shipPlacementMousePressedBUTTON1 (Ship[] ships, ShipPlacement shipPlacement, int coordinates)
	{
		for (Ship ship: ships) 
		{
			if (ship.selected == true && ship.placed == false)
			{
				if (ship.orientation &&
					0 <= (coordinates) &&
					(coordinates + ship.size - 1) < 100 &&
					shipPlacementCheckEnabledButtonsX(ship, shipPlacement.placementButtons, coordinates) &&
					shipPlacementCheckButtonsSameRow(ship, shipPlacement.placementButtons, coordinates))
				{
					for (int i = 0; i < ship.size; i++)
					{
						ship.position[i] = coordinates + i;
						shipPlacement.placementButtons[coordinates + i].setBackground(colors[8]);
						shipPlacement.placementButtons[coordinates + i].setEnabled(false);
					}

					ship.placed = true;
					ship.selected = false;
					
					for (int i = 0; i < shipPlacement.selectionButtons.length; i++)
					{
						if (!ships[i].placed)
						{
							shipPlacement.selectionButtons[i].setEnabled(true);
						}
					}
				}

				else if (!ship.orientation &&
						0 <= (coordinates) &&
						(coordinates + ((ship.size - 1) * 10)) < 100 &&
						shipPlacementCheckEnabledButtonsY(ship, shipPlacement.placementButtons, coordinates))
				{
					for (int i = 0; i < ship.size; i++)
					{
						ship.position[i] = coordinates + (i * 10);
						shipPlacement.placementButtons[coordinates + (i * 10)].setBackground(colors[8]);
						shipPlacement.placementButtons[coordinates + (i * 10)].setEnabled(false);

					}

					ship.placed = true;
					ship.selected = false;

					for (int i = 0; i < shipPlacement.selectionButtons.length; i++)
					{
						if (!ships[i].placed)
						{
							shipPlacement.selectionButtons[i].setEnabled(true);
						}
					}
				}
			}
		}
	}

	public void shipPlacementMouseReleased (Ship[] ships, ShipPlacement shipPlacement, int coordinates)
	{
		ships[coordinates].selected = true;

		for (int i = 0; i < shipPlacement.selectionButtons.length; i++)
		{
			shipPlacement.selectionButtons[i].setEnabled(false);
		}

		if (shipPlacementCheckShipPlacement(ships))
		{
			shipPlacement.done.setEnabled(true);
		}
	}

	public boolean checkWinner (Ship[] ships, int coordinates, String player)
	{
		boolean w = true;
		boolean hit = false;

		for (Ship ship: ships)
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
			winner = new Winner(colors, sizes, player, shootingA.playerShipsPanel, shootingB.playerShipsPanel);
			winner.mainMenu.addActionListener(this);
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

				shipPlacementA = new ShipPlacement(sizes, colors, shipsA, "PLAYER 1");
				shipPlacementA.addMouseListener(this);
				shipPlacementSetSelectionPanel(shipPlacementA.selectionButtons);
				shipPlacementSetPlacementPanel(shipPlacementA.placementButtons);
				shipPlacementA.done.addActionListener(this);
				gameInterface.cardPanel.add(shipPlacementA, "1");

				shipPlacementB = new ShipPlacement(sizes, colors, shipsB, "PLAYER 2");
				shipPlacementB.addMouseListener(this);
				shipPlacementSetSelectionPanel(shipPlacementB.selectionButtons);
				shipPlacementSetPlacementPanel(shipPlacementB.placementButtons);
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
					shootingA = new Shooting(sizes, colors, shipsA, "PLAYER 1");

					for (int i = 0; i < shootingA.shootingButtons.length; i++)
					{
						shootingA.shootingButtons[i].addActionListener(this);
					}

					gameInterface.cardPanel.add(shootingA, "4");

					shootingB = new Shooting(sizes, colors, shipsB, "PLAYER 2");

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
								shootingA.shootingButtons[coordinates].setBackground(colors[6]);
								shootingB.playerShipsLabels[coordinates].setBackground(colors[6]);
							}

							else if (shootingB.playerShipsLabels[coordinates].getBackground() == colors[4])
							{
								shootingA.shootingButtons[coordinates].setBackground(colors[7]);
								shootingB.playerShipsLabels[coordinates].setBackground(colors[7]);
							}

							shootingA.shootingButtons[coordinates].setEnabled(false);
						}
					}

					if (!checkWinner(shipsB, coordinates, "PLAYER 1"))
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
								shootingB.shootingButtons[coordinates].setBackground(colors[6]);
								shootingA.playerShipsLabels[coordinates].setBackground(colors[6]);
							}

							else if (shootingA.playerShipsLabels[coordinates].getBackground() == colors[4])
							{
								shootingB.shootingButtons[coordinates].setBackground(colors[7]);
								shootingA.playerShipsLabels[coordinates].setBackground(colors[7]);
							}

							shootingB.shootingButtons[coordinates].setEnabled(false);
						}
					}

					if (!checkWinner(shipsA, coordinates, "PLAYER 2"))
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

	public void mouseEntered(MouseEvent e)
	{
		if (e.getSource() instanceof JButton)
		{
			JButton enteredButton = (JButton) e.getSource();
			int coordinates = Integer.parseInt(enteredButton.getName());

			if (enteredButton == shipPlacementA.placementButtons[coordinates])
			{
				shipPlacementMouseEntered(shipsA, shipPlacementA, coordinates);
			}

			else if (enteredButton == shipPlacementB.placementButtons[coordinates])
			{
				shipPlacementMouseEntered(shipsB, shipPlacementB, coordinates);
			}
		}
	}

	public void mouseExited(MouseEvent e)
	{
		if (e.getSource() instanceof JButton)
		{
			JButton exitedButton = (JButton) e.getSource();
			int coordinates = Integer.parseInt(exitedButton.getName());

			if (exitedButton == shipPlacementA.placementButtons[coordinates])
			{
				shipPlacementMouseExited(shipsA, shipPlacementA, coordinates);
			}

			else if (exitedButton == shipPlacementB.placementButtons[coordinates])
			{
				shipPlacementMouseExited(shipsB, shipPlacementB, coordinates);
			}
		}
	}

	public void mousePressed(MouseEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON3)
		{
			if (e.getSource() instanceof JButton)
			{
				JButton clickedButton = (JButton) e.getSource();
				int coordinates = Integer.parseInt(clickedButton.getName());

				for (JButton placementButton: shipPlacementA.placementButtons)
				{
					if (placementButton.isEnabled())
					{
						placementButton.setBackground(colors[4]);
					}
				}

				for (JButton placementButton: shipPlacementA.placementButtons)
				{
					if (clickedButton == placementButton)
					{
						shipPlacementMousePressedBUTTON3(shipsA, shipPlacementA, clickedButton, coordinates);
					}
				}

				for (JButton placementButton: shipPlacementB.placementButtons)
				{
					if (placementButton.isEnabled())
					{
						placementButton.setBackground(colors[4]);
					}
				}

				for (JButton placementButton: shipPlacementB.placementButtons)
				{
					if (clickedButton == placementButton)
					{
						shipPlacementMousePressedBUTTON3(shipsB, shipPlacementB, clickedButton, coordinates);
					}
				}
			}

			else
			{
				for (Ship ship: shipsA)
				{
					if (ship.selected)
					{
						ship.orientation = !ship.orientation;
					}
				}

				for (Ship ship: shipsB)
				{
					if (ship.selected)
					{
						ship.orientation = !ship.orientation;
					}
				}
			}
		}

//--------------------------------------------------------------------------------------------------------------

		else if (e.getSource() instanceof JButton && e.getButton() == MouseEvent.BUTTON1)
		{
			JButton clickedButton = (JButton) e.getSource();
			int coordinates = Integer.parseInt(clickedButton.getName());

			if (clickedButton == shipPlacementA.placementButtons[coordinates])
			{
				shipPlacementMousePressedBUTTON1(shipsA, shipPlacementA, coordinates);
			}

			if (clickedButton == shipPlacementB.placementButtons[coordinates])
			{
				shipPlacementMousePressedBUTTON1(shipsB, shipPlacementB, coordinates);
			}
		}
	}

	public void mouseReleased(MouseEvent e)
	{
		if (e.getSource() instanceof JButton && e.getButton() == MouseEvent.BUTTON1)
		{
			JButton clickedButton = (JButton) e.getSource();
			int coordinates = Integer.parseInt(clickedButton.getName());

			if (clickedButton.isEnabled() &&
				coordinates < shipPlacementA.selectionButtons.length &&
				clickedButton == shipPlacementA.selectionButtons[coordinates])
			{
				shipPlacementMouseReleased(shipsA, shipPlacementA, coordinates);
			}

			if (clickedButton.isEnabled() &&
				coordinates < shipPlacementB.selectionButtons.length &&
				clickedButton == shipPlacementB.selectionButtons[coordinates])
			{
				shipPlacementMouseReleased(shipsB, shipPlacementB, coordinates);
			}
		}
	}

	public void mouseClicked (MouseEvent e)
	{}
}
