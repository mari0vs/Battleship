package battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")

public class ShipPlacement extends JPanel implements MouseListener
{
	JPanel selectionPanel, placementPanel, blankPanel;
	JLabel playerLabel;
	JButton next;
	JButton[] placementButtons, selectionButtons;
	Ship[] ships;

	public ShipPlacement (Ship[] mainShips, String player)
	{
		setLayout(new BorderLayout(200, 0));
		setBackground(Color.gray);
		addMouseListener(this);

		//------------------------------ Panels ------------------------------

		selectionPanel = new JPanel(new GridLayout(6, 1, 5, 5));
		selectionPanel.setPreferredSize(new Dimension(400, 0));
		selectionPanel.setBackground(Color.gray);
		add(selectionPanel, BorderLayout.WEST);

		placementPanel = new JPanel(new GridLayout(10, 10, 0, 0));
		placementPanel.setBackground(Color.gray);
		add(placementPanel, BorderLayout.CENTER);

		/*JPanel blankPanel = new JPanel(null);
		blankPanel.setPreferredSize(new Dimension(400, 0));
		blankPanel.setOpaque(false);
		add(blankPanel, BorderLayout.EAST);*/

		//------------------------------ Content ------------------------------

		playerLabel = new JLabel(player, SwingConstants.CENTER);
		playerLabel.setFont(new Font("Comic Sans", Font.BOLD, 60));
		playerLabel.setForeground(Color.black);
		add(playerLabel, BorderLayout.NORTH);

		ships = mainShips;

		selectionButtons = new JButton[ships.length];
		setSelectionPanel(ships, selectionButtons);

		next = new JButton("Next");
		next.setFocusable(false);
		next.setEnabled(false);
		next.setOpaque(true);
		next.setFont(new Font("Comic Sans", Font.BOLD, 30));
		next.setBackground(Color.darkGray);
		next.setForeground(Color.white);
		selectionPanel.add(next);

		placementButtons = new JButton[100];
		setPlacementPanel(placementButtons);
	}

//--------------------------------------------------------------------------------------------------------------

	public void setSelectionPanel (Ship[] s, JButton[] bs)
	{
		for (int i = 0; i < bs.length; i++)
		{
			if (i == 0 || i == 1)
			{
				s[i] = new Ship(2);
				bs[i] = new JButton("Small Ship");
				bs[i].setName(Integer.toString(i));
				bs[i].setFocusable(false);
				bs[i].setEnabled(true);
				bs[i].setOpaque(true);
				bs[i].setFont(new Font("Comic Sans", Font.BOLD, 30));
				bs[i].setBackground(Color.darkGray);
				bs[i].setForeground(Color.white);
				bs[i].addMouseListener(this);
				selectionPanel.add(bs[i]);
			}

			else if (i == 2 || i == 3)
			{
				s[i] = new Ship(3);
				bs[i] = new JButton("Medium Ship");
				bs[i].setName(Integer.toString(i));
				bs[i].setFocusable(false);
				bs[i].setEnabled(true);
				bs[i].setOpaque(true);
				bs[i].setFont(new Font("Comic Sans", Font.BOLD, 30));
				bs[i].setBackground(Color.darkGray);
				bs[i].setForeground(Color.white);
				bs[i].addMouseListener(this);
				selectionPanel.add(bs[i]);
			}

			else if (i == 4)
			{
				s[i] = new Ship(4);
				bs[i] = new JButton("Big Ship");
				bs[i].setName(Integer.toString(i));
				bs[i].setFocusable(false);
				bs[i].setEnabled(true);
				bs[i].setOpaque(true);
				bs[i].setFont(new Font("Comic Sans", Font.BOLD, 30));
				bs[i].setBackground(Color.darkGray);
				bs[i].setForeground(Color.white);
				bs[i].addMouseListener(this);
				selectionPanel.add(bs[i]);
			}
		}
	}

//--------------------------------------------------------------------------------------------------------------

