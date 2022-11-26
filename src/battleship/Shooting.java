package battleship;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")

public class Shooting extends JPanel
{
	JPanel leftPanel, playerShipsPanel, centerPanel, shootingPanel, rightPanel;
	JLabel playerLabel;
	JLabel[] playerShipsLabels;
	JButton[] shootingButtons;

	public Shooting (SizeSetter sis, Assets ass, String player)
	{
		setLayout(new BorderLayout(sis.sizes[4], 0));
		setBackground(ass.colors[0]);

		playerLabel = new JLabel(player, SwingConstants.CENTER);
		playerLabel.setFont(new Font("Comic Sans", Font.BOLD, 80));
		playerLabel.setForeground(ass.colors[1]);
		add(playerLabel, BorderLayout.NORTH);

		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(sis.sizes[5], 0));
		leftPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		leftPanel.setOpaque(false);
		add(leftPanel, BorderLayout.WEST);

		BoxLayout boxlayout = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
		leftPanel.setLayout(boxlayout);

		playerShipsPanel = new JPanel(new GridLayout(10, 10, 0, 0));
		playerShipsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		playerShipsPanel.setBackground(ass.colors[2]);
		// playerShipsPanel.setAlignmentX(CENTER_ALIGNMENT);
		leftPanel.add(playerShipsPanel);

		leftPanel.add(Box.createRigidArea(new Dimension(0, sis.sizes[7])));

		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		centerPanel.setOpaque(false);
		add(centerPanel, BorderLayout.CENTER);

		shootingPanel = new JPanel(new GridLayout(10, 10, 0, 0));
		shootingPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		shootingPanel.setBackground(ass.colors[2]);
		centerPanel.add(shootingPanel, BorderLayout.CENTER);

		rightPanel = new JPanel();
		rightPanel.setOpaque(false);
		rightPanel.setPreferredSize(new Dimension(sis.sizes[5], 0));
		add(rightPanel, BorderLayout.EAST);

		playerShipsLabels = new JLabel[100];
		shootingButtons = new JButton[100];
	}

	public void setPlayerShipsPanel (Assets ass, Ship[] ships, boolean[] shots)
	{
		for (int i = 0; i < playerShipsLabels.length; i++)
		{
			playerShipsLabels[i] = new JLabel();
			playerShipsLabels[i].setName(Integer.toString(i));
			playerShipsLabels[i].setOpaque(true);
			// playerShipsLabels[i].setBorder(new EmptyBorder(0, 0, 0, 0));
			playerShipsLabels[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ass.colors[1]));
			playerShipsLabels[i].setBackground(ass.colors[4]);

			if (shots[i])
			{
				playerShipsLabels[i].setBackground(ass.colors[7]);
			}

			else
			{
				playerShipsLabels[i].setBackground(ass.colors[4]);
			}

			playerShipsPanel.add(playerShipsLabels[i]);
		}

		for (Ship ship: ships)
		{
			for (int i = 0; i < ship.size; i++)
			{
				if (ship.hits[i])
				{
					playerShipsLabels[ship.position[i]].setBackground(ass.colors[6]);
				}

				else
				{
					playerShipsLabels[ship.position[i]].setBackground(ass.colors[8]);
				}
			}
		}
	}

	public void setShootingPanel (Assets ass, Ship[] ships, boolean[] shots)
	{
		for (int i = 0; i < shootingButtons.length; i++)
		{
			shootingButtons[i] = new JButton();
			shootingButtons[i].setName(Integer.toString(i));
			shootingButtons[i].setFocusable(false);
			shootingButtons[i].setOpaque(true);
			// shootingButtons[i].setBorder(new EmptyBorder(0, 0, 0, 0));
			shootingButtons[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ass.colors[1]));

			if (shots[i])
			{
				shootingButtons[i].setEnabled(false);
				shootingButtons[i].setBackground(ass.colors[7]);
			}

			else
			{
				shootingButtons[i].setEnabled(true);
				shootingButtons[i].setBackground(ass.colors[4]);
			}

			shootingPanel.add(shootingButtons[i]);
		}

		for (Ship ship: ships)
		{
			for (int i = 0; i < ship.size; i++)
			{
				if (ship.hits[i])
				{
					shootingButtons[ship.position[i]].setEnabled(false);
					shootingButtons[ship.position[i]].setBackground(ass.colors[6]);
				}
			}
		}
	}
}
