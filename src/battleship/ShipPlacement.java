package battleship;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")

public class ShipPlacement extends JPanel implements MouseListener
{
	JPanel leftPanel, selectionPanel, centerPanel, placementPanel, rightPanel, donePanel;
	JLabel playerLabel;
	JButton done;
	Color[] colors;
	JButton[] placementButtons, selectionButtons;
	Ship[] ships;

	public ShipPlacement (Color[] c, int[] ss, Ship[] s, String player)
	{
		Font buttonFont = new Font("Comic Sans", Font.BOLD, 40);
		colors = c;

		setLayout(new BorderLayout(ss[7], 0));
		setBackground(colors[0]);
		addMouseListener(this);

		//------------------------------ Panels ------------------------------

		playerLabel = new JLabel(player, SwingConstants.CENTER);
		playerLabel.setFont(new Font("Comic Sans", Font.BOLD, 80));
		playerLabel.setForeground(colors[1]);
		add(playerLabel, BorderLayout.NORTH);

		JPanel leftPanel = new JPanel(new BorderLayout());
		leftPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		leftPanel.setOpaque(false);
		add(leftPanel, BorderLayout.WEST);

		selectionPanel = new JPanel();
		selectionPanel.setPreferredSize(new Dimension(ss[8], 0));
		Border spb = BorderFactory.createMatteBorder(10, 10, 10, 10, colors[2]);
		Border spm = new EmptyBorder(20, 20, 20, 20);
		selectionPanel.setBorder(new CompoundBorder(spb, spm));
		selectionPanel.setBackground(colors[5]);
		leftPanel.add(selectionPanel, BorderLayout.CENTER);

		BoxLayout spbl = new BoxLayout(selectionPanel, BoxLayout.Y_AXIS);
		selectionPanel.setLayout(spbl);

		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		centerPanel.setOpaque(false);
		add(centerPanel, BorderLayout.CENTER);

		placementPanel = new JPanel(new GridLayout(10, 10, 0, 0));
		placementPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		placementPanel.setBackground(colors[0]);
		centerPanel.add(placementPanel, BorderLayout.CENTER);

		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.setBorder(new EmptyBorder(ss[9], 20, ss[9], 20));
		rightPanel.setOpaque(false);
		add(rightPanel, BorderLayout.EAST);

		JPanel donePanel = new JPanel();
		donePanel.setPreferredSize(new Dimension(ss[8], 0));
		Border dpb = BorderFactory.createMatteBorder(10, 10, 10, 10, colors[2]);
		Border dpm = new EmptyBorder(20, 20, 20, 20);
		donePanel.setBorder(new CompoundBorder(dpb, dpm));
		donePanel.setBackground(colors[5]);
		rightPanel.add(donePanel, BorderLayout.CENTER);

		BoxLayout rpbl = new BoxLayout(donePanel, BoxLayout.Y_AXIS);
		donePanel.setLayout(rpbl);

		//------------------------------ Content ------------------------------

		ships = s;

		selectionButtons = new JButton[ships.length];
		setSelectionPanel(selectionButtons, buttonFont);

		donePanel.add(Box.createVerticalGlue());

		done = new JButton("Done");
		done.setFocusable(false);
		done.setEnabled(true);//--------------------------------------------------------------------------false
		done.setOpaque(true);
		done.setFont(buttonFont);
		done.setBorder(new EmptyBorder(20, 60, 20, 60));
		done.setBackground(colors[0]);
		done.setForeground(colors[1]);
		done.setAlignmentX(CENTER_ALIGNMENT);
		donePanel.add(done);

		donePanel.add(Box.createVerticalGlue());

		placementButtons = new JButton[100];
		setPlacementPanel(placementButtons);
	}

//--------------------------------------------------------------------------------------------------------------