	public boolean checkShipPlacement (Ship[] s)
	{
		int counter = s.length;
		boolean allShipsPlaced = false;

		for (int i = 0; i < s.length; i++)
		{
			if (s[i].placed)
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

//--------------------------------------------------------------------------------------------------------------

	public void setPlacementPanel (JButton[] bs)
	{
		for (int i = 0; i < bs.length; i++)
		{
			if (i < 10)
			{
				bs[i] = new JButton();
				bs[i].setName("0" + Integer.toString(i));
				bs[i].setFocusable(true);
				bs[i].setEnabled(true);
				bs[i].setOpaque(true);
				bs[i].setBackground(Color.blue);
				bs[i].addMouseListener(this);
				placementPanel.add(bs[i]);
			}

			else
			{
				bs[i] = new JButton();
				bs[i].setName(Integer.toString(i));
				bs[i].setFocusable(true);
				bs[i].setEnabled(true);
				bs[i].setOpaque(true);
				bs[i].setBackground(Color.blue);
				bs[i].addMouseListener(this);
				placementPanel.add(bs[i]);
			}
		}
	}

//--------------------------------------------------------------------------------------------------------------

	public boolean checkEnabledButtonsX (Ship s, JButton[] b, int c)
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

//--------------------------------------------------------------------------------------------------------------

	public boolean checkEnabledButtonsY (Ship s, JButton[] b, int c)
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

//--------------------------------------------------------------------------------------------------------------

	public boolean checkButtonsSameRow (Ship s, JButton[] b, int c)
	{
		int counter = s.size;
		int buttonRow = Integer.parseInt(String.valueOf(c).substring(0,1));
		boolean allButtonsSameRow = false;

		for (int i = 0; i < s.size; i++)
		{
			if (buttonRow == Integer.parseInt(String.valueOf(c + i).substring(0,1)))
			{
				counter--;
			}
		}

		if (counter == 0)
		{
			allButtonsSameRow = true;
		}

		return allButtonsSameRow;
	}

//--------------------------------------------------------------------------------------------------------------

	public void mouseEntered (MouseEvent e)
	{
		if (e.getSource() instanceof JButton)
		{
			JButton enteredButton = (JButton) e.getSource();
			int coordinates = Integer.parseInt(enteredButton.getName());

			if (enteredButton == placementButtons[coordinates])
			{
				for (int ship = 0; ship < ships.length; ship++)
				{
					if (ships[ship].selected && !ships[ship].placed)
					{
						if (ships[ship].orientation &&
							0 <= (coordinates) &&
							(coordinates + ships[ship].size - 1) < 100 &&
							checkEnabledButtonsX(ships[ship], placementButtons, coordinates) &&
							checkButtonsSameRow(ships[ship], placementButtons, coordinates))
						{
							for (int i = 0; i < ships[ship].size; i++)
							{
								placementButtons[coordinates + i].setBackground(Color.black);
							}
						}

						else if (!ships[ship].orientation &&
								0 <= (coordinates) &&
								(coordinates + ((ships[ship].size - 1) * 10)) < 100 &&
								checkEnabledButtonsY(ships[ship], placementButtons, coordinates))
						{
							for (int i = 0; i < ships[ship].size; i++)
							{
								placementButtons[coordinates + (i * 10)].setBackground(Color.black);
							}
						}

						else if (placementButtons[coordinates].isEnabled())
						{
							placementButtons[coordinates].setBackground(Color.red);
						}
					}
				}
			}
		}		
	}

//--------------------------------------------------------------------------------------------------------------

	public void mouseExited (MouseEvent e)
	{
		if (e.getSource() instanceof JButton)
		{
			JButton exitedButton = (JButton) e.getSource();
			int coordinates = Integer.parseInt(exitedButton.getName());

			if (exitedButton == placementButtons[coordinates])
			{
				for (int ship = 0; ship < ships.length; ship++)
				{
					if (ships[ship].selected == true && ships[ship].placed == false)
					{
						if (ships[ship].orientation &&
							0 <= (coordinates) &&
							(coordinates + ships[ship].size - 1) < 100 &&
							checkEnabledButtonsX(ships[ship], placementButtons, coordinates) &&
							checkButtonsSameRow(ships[ship], placementButtons, coordinates))
						{
							for (int i = 0; i < ships[ship].size; i++)
							{
								placementButtons[coordinates + i].setBackground(Color.blue);
							}
						}

						else if (!ships[ship].orientation &&
								0 <= (coordinates) &&
								(coordinates + ((ships[ship].size - 1) * 10)) < 100 &&
								checkEnabledButtonsY(ships[ship], placementButtons, coordinates))
						{
							for (int i = 0; i < ships[ship].size; i++)
							{
								placementButtons[coordinates + (i * 10)].setBackground(Color.blue);
							}
						}

						else if (placementButtons[coordinates].isEnabled())
						{
							placementButtons[coordinates].setBackground(Color.blue);
						}
					}
				}
			}
		}
	}

//--------------------------------------------------------------------------------------------------------------

	public void mousePressed (MouseEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON3)
		{
			JButton clickedButton = (JButton) e.getSource();
			int coordinates = Integer.parseInt(clickedButton.getName());

			for (int i = 0; i < placementButtons.length; i++)
			{
				if (placementButtons[i].isEnabled() &&
					placementButtons[i].getBackground() == Color.black)
				{
					placementButtons[i].setBackground(Color.blue);
				}
			}

			for (int ship = 0; ship < ships.length; ship++)
			{
				if (ships[ship].selected)
				{
					ships[ship].orientation = !ships[ship].orientation;

					if (ships[ship].orientation &&
						0 <= (coordinates) &&
						(coordinates + ships[ship].size - 1) < 100 &&
						checkEnabledButtonsX(ships[ship], placementButtons, coordinates) &&
						checkButtonsSameRow(ships[ship], placementButtons, coordinates))
					{
						for (int i = 0; i < ships[ship].size; i++)
						{
							placementButtons[coordinates + i].setBackground(Color.black);
						}
					}

					else if (!ships[ship].orientation &&
							0 <= (coordinates) &&
							(coordinates + ((ships[ship].size - 1) * 10)) < 100 &&
							checkEnabledButtonsY(ships[ship], placementButtons, coordinates))
					{
						for (int i = 0; i < ships[ship].size; i++)
						{
							placementButtons[coordinates + (i * 10)].setBackground(Color.black);
						}
					}

					else if (placementButtons[coordinates].isEnabled())
					{
						placementButtons[coordinates].setBackground(Color.red);
					}
				}
			}
		}

//--------------------------------------------------------------------------------------------------------------

		else if (e.getSource() instanceof JButton && e.getButton() == MouseEvent.BUTTON1)
		{
			JButton clickedButton = (JButton) e.getSource();
			int coordinates = Integer.parseInt(clickedButton.getName());

			if (clickedButton == placementButtons[coordinates])
			{
				for (int ship = 0; ship < ships.length; ship++) 
				{
					if (ships[ship].selected == true && ships[ship].placed == false)
					{
						if (ships[ship].orientation &&
							0 <= (coordinates) &&
							(coordinates + ships[ship].size - 1) < 100 &&
							checkEnabledButtonsX(ships[ship], placementButtons, coordinates) &&
							checkButtonsSameRow(ships[ship], placementButtons, coordinates))
						{
							for (int i = 0; i < ships[ship].size; i++)
							{
								ships[ship].position[i] = coordinates + i;
								placementButtons[coordinates + i].setBackground(Color.black);
								placementButtons[coordinates + i].setEnabled(false);
							}

							ships[ship].placed = true;
							ships[ship].selected = false;
							
							for (int i = 0; i < selectionButtons.length; i++)
							{
								if (!ships[i].placed)
								{
									selectionButtons[i].setEnabled(true);
								}
							}
						}

						else if (!ships[ship].orientation &&
								0 <= (coordinates) &&
								(coordinates + ((ships[ship].size - 1) * 10)) < 100 &&
								checkEnabledButtonsY(ships[ship], placementButtons, coordinates))
						{
							for (int i = 0; i < ships[ship].size; i++)
							{
								ships[ship].position[i] = coordinates + (i * 10);
								placementButtons[coordinates + (i * 10)].setBackground(Color.black);
								placementButtons[coordinates + (i * 10)].setEnabled(false);

							}

							ships[ship].placed = true;
							ships[ship].selected = false;

							for (int i = 0; i < selectionButtons.length; i++)
							{
								if (!ships[i].placed)
								{
									selectionButtons[i].setEnabled(true);
								}
							}
						}
					}
				}
			}
		}
	}

//--------------------------------------------------------------------------------------------------------------

	public void mouseReleased (MouseEvent e)
	{
		if (e.getSource() instanceof JButton && e.getButton() == MouseEvent.BUTTON1)
		{
			JButton clickedButton = (JButton) e.getSource();
			//String buttonText = clickedButton.getText();
			int coordinates = Integer.parseInt(clickedButton.getName());

			if (clickedButton.isEnabled() &&
				coordinates < selectionButtons.length &&
				clickedButton == selectionButtons[coordinates])
			{
				ships[coordinates].selected = true;

				for (int i = 0; i < selectionButtons.length; i++)
				{
					selectionButtons[i].setEnabled(false);
				}
			}
		}

		if (checkShipPlacement(ships))
		{
			next.setEnabled(true);
		}
	}

//--------------------------------------------------------------------------------------------------------------

	public void mouseClicked (MouseEvent e)
	{}
}
