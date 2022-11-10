package battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")

public class Shooting extends JPanel implements ActionListener
{
	JPanel playerShipsPanel, shootingPanel;
	JLabel playerLabel;
	JLabel[] playerShipsLabels;
	JButton[] shootingButtons;
	Ship[] playerShips, enemyShips;

	public Shooting (Ship[] shipsA, Ship[] shipsB, String player)
	{
		setLayout(new BorderLayout(200, 0));
		setBackground(Color.gray);

		playerShipsPanel = new JPanel(new GridLayout(10, 10, 1, 1));
		playerShipsPanel.setPreferredSize(new Dimension(1000, 0));
		playerShipsPanel.setBackground(Color.gray);
		add(playerShipsPanel, BorderLayout.WEST);

		shootingPanel = new JPanel(new GridLayout(10, 10, 0, 0));
		shootingPanel.setBackground(Color.gray);
		add(shootingPanel, BorderLayout.CENTER);

		playerLabel = new JLabel(player, SwingConstants.CENTER);
		playerLabel.setFont(new Font("Comic Sans", Font.BOLD, 60));
		playerLabel.setForeground(Color.black);
		add(playerLabel, BorderLayout.NORTH);

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
		}
	}
}