	public void setSelectionPanel (JButton[] bs, Font bf)
	{
		for (int i = 0; i < bs.length; i++)
		{
			bs[i] = new JButton();
			bs[i].setName(Integer.toString(i));
			bs[i].setFocusable(false);
			bs[i].setEnabled(true);
			bs[i].setOpaque(true);
			bs[i].setFont(bf);
			bs[i].setBorder(new EmptyBorder(20, 20, 20, 20));
			bs[i].setBackground(colors[0]);
			bs[i].setForeground(colors[1]);
			bs[i].addMouseListener(this);
			// bs[i].setAlignmentX(CENTER_ALIGNMENT);
			selectionPanel.add(bs[i]);

			if (i == 0 || i == 1)
			{
				bs[i].setText("Small Ship");

				selectionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
			}

			else if (i == 2 || i == 3)
			{
				bs[i].setText("Medium Ship");

				selectionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
			}

			else
			{
				bs[i].setText("Big Ship");
			}
		}

		selectionPanel.add(Box.createVerticalGlue());
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
			bs[i] = new JButton();
			bs[i].setName(Integer.toString(i));
			bs[i].setFocusable(true);
			bs[i].setEnabled(true);
			bs[i].setOpaque(true);
			// bs[i].setBorder(new EmptyBorder(0, 0, 0, 0));
			bs[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colors[1]));
			bs[i].setBackground(colors[5]);
			bs[i].addMouseListener(this);
			placementPanel.add(bs[i]);
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
				for (Ship ship: ships)
				{
					if (ship.selected && !ship.placed)
					{
						if (ship.orientation &&
							0 <= (coordinates) &&
							(coordinates + ship.size - 1) < 100 &&
							checkEnabledButtonsX(ship, placementButtons, coordinates) &&
							checkButtonsSameRow(ship, placementButtons, coordinates))
						{
							for (int i = 0; i < ship.size; i++)
							{
								placementButtons[coordinates + i].setBackground(colors[4]);
							}
						}

						else if (!ship.orientation &&
								0 <= (coordinates) &&
								(coordinates + ((ship.size - 1) * 10)) < 100 &&
								checkEnabledButtonsY(ship, placementButtons, coordinates))
						{
							for (int i = 0; i < ship.size; i++)
							{
								placementButtons[coordinates + (i * 10)].setBackground(colors[4]);
							}
						}

						else if (placementButtons[coordinates].isEnabled())
						{
							placementButtons[coordinates].setBackground(colors[3]);
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
				for (Ship ship: ships)
				{
					if (ship.selected == true && ship.placed == false)
					{
						if (ship.orientation &&
							0 <= (coordinates) &&
							(coordinates + ship.size - 1) < 100 &&
							checkEnabledButtonsX(ship, placementButtons, coordinates) &&
							checkButtonsSameRow(ship, placementButtons, coordinates))
						{
							for (int i = 0; i < ship.size; i++)
							{
								placementButtons[coordinates + i].setBackground(colors[5]);
							}
						}

						else if (!ship.orientation &&
								0 <= (coordinates) &&
								(coordinates + ((ship.size - 1) * 10)) < 100 &&
								checkEnabledButtonsY(ship, placementButtons, coordinates))
						{
							for (int i = 0; i < ship.size; i++)
							{
								placementButtons[coordinates + (i * 10)].setBackground(colors[5]);
							}
						}

						else if (placementButtons[coordinates].isEnabled())
						{
							placementButtons[coordinates].setBackground(colors[5]);
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
			if (e.getSource() instanceof JButton)
			{
				JButton clickedButton = (JButton) e.getSource();
				int coordinates = Integer.parseInt(clickedButton.getName());

				for (JButton pb: placementButtons)
				{
					if (pb.isEnabled())
					{
						pb.setBackground(colors[5]);
					}
				}

				for (JButton pb: placementButtons)
				{
					if (clickedButton == pb)
					{
						for (Ship ship: ships)
						{
							if (ship.selected)
							{
								ship.orientation = !ship.orientation;

								if (ship.orientation &&
									0 <= (coordinates) &&
									(coordinates + ship.size - 1) < 100 &&
									checkEnabledButtonsX(ship, placementButtons, coordinates) &&
									checkButtonsSameRow(ship, placementButtons, coordinates))
								{
									for (int i = 0; i < ship.size; i++)
									{
										placementButtons[coordinates + i].setBackground(colors[4]);
									}
								}

								else if (!ship.orientation &&
										0 <= (coordinates) &&
										(coordinates + ((ship.size - 1) * 10)) < 100 &&
										checkEnabledButtonsY(ship, placementButtons, coordinates))
								{
									for (int i = 0; i < ship.size; i++)
									{
										placementButtons[coordinates + (i * 10)].setBackground(colors[4]);
									}
								}

								else if (pb.isEnabled())
								{
									pb.setBackground(colors[3]);
								}
							}
						}
					}
				}
			}

			else
				for (Ship ship: ships)
				{
					if (ship.selected)
					{
						ship.orientation = !ship.orientation;
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
				for (Ship ship: ships) 
				{
					if (ship.selected == true && ship.placed == false)
					{
						if (ship.orientation &&
							0 <= (coordinates) &&
							(coordinates + ship.size - 1) < 100 &&
							checkEnabledButtonsX(ship, placementButtons, coordinates) &&
							checkButtonsSameRow(ship, placementButtons, coordinates))
						{
							for (int i = 0; i < ship.size; i++)
							{
								ship.position[i] = coordinates + i;
								placementButtons[coordinates + i].setBackground(colors[6]);
								placementButtons[coordinates + i].setEnabled(false);
							}

							ship.placed = true;
							ship.selected = false;
							
							for (int i = 0; i < selectionButtons.length; i++)
							{
								if (!ships[i].placed)
								{
									selectionButtons[i].setEnabled(true);
								}
							}
						}

						else if (!ship.orientation &&
								0 <= (coordinates) &&
								(coordinates + ((ship.size - 1) * 10)) < 100 &&
								checkEnabledButtonsY(ship, placementButtons, coordinates))
						{
							for (int i = 0; i < ship.size; i++)
							{
								ship.position[i] = coordinates + (i * 10);
								placementButtons[coordinates + (i * 10)].setBackground(colors[6]);
								placementButtons[coordinates + (i * 10)].setEnabled(false);

							}

							ship.placed = true;
							ship.selected = false;

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
