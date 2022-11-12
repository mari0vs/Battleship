package battleship;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")

public class ShipPlacement extends JPanel implements MouseListener
{
	JPanel selectionPanel, placementPanel, blankPanel;
	JLabel playerLabel;
	JButton done;
	Color[] colors;
	JButton[] placementButtons, selectionButtons;
	Ship[] ships;

	public ShipPlacement (Ship[] mainShips, String player, Color[] c)
	{
		Font titleFont = new Font("Comic Sans", Font.BOLD, 80);
		Font buttonFont = new Font("Comic Sans", Font.BOLD, 50);
		colors = c;
		ships = mainShips;

		setLayout(new BorderLayout(200, 0));
		setBackground(Color.black);
		addMouseListener(this);

		//------------------------------ Panels ------------------------------

		selectionPanel = new JPanel();
		selectionPanel.setPreferredSize(new Dimension(400, 0));
		selectionPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		selectionPanel.setBackground(colors[6]);
		add(selectionPanel, BorderLayout.WEST);

		BoxLayout boxlayout = new BoxLayout(selectionPanel, BoxLayout.Y_AXIS);
		selectionPanel.setLayout(boxlayout);

		placementPanel = new JPanel(new GridLayout(10, 10, 0, 0));
		placementPanel.setBackground(colors[6]);
		add(placementPanel, BorderLayout.CENTER);

		JPanel blankPanel = new JPanel(null);
		blankPanel.setPreferredSize(new Dimension(400, 0));
		blankPanel.setOpaque(false);
		add(blankPanel, BorderLayout.EAST);

		//------------------------------ Content ------------------------------

		playerLabel = new JLabel(player, SwingConstants.CENTER);
		playerLabel.setFont(titleFont);
		playerLabel.setForeground(c[0]);
		add(playerLabel, BorderLayout.NORTH);

		selectionButtons = new JButton[ships.length];
		setSelectionPanel(ships, selectionButtons, buttonFont);

		selectionPanel.add(Box.createVerticalGlue());

		done = new JButton("Done");
		done.setFocusable(false);
		done.setEnabled(true);//------------------------------------------------------------------------------------------false
		done.setOpaque(true);
		done.setFont(buttonFont);
		done.setBorder(new EmptyBorder(30, 80, 30, 80));
		done.setBackground(Color.black);
		done.setForeground(Color.white);
		done.setAlignmentX(CENTER_ALIGNMENT);
		selectionPanel.add(done);

		placementButtons = new JButton[100];
		setPlacementPanel(placementButtons);
	}

//--------------------------------------------------------------------------------------------------------------

	public void setSelectionPanel (Ship[] s, JButton[] bs, Font bf)
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
				bs[i].setFont(bf);
				bs[i].setBorder(new EmptyBorder(30, 20, 30, 20));
				bs[i].setBackground(Color.black);
				bs[i].setForeground(Color.white);
				bs[i].addMouseListener(this);
				bs[i].setAlignmentX(CENTER_ALIGNMENT);
				selectionPanel.add(bs[i]);

				selectionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
			}

			else if (i == 2 || i == 3)
			{
				s[i] = new Ship(3);
				bs[i] = new JButton("Medium Ship");
				bs[i].setName(Integer.toString(i));
				bs[i].setFocusable(false);
				bs[i].setEnabled(true);
				bs[i].setOpaque(true);
				bs[i].setFont(bf);
				bs[i].setBorder(new EmptyBorder(30, 10, 30, 10));
				bs[i].setBackground(Color.black);
				bs[i].setForeground(Color.white);
				bs[i].addMouseListener(this);
				bs[i].setAlignmentX(CENTER_ALIGNMENT);
				selectionPanel.add(bs[i]);

				selectionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
			}

			else if (i == 4)
			{
				s[i] = new Ship(4);
				bs[i] = new JButton("Big Ship");
				bs[i].setName(Integer.toString(i));
				bs[i].setFocusable(false);
				bs[i].setEnabled(true);
				bs[i].setOpaque(true);
				bs[i].setFont(bf);
				bs[i].setBorder(new EmptyBorder(30, 80, 30, 80));
				bs[i].setBackground(Color.black);
				bs[i].setForeground(Color.white);
				bs[i].addMouseListener(this);
				bs[i].setAlignmentX(CENTER_ALIGNMENT);
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
				bs[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
				bs[i].setBackground(colors[1]);
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
				bs[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
				bs[i].setBackground(colors[1]);
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
								placementButtons[coordinates + i].setBackground(colors[4]);
							}
						}

						else if (!ships[ship].orientation &&
								0 <= (coordinates) &&
								(coordinates + ((ships[ship].size - 1) * 10)) < 100 &&
								checkEnabledButtonsY(ships[ship], placementButtons, coordinates))
						{
							for (int i = 0; i < ships[ship].size; i++)
							{
								placementButtons[coordinates + (i * 10)].setBackground(colors[4]);
							}
						}

						else if (placementButtons[coordinates].isEnabled())
						{
							placementButtons[coordinates].setBackground(colors[0]);
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
								placementButtons[coordinates + i].setBackground(colors[1]);
							}
						}

						else if (!ships[ship].orientation &&
								0 <= (coordinates) &&
								(coordinates + ((ships[ship].size - 1) * 10)) < 100 &&
								checkEnabledButtonsY(ships[ship], placementButtons, coordinates))
						{
							for (int i = 0; i < ships[ship].size; i++)
							{
								placementButtons[coordinates + (i * 10)].setBackground(colors[1]);
							}
						}

						else if (placementButtons[coordinates].isEnabled())
						{
							placementButtons[coordinates].setBackground(colors[1]);
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

			for (int ship = 0; ship < ships.length; ship++)
			{
				if (ships[ship].selected)
				{
					ships[ship].orientation = !ships[ship].orientation;
				}
			}
		}

		if (e.getSource() instanceof JButton && e.getButton() == MouseEvent.BUTTON3)
		{
			JButton clickedButton = (JButton) e.getSource();
			int coordinates = Integer.parseInt(clickedButton.getName());

			for (int i = 0; i < placementButtons.length; i++)
			{
				if (placementButtons[i].isEnabled() &&
					placementButtons[i].getBackground() == colors[4])
				{
					placementButtons[i].setBackground(colors[1]);
				}
			}

			for (int ship = 0; ship < ships.length; ship++)
			{
				ships[ship].orientation = !ships[ship].orientation;

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
							placementButtons[coordinates + i].setBackground(colors[4]);
						}
					}

					else if (!ships[ship].orientation &&
							0 <= (coordinates) &&
							(coordinates + ((ships[ship].size - 1) * 10)) < 100 &&
							checkEnabledButtonsY(ships[ship], placementButtons, coordinates))
					{
						for (int i = 0; i < ships[ship].size; i++)
						{
							placementButtons[coordinates + (i * 10)].setBackground(colors[4]);
						}
					}

					else if (placementButtons[coordinates].isEnabled())
					{
						placementButtons[coordinates].setBackground(colors[0]);
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
								placementButtons[coordinates + i].setBackground(colors[4]);
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
								placementButtons[coordinates + (i * 10)].setBackground(colors[4]);
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
			done.setEnabled(true);
		}
	}

//--------------------------------------------------------------------------------------------------------------

	public void mouseClicked (MouseEvent e)
	{}
}
