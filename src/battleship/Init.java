package battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Init implements ActionListener, MouseListener
{
	int turn;
	Ship[] shipsA, shipsB;
	boolean[] shotsA, shotsB;
	SaveManager saveManager1Turn,
				saveManager1ShotsA,
				saveManager1ShotsB,
				saveManager1A,
				saveManager1B,
				saveManager2Turn,
				saveManager2ShotsA,
				saveManager2ShotsB,
				saveManager2A,
				saveManager2B,
				saveManager3Turn,
				saveManager3ShotsA,
				saveManager3ShotsB,
				saveManager3B,
				saveManager3A;
	SizeSetter sizeSetter;
	int[] sizes;
	Colors coolors;
	ImageIcon[] iconsA, iconsB;
	Color[] colors;
	Font[] fonts;
	SaveManager saveManager;
	MenuInterface menuInterface;
	GameInterface gameInterface;
	ShipPlacement shipPlacementA, shipPlacementB;
	Shooting shootingA, shootingB;
	Winner winner;

	public Init () throws IOException
	{
		turn = 1;
		shotsA = new boolean[100];
		shotsB = new boolean[100];
		shipsA = new Ship[5];
		setShips(shipsA);
		shipsB = new Ship[5];
		setShips(shipsB);
//
		saveManager1Turn = new SaveManager(1, "Turn");
		if (!saveManager1Turn.fileExistance())
		{
			saveManager1Turn.fileSave(turn);
		}

		saveManager1ShotsA = new SaveManager(1, "shotsA");
		if (!saveManager1ShotsA.fileExistance())
		{
			saveManager1ShotsA.fileSave(shotsA);
		}

		saveManager1ShotsB = new SaveManager(1, "shotsB");
		if (!saveManager1ShotsB.fileExistance())
		{
			saveManager1ShotsB.fileSave(shotsB);
		}

		saveManager1A = new SaveManager(1, "shipsA");
		if (!saveManager1A.fileExistance())
		{
			saveManager1A.fileSave(shipsA);
		}

		saveManager1B = new SaveManager(1, "shipsB");
		if (!saveManager1B.fileExistance())
		{
			saveManager1B.fileSave(shipsB);
		}

		saveManager2Turn = new SaveManager(2, "Turn");
		if (!saveManager2Turn.fileExistance())
		{
			saveManager2Turn.fileSave(turn);
		}

		saveManager2ShotsA = new SaveManager(2, "shotsA");
		if (!saveManager2ShotsA.fileExistance())
		{
			saveManager2ShotsA.fileSave(shotsA);
		}

		saveManager2ShotsB = new SaveManager(2, "shotsB");
		if (!saveManager2ShotsB.fileExistance())
		{
			saveManager2ShotsB.fileSave(shotsB);
		}

		saveManager2A = new SaveManager(2, "shipsA");
		if (!saveManager2A.fileExistance())
		{
			saveManager2A.fileSave(shipsA);
		}

		saveManager2B = new SaveManager(2, "shipsB");
		if (!saveManager2B.fileExistance())
		{
			saveManager2B.fileSave(shipsB);
		}

		saveManager3Turn = new SaveManager(3, "Turn");
		if (!saveManager3Turn.fileExistance())
		{
			saveManager3Turn.fileSave(turn);
		}

		saveManager3ShotsA = new SaveManager(3, "shotsA");
		if (!saveManager3ShotsA.fileExistance())
		{
			saveManager3ShotsA.fileSave(shotsA);
		}

		saveManager3ShotsB = new SaveManager(3, "shotsB");
		if (!saveManager3ShotsB.fileExistance())
		{
			saveManager3ShotsB.fileSave(shotsB);
		}

		saveManager3A = new SaveManager(3, "shipsA");
		if (!saveManager3A.fileExistance())
		{
			saveManager3A.fileSave(shipsA);
		}

		saveManager3B = new SaveManager(3, "shipsB");
		if (!saveManager3B.fileExistance())
		{
			saveManager3B.fileSave(shipsB);
		}
//
		sizeSetter = new SizeSetter();
		sizes = sizeSetter.sizes;

		coolors = new Colors(new String[] {"retro", "dark"});
		iconsA = coolors.iconsA;
		iconsB = coolors.iconsB;
		colors = coolors.colors;

		menuInterface = new MenuInterface(colors);
		menuInterface.newGame.addActionListener(this);
		menuInterface.loadGame.addActionListener(this);
		menuInterface.exit.addActionListener(this);
		menuInterface.save1.addActionListener(this);
		menuInterface.save2.addActionListener(this);
		menuInterface.save3.addActionListener(this);
		menuInterface.back.addActionListener(this);
		menuInterface.setVisible(true);

	}

	public static void main (String[] args) throws IOException
	{
		new Init();
	}

	public Ship[] setShips (Ship[] ss)
	{
		ss[0] = new Ship(2);
		ss[1] = new Ship(2);
		ss[2] = new Ship(3);
		ss[3] = new Ship(3);
		ss[4] = new Ship(4);

		return ss;
	}

	public void setGameInterface ()
	{
		gameInterface = new GameInterface(sizes, colors, turn);
		gameInterface.newGame.addActionListener(this);
		gameInterface.loadSave1.addActionListener(this);
		gameInterface.loadSave2.addActionListener(this);
		gameInterface.loadSave3.addActionListener(this);
		gameInterface.saveSave1.addActionListener(this);
		gameInterface.saveSave2.addActionListener(this);
		gameInterface.saveSave3.addActionListener(this);
		gameInterface.exit.addActionListener(this);
		gameInterface.nextTurn.addActionListener(this);

		if (!CheckShipPlacement(shipsA))
		{
			shipPlacementA = new ShipPlacement(sizes, colors, iconsA, shipsA, "PLAYER 1");
			shipPlacementA.addMouseListener(this);
			shipPlacementSetSelectionPanel(shipPlacementA.selectionButtons);
			shipPlacementSetPlacementPanel(shipPlacementA.placementButtons);
			shipPlacementA.done.addActionListener(this);
			gameInterface.cardPanel.add(shipPlacementA, "1");

			shipPlacementB = new ShipPlacement(sizes, colors, iconsB, shipsB, "PLAYER 2");
			shipPlacementB.addMouseListener(this);
			shipPlacementSetSelectionPanel(shipPlacementB.selectionButtons);
			shipPlacementSetPlacementPanel(shipPlacementB.placementButtons);
			shipPlacementB.done.addActionListener(this);
			gameInterface.cardPanel.add(shipPlacementB, "2");

			gameInterface.currentCard = 1;
			gameInterface.card.show(gameInterface.cardPanel, "1");
		}

		else if (!CheckShipPlacement(shipsB))
		{
			shipPlacementB = new ShipPlacement(sizes, colors, iconsB, shipsB, "PLAYER 2");
			shipPlacementB.addMouseListener(this);
			shipPlacementSetSelectionPanel(shipPlacementB.selectionButtons);
			shipPlacementSetPlacementPanel(shipPlacementB.placementButtons);
			shipPlacementB.done.addActionListener(this);
			gameInterface.cardPanel.add(shipPlacementB, "2");

			gameInterface.currentCard = 2;
			gameInterface.card.show(gameInterface.cardPanel, "2");
		}

		else
		{
			shootingA = new Shooting(sizes, colors, shipsA, shipsB, shotsA, shotsB, "PLAYER 1");

			for (int i = 0; i < shootingA.shootingButtons.length; i++)
			{
				shootingA.shootingButtons[i].addActionListener(this);
			}

			gameInterface.cardPanel.add(shootingA, "4");

			shootingB = new Shooting(sizes, colors, shipsB, shipsA, shotsB, shotsA, "PLAYER 2");

			for (int i = 0; i < shootingB.shootingButtons.length; i++)
			{
				shootingB.shootingButtons[i].addActionListener(this);
			}

			gameInterface.cardPanel.add(shootingB, "5");

			gameInterface.card.show(gameInterface.cardPanel, "3");
			gameInterface.currentCard = 3;
		}

		menuInterface.setVisible(false);
		gameInterface.setVisible(true);
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

	public boolean CheckShipPlacement (Ship[] ss)
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

		if (CheckShipPlacement(ships))
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
				turn = 1;
				shotsA = new boolean[100];
				shotsB = new boolean[100];
				setShips(shipsA);
				setShips(shipsB);
				setGameInterface();
			}

			else if (buttonText == "Load Game")
			{
				menuInterface.card.last(menuInterface.cardPanel);
			}

			else if (buttonText == "Save 1")
			{
				turn = saveManager1Turn.fileLoad(turn);
				shipsA = saveManager1A.fileLoad(shipsA);
				shipsB = saveManager1B.fileLoad(shipsB);
				shotsA = saveManager1ShotsA.fileLoad(shotsA);
				shotsB = saveManager1ShotsB.fileLoad(shotsB);

				menuInterface.card.first(menuInterface.cardPanel);
				setGameInterface();
			}

			else if (buttonText == "Save 2")
			{
				turn = saveManager2Turn.fileLoad(turn);
				shipsA = saveManager2A.fileLoad(shipsA);
				shipsB = saveManager2B.fileLoad(shipsB);
				shotsA = saveManager2ShotsA.fileLoad(shotsA);
				shotsB = saveManager2ShotsB.fileLoad(shotsB);

				menuInterface.card.first(menuInterface.cardPanel);
				setGameInterface();
			}

			else if (buttonText == "Save 3")
			{
				turn = saveManager3Turn.fileLoad(turn);
				shipsA = saveManager3A.fileLoad(shipsA);
				shipsB = saveManager3B.fileLoad(shipsB);
				shotsA = saveManager3ShotsA.fileLoad(shotsA);
				shotsB = saveManager3ShotsB.fileLoad(shotsB);

				menuInterface.card.first(menuInterface.cardPanel);
				setGameInterface();
			}

			else if (buttonText == "Back")
			{
				menuInterface.card.first(menuInterface.cardPanel);
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
					shootingA = new Shooting(sizes, colors, shipsA, shipsB, shotsA, shotsB, "PLAYER 1");

					for (int i = 0; i < shootingA.shootingButtons.length; i++)
					{
						shootingA.shootingButtons[i].addActionListener(this);
					}

					gameInterface.cardPanel.add(shootingA, "4");

					shootingB = new Shooting(sizes, colors, shipsB, shipsA, shotsB, shotsA, "PLAYER 2");

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
				}

				if (buttonName == "B")
				{
					gameInterface.currentCard = 5;
					gameInterface.card.show(gameInterface.cardPanel, "5");
				}
			}

			else if (gameInterface.currentCard == 4)
			{
				int coordinates = Integer.parseInt(clickedButton.getName());

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
							shotsA[coordinates] = true;
							shootingA.shootingButtons[coordinates].setBackground(colors[7]);
							shootingB.playerShipsLabels[coordinates].setBackground(colors[7]);
						}

						shootingA.shootingButtons[coordinates].setEnabled(false);
					}
				}

				if (!checkWinner(shipsB, coordinates, "PLAYER 1"))
				{
					turn = 2;
					gameInterface.nextTurn.setName("B");
					gameInterface.nextTurn.setText("PLAYER 2'S TURN");
					gameInterface.currentCard = 3;
					gameInterface.card.show(gameInterface.cardPanel, "3");
				}
			}


			else if (gameInterface.currentCard == 5)
			{
				int coordinates = Integer.parseInt(clickedButton.getName());

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
							shotsB[coordinates] = true;
							shootingB.shootingButtons[coordinates].setBackground(colors[7]);
							shootingA.playerShipsLabels[coordinates].setBackground(colors[7]);
						}

						shootingB.shootingButtons[coordinates].setEnabled(false);
					}
				}

				if (!checkWinner(shipsA, coordinates, "PLAYER 2"))
				{
					turn = 1;
					gameInterface.nextTurn.setName("A");
					gameInterface.nextTurn.setText("PLAYER 1'S TURN");
					gameInterface.currentCard = 3;
					gameInterface.card.show(gameInterface.cardPanel, "3");
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

			if (clickedItem == gameInterface.newGame)
			{
				gameInterface.setVisible(false);
				turn = 1;
				shotsA = new boolean[100];
				shotsB = new boolean[100];
				setShips(shipsA);
				setShips(shipsB);
				setGameInterface();
				gameInterface.setVisible(true);
			}

			else if (clickedItem == gameInterface.loadSave1)
			{
				gameInterface.setVisible(false);

				turn = saveManager1Turn.fileLoad(turn);
				shipsA = saveManager1A.fileLoad(shipsA);
				shipsB = saveManager1B.fileLoad(shipsB);
				shotsA = saveManager1ShotsA.fileLoad(shotsA);
				shotsB = saveManager1ShotsB.fileLoad(shotsB);

				setGameInterface();

				gameInterface.setVisible(true);
			}

			else if (clickedItem == gameInterface.loadSave2)
			{
				gameInterface.setVisible(false);

				turn = saveManager2Turn.fileLoad(turn);
				shipsA = saveManager2A.fileLoad(shipsA);
				shipsB = saveManager2B.fileLoad(shipsB);
				shotsA = saveManager2ShotsA.fileLoad(shotsA);
				shotsB = saveManager2ShotsB.fileLoad(shotsB);

				setGameInterface();

				gameInterface.setVisible(true);
			}

			else if (clickedItem == gameInterface.loadSave3)
			{
				gameInterface.setVisible(false);

				turn = saveManager3Turn.fileLoad(turn);
				shipsA = saveManager3A.fileLoad(shipsA);
				shipsB = saveManager3B.fileLoad(shipsB);
				shotsA = saveManager3ShotsA.fileLoad(shotsA);
				shotsB = saveManager3ShotsB.fileLoad(shotsB);

				setGameInterface();

				gameInterface.setVisible(true);
			}

			else if (clickedItem == gameInterface.saveSave1)
			{
				for (Ship ship: shipsA)
				{
					ship.selected = false;
				}

				for (Ship ship: shipsB)
				{
					ship.selected = false;
				}

				saveManager1Turn.fileSave(turn);
				saveManager1A.fileSave(shipsA);
				saveManager1B.fileSave(shipsB);
				saveManager1ShotsA.fileSave(shotsA);
				saveManager1ShotsB.fileSave(shotsB);
			}

			else if (clickedItem == gameInterface.saveSave2)
			{
				saveManager2Turn.fileSave(turn);
				saveManager2A.fileSave(shipsA);
				saveManager2B.fileSave(shipsB);
				saveManager2ShotsA.fileSave(shotsA);
				saveManager2ShotsB.fileSave(shotsB);
			}

			else if (clickedItem == gameInterface.saveSave3)
			{
				saveManager3Turn.fileSave(turn);
				saveManager3A.fileSave(shipsA);
				saveManager3B.fileSave(shipsB);
				saveManager3ShotsA.fileSave(shotsA);
				saveManager3ShotsB.fileSave(shotsB);
			}

			else if (clickedItem == gameInterface.exit)
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
