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
	Color[] colors;
	Ship[] playerShips, enemyShips;
	boolean[] playerShots, enemyShots;

	public Shooting (int[] ss, Color[] c, Ship[] pss, Ship[] ess, boolean[] pshs, boolean[] eshs, String player)
	{
		colors = c;

		setLayout(new BorderLayout(ss[4], 0));
		setBackground(colors[0]);

		playerLabel = new JLabel(player, SwingConstants.CENTER);
		playerLabel.setFont(new Font("Comic Sans", Font.BOLD, 80));
		playerLabel.setForeground(colors[1]);
		add(playerLabel, BorderLayout.NORTH);

		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(ss[5], 0));
		leftPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		leftPanel.setOpaque(false);
		add(leftPanel, BorderLayout.WEST);

		BoxLayout boxlayout = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
		leftPanel.setLayout(boxlayout);

		playerShipsPanel = new JPanel(new GridLayout(10, 10, 0, 0));
		playerShipsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		playerShipsPanel.setBackground(colors[2]);
		// playerShipsPanel.setAlignmentX(CENTER_ALIGNMENT);
		leftPanel.add(playerShipsPanel);

		leftPanel.add(Box.createRigidArea(new Dimension(0, ss[7])));

		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		centerPanel.setOpaque(false);
		add(centerPanel, BorderLayout.CENTER);

		shootingPanel = new JPanel(new GridLayout(10, 10, 0, 0));
		shootingPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		shootingPanel.setBackground(colors[2]);
		centerPanel.add(shootingPanel, BorderLayout.CENTER);

		rightPanel = new JPanel();
		rightPanel.setOpaque(false);
		rightPanel.setPreferredSize(new Dimension(ss[5], 0));
		add(rightPanel, BorderLayout.EAST);

		playerShips = pss;
		enemyShips = ess;
		playerShots = pshs;
		enemyShots = eshs;

		playerShipsLabels = new JLabel[100];
		setPlayerShipsPanel(playerShipsLabels);

		shootingButtons = new JButton[100];
		setShootingPanel(shootingButtons);
	}

	public void setPlayerShipsPanel (JLabel[] ls)
	{
		for (int i = 0; i < ls.length; i++)
		{
			ls[i] = new JLabel();
			ls[i].setName(Integer.toString(i));
			ls[i].setOpaque(true);
			// ls[i].setBorder(new EmptyBorder(0, 0, 0, 0));
			ls[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colors[1]));
			ls[i].setBackground(colors[4]);

			if (playerShots[i])
			{
				ls[i].setBackground(colors[7]);
			}

			else
			{
				ls[i].setBackground(colors[4]);
			}

			playerShipsPanel.add(ls[i]);
		}

		for (Ship ship: playerShips)
		{
			for (int i = 0; i < ship.size; i++)
			{
				if (ship.hits[i])
				{
					ls[ship.position[i]].setBackground(colors[6]);
				}

				else
				{
					ls[ship.position[i]].setBackground(colors[8]);
				}
			}
		}
	}

	public void setShootingPanel (JButton[] bs)
	{
		for (int i = 0; i < bs.length; i++)
		{
			bs[i] = new JButton();
			bs[i].setName(Integer.toString(i));
			bs[i].setFocusable(false);
			bs[i].setOpaque(true);
			// bs[i].setBorder(new EmptyBorder(0, 0, 0, 0));
			bs[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colors[1]));

			if (enemyShots[i])
			{
				bs[i].setEnabled(false);
				bs[i].setBackground(colors[7]);
			}

			else
			{
				bs[i].setEnabled(true);
				bs[i].setBackground(colors[4]);
			}

			shootingPanel.add(bs[i]);
		}

		for (Ship ship: enemyShips)
		{
			for (int i = 0; i < ship.size; i++)
			{
				if (ship.hits[i])
				{
					bs[ship.position[i]].setEnabled(false);
					bs[ship.position[i]].setBackground(colors[6]);
				}
			}
		}
	}
}
