package battleship;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")

public class Shooting extends JPanel implements ActionListener
{
	JPanel leftPanel, rightPanel, playerShipsPanel, shootingPanel;
	JLabel playerLabel;
	JLabel[] playerShipsLabels;
	JButton[] shootingButtons;
	Color[] colors;
	Ship[] playerShips, enemyShips;

	public Shooting (Ship[] shipsA, Ship[] shipsB, String player, Color[] c)
	{
		colors = c;

		// setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setLayout(new BorderLayout(200, 0));
		setBackground(Color.black);

		playerLabel = new JLabel(player, SwingConstants.CENTER);
		playerLabel.setFont(new Font("Comic Sans", Font.BOLD, 80));
		playerLabel.setForeground(Color.white);
		add(playerLabel, BorderLayout.NORTH);

		leftPanel = new JPanel();
		leftPanel.setOpaque(false);
		leftPanel.setPreferredSize(new Dimension(500, 0));
		leftPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		add(leftPanel, BorderLayout.WEST);

		BoxLayout boxlayout = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
		leftPanel.setLayout(boxlayout);

		playerShipsPanel = new JPanel(new GridLayout(10, 10, 1, 1));
		playerShipsPanel.setBackground(Color.black);
		playerShipsPanel.setAlignmentX(CENTER_ALIGNMENT);
		leftPanel.add(playerShipsPanel);

		leftPanel.add(Box.createRigidArea(new Dimension(0, 750)));

		shootingPanel = new JPanel(new GridLayout(10, 10, 1, 1));
		shootingPanel.setBackground(Color.black);
		add(shootingPanel, BorderLayout.CENTER);

		rightPanel = new JPanel();
		rightPanel.setOpaque(false);
		rightPanel.setPreferredSize(new Dimension(400, 0));
		add(rightPanel, BorderLayout.EAST);

		playerShips = shipsA;
		enemyShips = shipsB;

		playerShipsLabels = new JLabel[100];
		setPlayerShipsPanel(playerShipsLabels, playerShips);

		shootingButtons = new JButton[100];
		setShootingPanel(shootingButtons);
	}

	public void setPlayerShipsPanel (JLabel[] bs, Ship[] s)
	{
		for (int i = 0; i < bs.length; i++)
		{
			if (i < 10)
			{
				bs[i] = new JLabel();
				bs[i].setName("0" + Integer.toString(i));
				bs[i].setOpaque(true);
				bs[i].setBackground(Color.blue);
				playerShipsPanel.add(bs[i]);
			}

			else
			{
				bs[i] = new JLabel();
				bs[i].setName(Integer.toString(i));
				bs[i].setOpaque(true);
				bs[i].setBackground(Color.blue);
				playerShipsPanel.add(bs[i]);
			}
		}

		for (int i = 0; i < s.length; i++)
		{
			for (int j = 0; j < s[i].size; j++)
			{
				bs[s[i].position[j]].setBackground(Color.black);
			}
		}
	}

	public void setShootingPanel (JButton[] bs)
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
				bs[i].addActionListener(this);
				shootingPanel.add(bs[i]);
			}

			else
			{
				bs[i] = new JButton();
				bs[i].setName(Integer.toString(i));
				bs[i].setFocusable(true);
				bs[i].setEnabled(true);
				bs[i].setOpaque(true);
				bs[i].setBackground(Color.blue);
				bs[i].addActionListener(this);
				shootingPanel.add(bs[i]);
			}
		}
	}

	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource() instanceof JButton)
		{
			JButton clickedButton = (JButton) e.getSource();
			int coordinates = Integer.parseInt(clickedButton.getName());

			if (clickedButton == shootingButtons[coordinates])
			{
				for (Ship enemyShip: enemyShips)
				{
					for (int position: enemyShip.position)
					{
						if (coordinates == position)
						{
							shootingButtons[coordinates].setBackground(Color.red);
						}

						else if (shootingButtons[coordinates].getBackground() == Color.blue)
						{
							shootingButtons[coordinates].setBackground(Color.green);
						}

						shootingButtons[coordinates].setEnabled(false);
					}
				}
			}
		}
	}
}
