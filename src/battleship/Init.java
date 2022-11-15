package battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Init implements ActionListener, MouseListener
{
	GraphicsDevice graphicsDevice;
	int screenWidth, screenHeight;
	Colors coolors;
	Color[] colors;
	Font[] fonts;
	MenuInterface menuInterface;
	Ship[] ships;
	GameInterface gameInterface;
	ShipPlacement shipPlacementA, shipPlacementB;
	Shooting shootingA, shootingB;
	Winner winner;

	public Init ()
	{
		graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		screenWidth = graphicsDevice.getDisplayMode().getWidth();
		screenHeight = graphicsDevice.getDisplayMode().getHeight();

		coolors = new Colors(new String[] {"retro", "dark"});
		colors = coolors.colors;

		menuInterface = new MenuInterface();
		menuInterface.newGame.addActionListener(this);
		menuInterface.loadGame.addActionListener(this);
		menuInterface.exit.addActionListener(this);
		menuInterface.setVisible(true);

	}

	public static void main (String[] args)
	{
		new Init();
	}

	public void actionPerformed(ActionEvent e)
	{

		if (e.getSource() instanceof JButton)
		{
			JButton clickedButton = (JButton) e.getSource();
			String buttonText = clickedButton.getText();

			if (buttonText == "New Game")
			{
				gameInterface = new GameInterface(colors);
				gameInterface.exit.addActionListener(this);
				gameInterface.newGame.addActionListener(this);
				// gameInterface.ng.addActionListener(this);
				gameInterface.setVisible(true);
				menuInterface.setVisible(false);
			}

			else if (buttonText == "Load Game")
			{
				gameInterface = new GameInterface(colors);
				gameInterface.exit.addActionListener(this);
				gameInterface.newGame.addActionListener(this);
				// gameInterface.ng.addActionListener(this);

				/*for (Ship ship: gameInterface.shipsA)
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
				}*/

				gameInterface.setVisible(true);
				menuInterface.setVisible(false);
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
				gameInterface = new GameInterface(colors);
				gameInterface.exit.addActionListener(this);
				gameInterface.newGame.addActionListener(this);
				// gameInterface.ng.addActionListener(this);
			}

			else if (clickedItemText == "Load Game")
			{
				gameInterface = new GameInterface(colors);
				gameInterface.exit.addActionListener(this);
				gameInterface.newGame.addActionListener(this);
				// gameInterface.ng.addActionListener(this);

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
			}

			else if (clickedItemText == "Exit")
			{
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
