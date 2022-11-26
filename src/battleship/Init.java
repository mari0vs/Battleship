package battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Init implements ActionListener, MouseListener
{
	String playerA, playerB;
	Ship[] shipsA, shipsB;
	boolean shotsA[], shotsB[], turn;
	SaveManager saveManager1, saveManager2, saveManager3;
	SizeSetter sizeSetter;
	Assets assets;
	MenuInterface menuInterface;
	GameInterface gameInterface;
	ShipPlacement shipPlacementA, shipPlacementB;
	Shooting shootingA, shootingB;
	Winner winner;

	public Init () throws IOException
	{
		playerA = "PLAYER 1";
		playerB = "PLAYER 2";
		sizeSetter = new SizeSetter();
		assets = new Assets(new String[] {"retro", "dark"});

		saveManager1 = new SaveManager(1);
		saveManager2 = new SaveManager(2);
		saveManager3 = new SaveManager(3);

		menuInterface = new MenuInterface(assets);
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

	public void setNewGame ()
	{
		shipsA = new Ship[5];
		shipsA[0] = new Ship(2);
		shipsA[1] = new Ship(2);
		shipsA[2] = new Ship(3);
		shipsA[3] = new Ship(3);
		shipsA[4] = new Ship(4);

		shipsB = new Ship[5];
		shipsB[0] = new Ship(2);
		shipsB[1] = new Ship(2);
		shipsB[2] = new Ship(3);
		shipsB[3] = new Ship(3);
		shipsB[4] = new Ship(4);

		shotsA = new boolean[100];
		shotsB = new boolean[100];

		turn = true;

		setGameInterface();
	}

	public void setGameInterface ()
	{
		gameInterface = new GameInterface(sizeSetter, assets);
		gameInterface.setTurnButtonPlayer(turn, playerA, playerB);
		gameInterface.newGame.addActionListener(this);
		gameInterface.loadSave1.addActionListener(this);
		gameInterface.loadSave2.addActionListener(this);
		gameInterface.loadSave3.addActionListener(this);
		gameInterface.saveSave1.addActionListener(this);
		gameInterface.saveSave2.addActionListener(this);
		gameInterface.saveSave3.addActionListener(this);
		gameInterface.exit.addActionListener(this);
		gameInterface.nextTurn.addActionListener(this);

		if (!checkShipPlacement(shipsA))
		{
			shipPlacementA = new ShipPlacement(sizeSetter, assets, shipsA, playerA);
			shipPlacementA.addMouseListener(this);
			setButtonsListener(shipPlacementA.selectionButtons, false);
			setButtonsListener(shipPlacementA.placementButtons, false);
			shipPlacementA.done.addActionListener(this);
			gameInterface.cardPanel.add(shipPlacementA, "1");

			shipPlacementB = new ShipPlacement(sizeSetter, assets, shipsB, playerB);
			shipPlacementB.addMouseListener(this);
			setButtonsListener(shipPlacementB.selectionButtons, false);
			setButtonsListener(shipPlacementB.placementButtons, false);
			shipPlacementB.done.addActionListener(this);
			gameInterface.cardPanel.add(shipPlacementB, "2");

			gameInterface.currentCard = 1;
			gameInterface.card.show(gameInterface.cardPanel, "1");
		}

		else if (!checkShipPlacement(shipsB))
		{
			shipPlacementA = new ShipPlacement(sizeSetter, assets, shipsA, playerA);
			shipPlacementA.addMouseListener(this);
			setButtonsListener(shipPlacementA.selectionButtons, false);
			setButtonsListener(shipPlacementA.placementButtons, false);
			shipPlacementA.done.addActionListener(this);
			gameInterface.cardPanel.add(shipPlacementA, "1");

			shipPlacementB = new ShipPlacement(sizeSetter, assets, shipsB, playerB);
			shipPlacementB.addMouseListener(this);
			setButtonsListener(shipPlacementB.selectionButtons, false);
			setButtonsListener(shipPlacementB.placementButtons, false);
			shipPlacementB.done.addActionListener(this);
			gameInterface.cardPanel.add(shipPlacementB, "2");

			gameInterface.currentCard = 2;
			gameInterface.card.show(gameInterface.cardPanel, "2");
		}

		else
		{
			shootingA = new Shooting(sizeSetter, assets, playerA);
			shootingA.setPlayerShipsPanel(assets, shipsA, shotsB);
			shootingA.setShootingPanel(assets, shipsB, shotsA);
			setButtonsListener(shootingA.shootingButtons, true);
			gameInterface.cardPanel.add(shootingA, "4");

			shootingB = new Shooting(sizeSetter, assets, playerB);
			shootingB.setPlayerShipsPanel(assets, shipsB, shotsA);
			shootingB.setShootingPanel(assets, shipsA, shotsB);
			setButtonsListener(shootingB.shootingButtons, true);
			gameInterface.cardPanel.add(shootingB, "5");

			if (checkWinner(shipsB, playerA) || checkWinner(shipsA, playerB))
			{
				gameInterface.currentCard = 6;
				gameInterface.card.show(gameInterface.cardPanel, "6");
			}

			else
			{
				gameInterface.card.show(gameInterface.cardPanel, "3");
				gameInterface.currentCard = 3;
			}
		}

		menuInterface.setVisible(false);
		gameInterface.setVisible(true);
	}

	public boolean checkShipPlacement (Ship[] ss)
	{
		boolean allShipsPlaced = true;

		for (Ship s: ss)
		{
			allShipsPlaced = allShipsPlaced && s.placed;
		}

		return allShipsPlaced;
	}

	public void setButtonsListener (JButton[] buttons, boolean boo)
	{
		if (boo)
		{
			for (JButton button: buttons)
			{
				button.addActionListener(this);
			}
		}

		else
		{
			for (JButton button: buttons)
			{
				button.addMouseListener(this);
			}
		}
	}

	public boolean checkHit (Ship[] ss, boolean[] shots, Shooting playerShooting, Shooting enemyShooting, int coordinates)
	{
		boolean hit = false;

		for (Ship ship: ss)
		{
			ship.sunk = true;

			for (int i = 0; i < ship.size; i++)
			{
				if (coordinates == ship.position[i])
				{
					hit = true;
					ship.hits[i] = true;
					playerShooting.shootingButtons[coordinates].setBackground(assets.colors[6]);
					enemyShooting.playerShipsLabels[coordinates].setBackground(assets.colors[6]);
				}

				else if (enemyShooting.playerShipsLabels[coordinates].getBackground() == assets.colors[4])
				{
					shots[coordinates] = true;
					playerShooting.shootingButtons[coordinates].setBackground(assets.colors[7]);
					enemyShooting.playerShipsLabels[coordinates].setBackground(assets.colors[7]);
				}

				ship.sunk = ship.sunk && ship.hits[i];
			}
		}

		playerShooting.shootingButtons[coordinates].setEnabled(false);
		return hit;
	}

	public boolean checkWinner (Ship[] ships, String player)
	{
		boolean w = true;

		for (Ship ship: ships)
		{
			w = w && ship.sunk;
		}

		if (w)
		{
			winner = new Winner(sizeSetter, assets, player, shootingA.playerShipsPanel, shootingB.playerShipsPanel);
			winner.mainMenu.addActionListener(this);
			gameInterface.cardPanel.add(winner, "6");
		}

		return w;
	}

	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource() instanceof JButton)
		{
			JButton clickedButton = (JButton) e.getSource();
			String buttonText = clickedButton.getText();

			if (buttonText == "New Game")
			{
				setNewGame();
			}

			else if (buttonText == "Load Game")
			{
				menuInterface.card.last(menuInterface.cardPanel);
			}

			else if (buttonText == "Save 1")
			{
				turn = saveManager1.fileLoad(turn);
				shipsA = saveManager1.fileLoad("A", shipsA);
				shipsB = saveManager1.fileLoad("B", shipsB);
				shotsA = saveManager1.fileLoad("A", shotsA);
				shotsB = saveManager1.fileLoad("B", shotsB);

				menuInterface.card.first(menuInterface.cardPanel);
				setGameInterface();
			}

			else if (buttonText == "Save 2")
			{
				turn = saveManager2.fileLoad(turn);
				shipsA = saveManager2.fileLoad("A", shipsA);
				shipsB = saveManager2.fileLoad("B", shipsB);
				shotsA = saveManager2.fileLoad("A", shotsA);
				shotsB = saveManager2.fileLoad("B", shotsB);

				menuInterface.card.first(menuInterface.cardPanel);
				setGameInterface();
			}

			else if (buttonText == "Save 3")
			{
				turn = saveManager3.fileLoad(turn);
				shipsA = saveManager3.fileLoad("A", shipsA);
				shipsB = saveManager3.fileLoad("B", shipsB);
				shotsA = saveManager3.fileLoad("A", shotsA);
				shotsB = saveManager3.fileLoad("B", shotsB);

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
					shootingA = new Shooting(sizeSetter, assets, playerA);
					shootingA.setPlayerShipsPanel(assets, shipsA, shotsB);
					shootingA.setShootingPanel(assets, shipsB, shotsA);
					setButtonsListener(shootingA.shootingButtons, true);
					gameInterface.cardPanel.add(shootingA, "4");

					shootingB = new Shooting(sizeSetter, assets, playerB);
					shootingB.setPlayerShipsPanel(assets, shipsB, shotsA);
					shootingB.setShootingPanel(assets, shipsA, shotsB);
					setButtonsListener(shootingB.shootingButtons, true);
					gameInterface.cardPanel.add(shootingB, "5");

					gameInterface.card.show(gameInterface.cardPanel, "3");
					gameInterface.currentCard = 3;

					gameInterface.currentCard = 3;
					gameInterface.card.show(gameInterface.cardPanel, "3");
				}
			}

			else if (gameInterface.currentCard == 3)
			{
				if (turn)
				{
					gameInterface.currentCard = 4;
					gameInterface.card.show(gameInterface.cardPanel, "4");
				}

				else
				{
					gameInterface.currentCard = 5;
					gameInterface.card.show(gameInterface.cardPanel, "5");
				}
			}

			else if (gameInterface.currentCard == 4)
			{
				int coordinates = Integer.parseInt(clickedButton.getName());

				if (!checkHit(shipsB, shotsA, shootingA, shootingB, coordinates))
				{
					turn = false;
					gameInterface.setTurnButtonPlayer(turn, playerA, playerB);

					gameInterface.currentCard = 3;
					gameInterface.card.show(gameInterface.cardPanel, "3");
				}

				else if (checkWinner(shipsB, playerA))
				{
					gameInterface.currentCard = 6;
					gameInterface.card.show(gameInterface.cardPanel, "6");
				}
			}


			else if (gameInterface.currentCard == 5)
			{
				int coordinates = Integer.parseInt(clickedButton.getName());

				if (!checkHit(shipsA, shotsB, shootingB, shootingA, coordinates))
				{
					turn = true;
					gameInterface.setTurnButtonPlayer(turn, playerA, playerB);

					gameInterface.currentCard = 3;
					gameInterface.card.show(gameInterface.cardPanel, "3");
				}

				else if (checkWinner(shipsA, playerB))
				{
					gameInterface.currentCard = 6;
					gameInterface.card.show(gameInterface.cardPanel, "6");
				}
			}

			else if (buttonText == "Main Menu")
			{
				gameInterface.setVisible(false);
				gameInterface = null;
				System.gc();
				menuInterface.setVisible(true);
			}
		}

		else
		{
			JMenuItem clickedItem = (JMenuItem) e.getSource();
			String clickedItemText = clickedItem.getText();

			if (clickedItem == gameInterface.newGame)
			{
				gameInterface.setVisible(false);
				setNewGame();
				gameInterface.setVisible(true);
			}

			else if (clickedItem == gameInterface.loadSave1)
			{
				gameInterface.setVisible(false);

				turn = saveManager1.fileLoad(turn);
				shipsA = saveManager1.fileLoad("A", shipsA);
				shipsB = saveManager1.fileLoad("B", shipsB);
				shotsA = saveManager1.fileLoad("A", shotsA);
				shotsB = saveManager1.fileLoad("B", shotsB);

				setGameInterface();
				gameInterface.setVisible(true);
			}

			else if (clickedItem == gameInterface.loadSave2)
			{
				gameInterface.setVisible(false);

				turn = saveManager2.fileLoad(turn);
				shipsA = saveManager2.fileLoad("A", shipsA);
				shipsB = saveManager2.fileLoad("B", shipsB);
				shotsA = saveManager2.fileLoad("A", shotsA);
				shotsB = saveManager2.fileLoad("B", shotsB);

				setGameInterface();
				gameInterface.setVisible(true);
			}

			else if (clickedItem == gameInterface.loadSave3)
			{
				gameInterface.setVisible(false);

				turn = saveManager3.fileLoad(turn);
				shipsA = saveManager3.fileLoad("A", shipsA);
				shipsB = saveManager3.fileLoad("B", shipsB);
				shotsA = saveManager3.fileLoad("A", shotsA);
				shotsB = saveManager3.fileLoad("B", shotsB);

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

				saveManager1.fileSave(turn);
				saveManager1.fileSave("A", shipsA);
				saveManager1.fileSave("B", shipsB);
				saveManager1.fileSave("A", shotsA);
				saveManager1.fileSave("B", shotsB);
			}

			else if (clickedItem == gameInterface.saveSave2)
			{
				for (Ship ship: shipsA)
				{
					ship.selected = false;
				}

				for (Ship ship: shipsB)
				{
					ship.selected = false;
				}

				saveManager2.fileSave(turn);
				saveManager2.fileSave("A", shipsA);
				saveManager2.fileSave("B", shipsB);
				saveManager2.fileSave("A", shotsA);
				saveManager2.fileSave("B", shotsB);
			}

			else if (clickedItem == gameInterface.saveSave3)
			{
				for (Ship ship: shipsA)
				{
					ship.selected = false;
				}

				for (Ship ship: shipsB)
				{
					ship.selected = false;
				}

				saveManager3.fileSave(turn);
				saveManager3.fileSave("A", shipsA);
				saveManager3.fileSave("B", shipsB);
				saveManager3.fileSave("A", shotsA);
				saveManager3.fileSave("B", shotsB);
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

	public boolean shipPlacementCheckEnabledButtonsX (Ship ship, JButton[] placementButtons, int coordinates)
	{
		boolean allButtonsEnabled = true;

		for (int i = 0; i < ship.size; i++)
		{
			allButtonsEnabled = allButtonsEnabled && placementButtons[coordinates + i].isEnabled();
		}

		return allButtonsEnabled;
	}

	public boolean shipPlacementCheckEnabledButtonsY (Ship ship, JButton[] placementButtons, int coordinates)
	{
		boolean allButtonsEnabled = true;

		for (int i = 0; i < ship.size; i++)
		{
			allButtonsEnabled = allButtonsEnabled && placementButtons[coordinates + (i * 10)].isEnabled();
		}

		return allButtonsEnabled;
	}

	public boolean shipPlacementCheckButtonsSameRow (Ship ship, int coordinates)
	{
		int buttonRow = Integer.parseInt(String.valueOf(coordinates).substring(0,1));
		boolean allButtonsSameRow = true;

		if (coordinates < 10)
		{
			for (int i = 0; i < ship.size; i++)
			{
				allButtonsSameRow = allButtonsSameRow && ((coordinates + i) < 10);
			}
		}

		else
		{
			for (int i = 0; i < ship.size; i++)
			{
				allButtonsSameRow = allButtonsSameRow && (buttonRow == Integer.parseInt(Integer.toString(coordinates + i).substring(0,1)));
			}
		}

		return allButtonsSameRow;
	}

	public void mouseEnteredAction (Ship[] ships, ShipPlacement shipPlacement, int coordinates)
	{
		for (Ship ship: ships)
		{
			if (ship.selected && !ship.placed)
			{
				if (ship.orientation &&
					0 <= (coordinates) &&
					(coordinates + ship.size - 1) < 100 &&
					shipPlacementCheckEnabledButtonsX(ship, shipPlacement.placementButtons, coordinates) &&
					shipPlacementCheckButtonsSameRow(ship, coordinates))
				{
					for (int i = 0; i < ship.size; i++)
					{
						shipPlacement.placementButtons[coordinates + i].setBackground(assets.colors[5]);
					}
				}

				else if (!ship.orientation &&
						0 <= (coordinates) &&
						(coordinates + ((ship.size - 1) * 10)) < 100 &&
						shipPlacementCheckEnabledButtonsY(ship, shipPlacement.placementButtons, coordinates))
				{
					for (int i = 0; i < ship.size; i++)
					{
						shipPlacement.placementButtons[coordinates + (i * 10)].setBackground(assets.colors[5]);
					}
				}

				else if (shipPlacement.placementButtons[coordinates].isEnabled())
				{
					shipPlacement.placementButtons[coordinates].setBackground(assets.colors[6]);
				}
			}
		}
	}

	public void mouseEntered (MouseEvent e)
	{
		if (e.getSource() instanceof JButton)
		{
			JButton enteredButton = (JButton) e.getSource();
			int coordinates = Integer.parseInt(enteredButton.getName());

			if (enteredButton == shipPlacementA.placementButtons[coordinates])
			{
				mouseEnteredAction(shipsA, shipPlacementA, coordinates);
			}

			else if (enteredButton == shipPlacementB.placementButtons[coordinates])
			{
				mouseEnteredAction(shipsB, shipPlacementB, coordinates);
			}
		}
	}

	public void mouseExitedAction (Ship[] ships, ShipPlacement shipPlacement, int coordinates)
	{
		for (Ship ship: ships)
		{
			if (ship.selected == true && ship.placed == false)
			{
				if (ship.orientation &&
					0 <= (coordinates) &&
					(coordinates + ship.size - 1) < 100 &&
					shipPlacementCheckEnabledButtonsX(ship, shipPlacement.placementButtons, coordinates) &&
					shipPlacementCheckButtonsSameRow(ship, coordinates))
				{
					for (int i = 0; i < ship.size; i++)
					{
						shipPlacement.placementButtons[coordinates + i].setBackground(assets.colors[4]);
					}
				}

				else if (!ship.orientation &&
						0 <= (coordinates) &&
						(coordinates + ((ship.size - 1) * 10)) < 100 &&
						shipPlacementCheckEnabledButtonsY(ship, shipPlacement.placementButtons, coordinates))
				{
					for (int i = 0; i < ship.size; i++)
					{
						shipPlacement.placementButtons[coordinates + (i * 10)].setBackground(assets.colors[4]);
					}
				}

				else if (shipPlacement.placementButtons[coordinates].isEnabled())
				{
					shipPlacement.placementButtons[coordinates].setBackground(assets.colors[4]);
				}
			}
		}
	}

	public void mouseExited (MouseEvent e)
	{
		if (e.getSource() instanceof JButton)
		{
			JButton exitedButton = (JButton) e.getSource();
			int coordinates = Integer.parseInt(exitedButton.getName());

			if (exitedButton == shipPlacementA.placementButtons[coordinates])
			{
				mouseExitedAction(shipsA, shipPlacementA, coordinates);
			}

			else if (exitedButton == shipPlacementB.placementButtons[coordinates])
			{
				mouseExitedAction(shipsB, shipPlacementB, coordinates);
			}
		}
	}

	public void mousePressedActionBUTTON3 (Ship[] ships, ShipPlacement shipPlacement, JButton placementButton, int coordinates)
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
					shipPlacementCheckButtonsSameRow(ship, coordinates))
				{
					for (int i = 0; i < ship.size; i++)
					{
						shipPlacement.placementButtons[coordinates + i].setBackground(assets.colors[5]);
					}
				}

				else if (!ship.orientation &&
						0 <= (coordinates) &&
						(coordinates + ((ship.size - 1) * 10)) < 100 &&
						shipPlacementCheckEnabledButtonsY(ship, shipPlacement.placementButtons, coordinates))
				{
					for (int i = 0; i < ship.size; i++)
					{
						shipPlacement.placementButtons[coordinates + (i * 10)].setBackground(assets.colors[5]);
					}
				}

				else if (placementButton.isEnabled())
				{
					placementButton.setBackground(assets.colors[6]);
				}
			}
		}
	}

	public void mousePressedActionBUTTON1 (Ship[] ships, ShipPlacement shipPlacement, int coordinates)
	{
		for (Ship ship: ships) 
		{
			if (ship.selected == true && ship.placed == false)
			{
				if (ship.orientation &&
					0 <= (coordinates) &&
					(coordinates + ship.size - 1) < 100 &&
					shipPlacementCheckEnabledButtonsX(ship, shipPlacement.placementButtons, coordinates) &&
					shipPlacementCheckButtonsSameRow(ship, coordinates))
				{
					for (int i = 0; i < ship.size; i++)
					{
						ship.position[i] = coordinates + i;
						shipPlacement.placementButtons[coordinates + i].setBackground(assets.colors[8]);
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
						shipPlacement.placementButtons[coordinates + (i * 10)].setBackground(assets.colors[8]);
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

	public void mousePressed (MouseEvent e)
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
						placementButton.setBackground(assets.colors[4]);
					}
				}

				for (JButton placementButton: shipPlacementA.placementButtons)
				{
					if (clickedButton == placementButton)
					{
						mousePressedActionBUTTON3(shipsA, shipPlacementA, clickedButton, coordinates);
					}
				}

				for (JButton placementButton: shipPlacementB.placementButtons)
				{
					if (placementButton.isEnabled())
					{
						placementButton.setBackground(assets.colors[4]);
					}
				}

				for (JButton placementButton: shipPlacementB.placementButtons)
				{
					if (clickedButton == placementButton)
					{
						mousePressedActionBUTTON3(shipsB, shipPlacementB, clickedButton, coordinates);
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

		else if (e.getSource() instanceof JButton && e.getButton() == MouseEvent.BUTTON1)
		{
			JButton clickedButton = (JButton) e.getSource();
			int coordinates = Integer.parseInt(clickedButton.getName());

			if (clickedButton == shipPlacementA.placementButtons[coordinates])
			{
				mousePressedActionBUTTON1(shipsA, shipPlacementA, coordinates);
			}

			if (clickedButton == shipPlacementB.placementButtons[coordinates])
			{
				mousePressedActionBUTTON1(shipsB, shipPlacementB, coordinates);
			}
		}
	}

	public void mouseReleasedAction (Ship[] ships, ShipPlacement shipPlacement, int coordinates)
	{
		ships[coordinates].selected = true;

		for (int i = 0; i < shipPlacement.selectionButtons.length; i++)
		{
			shipPlacement.selectionButtons[i].setEnabled(false);
		}

		if (checkShipPlacement(ships))
		{
			shipPlacement.done.setEnabled(true);
		}
	}

	public void mouseReleased (MouseEvent e)
	{
		if (e.getSource() instanceof JButton && e.getButton() == MouseEvent.BUTTON1)
		{
			JButton clickedButton = (JButton) e.getSource();
			int coordinates = Integer.parseInt(clickedButton.getName());

			if (clickedButton.isEnabled() &&
				coordinates < shipPlacementA.selectionButtons.length &&
				clickedButton == shipPlacementA.selectionButtons[coordinates])
			{
				mouseReleasedAction(shipsA, shipPlacementA, coordinates);
			}

			if (clickedButton.isEnabled() &&
				coordinates < shipPlacementB.selectionButtons.length &&
				clickedButton == shipPlacementB.selectionButtons[coordinates])
			{
				mouseReleasedAction(shipsB, shipPlacementB, coordinates);
			}
		}
	}

	public void mouseClicked (MouseEvent e)
	{}
}
