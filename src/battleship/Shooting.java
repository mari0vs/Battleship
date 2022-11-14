package battleship;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")

public class Shooting extends JPanel implements ActionListener
{
	JPanel leftPanel, playerShipsPanel, centerPanel, shootingPanel, rightPanel;
	JLabel playerLabel;
	JLabel[] playerShipsLabels;
	JButton[] shootingButtons;
	Color[] colors;
	Ship[] playerShips, enemyShips;

	public Shooting (Ship[] ps, Ship[] es, String player, Color[] c)
	{
		colors = c;

		setLayout(new BorderLayout(100, 0));
		setBackground(colors[0]);

		playerLabel = new JLabel(player, SwingConstants.CENTER);
		playerLabel.setFont(new Font("Comic Sans", Font.BOLD, 80));
		playerLabel.setForeground(colors[1]);
		add(playerLabel, BorderLayout.NORTH);

		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(500, 0));
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

		leftPanel.add(Box.createRigidArea(new Dimension(0, 750)));

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
		rightPanel.setPreferredSize(new Dimension(500, 0));
		add(rightPanel, BorderLayout.EAST);

		playerShips = ps;
		enemyShips = es;

		playerShipsLabels = new JLabel[100];
		setPlayerShipsPanel(playerShipsLabels, playerShips);

		shootingButtons = new JButton[100];
		setShootingPanel(shootingButtons);
	}

	public void setPlayerShipsPanel (JLabel[] ls, Ship[] ss)
	{
		for (int i = 0; i < ls.length; i++)
		{
			if (i < 10)
			{
				ls[i] = new JLabel();
				ls[i].setName("0" + Integer.toString(i));
				ls[i].setOpaque(true);
				// ls[i].setBorder(new EmptyBorder(0, 0, 0, 0));
				ls[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colors[1]));
				ls[i].setBackground(colors[5]);
				playerShipsPanel.add(ls[i]);
			}

			else
			{
				ls[i] = new JLabel();
				ls[i].setName(Integer.toString(i));
				ls[i].setOpaque(true);
				// ls[i].setBorder(new EmptyBorder(0, 0, 0, 0));
				ls[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colors[1]));
				ls[i].setBackground(colors[5]);
				playerShipsPanel.add(ls[i]);
			}
		}

		for (Ship s: ss)
		{
			for (int p: s.position)
			{
				ls[p].setBackground(colors[6]);
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
				// bs[i].setBorder(new EmptyBorder(0, 0, 0, 0));
				bs[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colors[1]));
				bs[i].setBackground(colors[5]);
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
				// bs[i].setBorder(new EmptyBorder(0, 0, 0, 0));
				bs[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colors[1]));
				bs[i].setBackground(colors[5]);
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
							shootingButtons[coordinates].setBackground(colors[3]);
						}

						else if (shootingButtons[coordinates].getBackground() == colors[5])
						{
							shootingButtons[coordinates].setBackground(colors[4]);
						}

						shootingButtons[coordinates].setEnabled(false);
					}
				}
			}
		}
	}
}
