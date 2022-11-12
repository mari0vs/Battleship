package battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")

public class GameInterface extends JFrame implements ActionListener
{
	JMenu menu;
	JMenuItem newGame, loadGame, saveGame, exit;
	CardLayout card;
	JPanel cardPanel;
	int currentCard;
	JButton nextTurn;
	Color[] colors;
	ShipPlacement shipPlacementA, shipPlacementB;
	Shooting shootingA, shootingB;
	Ship[] shipsA, shipsB;

	public GameInterface (Color[] c)
	{
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

		shipsA = new Ship[5];
		shipsB = new Ship[5];

		shipPlacementA = new ShipPlacement(shipsA, "PLAYER 1", c);
		shipPlacementA.done.addActionListener(this);
		cardPanel.add(shipPlacementA, "1");

		shipPlacementB = new ShipPlacement(shipsB, "PLAYER 2", c);
		shipPlacementB.done.addActionListener(this);
		cardPanel.add(shipPlacementB, "2");

		nextTurn = new JButton("PLAYER 1's turn");
		nextTurn.setName("Next Turn");
		nextTurn.setFocusable(true);
		nextTurn.setEnabled(true);
		nextTurn.setOpaque(true);
		nextTurn.setPreferredSize(new Dimension(0, 50));
		nextTurn.setBackground(Color.black);
		nextTurn.setForeground(Color.white);
		nextTurn.addActionListener(this);
		cardPanel.add(nextTurn, "3");

		card.first(cardPanel);
		currentCard = 1;
	}

	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource() instanceof JMenuItem)
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

		else
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

					currentCard = 4;
					card.show(cardPanel, "4");
				}

				/*for (int i = 0; i < shipsA.length; i++)
				{
					for (int j = 0; j < shipsA[i].size; j++)
					{
						System.out.println(shipsA[i].position[j]);
					}
					System.out.println();
				}
				System.out.println();*/
			}

			/*boolean turn = true;

			else if ()
			{}*/

			else
			{
				int coordinates = Integer.parseInt(clickedButton.getName());

				if (clickedButton == shootingA.shootingButtons[coordinates] && currentCard == 4)
				{
					boolean hit = false;
					boolean playerAWinner = false;

					for (Ship ship: shipsB)
					{
						for (int i = 0; i < ship.size; i++)
						{
							if (coordinates == ship.position[i])
							{
								while (!hit)
								{
									ship.hits[i] = true;
									hit = true;
								}

								if (!ship.hits[i])
								{
									ship.sunk = ship.hits[i];
								}

								else
								{
									ship.sunk = ship.hits[i];
								}
							}
						}

						/*for (boolean i: ship.hits)
						{
							if (!i)
							{
								ship.sunk = i;
							}

							else
							{
								ship.sunk = i;
							}
							
							System.out.println(ship.sunk);
						}
						System.out.println();*/

						/*if (!i)
							{
								ship.sunk = i;
							}

							else
							{
								ship.sunk = i;
							}*/

						playerAWinner = playerAWinner && ship.sunk;
					}

					if (playerAWinner)
					{
						System.out.println("Player 1 wins");
					}

					else if (!hit)
					{
						currentCard = 5;
						card.show(cardPanel, "5");
					}
				}

				else if (clickedButton == shootingB.shootingButtons[coordinates] && currentCard == 5)
				{
					boolean hit = false;

					for (int ship = 0; ship < shipsA.length; ship++)
					{
						for (int i = 0; i < shipsA[ship].size; i++)
						{
							if (coordinates == shipsA[ship].position[i])
							{
								hit = true;
							}
						}
					}

					if (!hit)
					{
						currentCard = 4;
						card.show(cardPanel, "4");
					}
				}
			}
		}
	}
}
