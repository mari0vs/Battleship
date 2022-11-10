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
	ShipPlacement shipPlacementA, shipPlacementB;
	Shooting shootingA, shootingB;
	Ship[] shipsA, shipsB;

	public GameInterface ()
	{
		//------------------------------ Frame ------------------------------

		setTitle("Menu");
		setBounds(10, 100, 1200, 1000);
		//setExtendedState(JFrame.MAXIMIZED_BOTH); 
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

		shipPlacementA = new ShipPlacement(shipsA, "Player 1");
		shipPlacementA.next.addActionListener(this);
		cardPanel.add(shipPlacementA, "1");

		shipPlacementB = new ShipPlacement(shipsB, "Player 2");
		shipPlacementB.next.addActionListener(this);
		cardPanel.add(shipPlacementB, "2");

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

			if (buttonText == "Next")
			{
				if (currentCard == 1)
				{
					currentCard = 2;
					card.show(cardPanel, "2");
				}

				else if (currentCard == 2)
				{
					shootingA = new Shooting(shipsA, shipsB, "Player 1");
					cardPanel.add(shootingA, "3");

					shootingB = new Shooting(shipsB, shipsA, "Player 2");
					cardPanel.add(shootingB, "4");

					currentCard = 3;
					card.show(cardPanel, "3");
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
		}
	}

	public static void main (String[] args)
	{
		GameInterface gi = new GameInterface();
		gi.setVisible(true);
	}
}
