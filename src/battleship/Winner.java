package battleship;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")

public class Winner extends JPanel// implements ActionListener
{
	JPanel lPanel, cPanel, rPanel;
	JLabel winnerLabel;
	JButton mainMenu;

    public Winner (SizeSetter sis, Assets ass, String player, JPanel pA, JPanel pB)
    {
    	setLayout(new BorderLayout());
		setBackground(ass.colors[0]);

		winnerLabel = new JLabel(player + " WINS!!!", SwingConstants.CENTER);
		winnerLabel.setFont(new Font("Comic Sans", Font.BOLD, 80));
		winnerLabel.setForeground(ass.colors[1]);
		add(winnerLabel, BorderLayout.NORTH);

		lPanel = new JPanel(new BorderLayout());
		lPanel.setPreferredSize(new Dimension(sis.sizes[8], 0));
		lPanel.setBorder(new EmptyBorder(sis.sizes[9], 20, sis.sizes[9], 20));
		lPanel.setOpaque(false);
		add(lPanel, BorderLayout.WEST);

		lPanel.add(pA, BorderLayout.CENTER);

		cPanel = new JPanel();
		Border b = BorderFactory.createMatteBorder(sis.sizes[10], 20, sis.sizes[10], 20, ass.colors[0]);
		Border m = BorderFactory.createMatteBorder(10, 10, 10, 10, ass.colors[2]);
		cPanel.setBorder(new CompoundBorder(b, m));
		cPanel.setBackground(ass.colors[3]);
		add(cPanel, BorderLayout.CENTER);

		BoxLayout cbl = new BoxLayout(cPanel, BoxLayout.Y_AXIS);
		cPanel.setLayout(cbl);

		cPanel.add(Box.createVerticalGlue());

		mainMenu = new JButton("Main Menu");
		mainMenu.setName("0");
		mainMenu.setFocusable(false);
		mainMenu.setEnabled(true);
		mainMenu.setOpaque(true);
		mainMenu.setFont(new Font("Comic Sans", Font.BOLD, 40));
		mainMenu.setBorder(new EmptyBorder(20, 20, 20, 20));
		mainMenu.setBackground(ass.colors[0]);
		mainMenu.setForeground(ass.colors[1]);
		mainMenu.setAlignmentX(CENTER_ALIGNMENT);
		cPanel.add(mainMenu);

		cPanel.add(Box.createVerticalGlue());

		rPanel = new JPanel(new BorderLayout());
		rPanel.setPreferredSize(new Dimension(sis.sizes[8], 0));
		rPanel.setBorder(new EmptyBorder(sis.sizes[9], 20, sis.sizes[9], 20));
		rPanel.setOpaque(false);
		add(rPanel, BorderLayout.EAST);

		rPanel.add(pB, BorderLayout.CENTER);
    }
}
