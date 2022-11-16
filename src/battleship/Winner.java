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
	JButton mm;
	Color[] colors;

    public Winner (String p, JPanel pA, JPanel pB, Color[] c)
    {
    	colors = c;

    	setLayout(new BorderLayout());
		setBackground(colors[0]);

		winnerLabel = new JLabel(p + " WINS!!!", SwingConstants.CENTER);
		winnerLabel.setFont(new Font("Comic Sans", Font.BOLD, 80));
		winnerLabel.setForeground(colors[1]);
		add(winnerLabel, BorderLayout.NORTH);

		lPanel = new JPanel();
		lPanel.setPreferredSize(new Dimension(1000, 0));
		lPanel.setBorder(new EmptyBorder(100, 20, 100, 20));
		lPanel.setOpaque(false);
		add(lPanel, BorderLayout.WEST);

		BoxLayout lbl = new BoxLayout(lPanel, BoxLayout.Y_AXIS);
		lPanel.setLayout(lbl);
		lPanel.add(pA);

		cPanel = new JPanel();
		Border b = BorderFactory.createMatteBorder(500, 20, 500, 20, colors[0]);
		Border m = BorderFactory.createMatteBorder(10, 10, 10, 10, colors[2]);
		cPanel.setBorder(new CompoundBorder(b, m));
		cPanel.setBackground(colors[5]);
		add(cPanel, BorderLayout.CENTER);

		BoxLayout cbl = new BoxLayout(cPanel, BoxLayout.Y_AXIS);
		cPanel.setLayout(cbl);

		cPanel.add(Box.createVerticalGlue());

		mm = new JButton("Main Menu");
		mm.setName("0");
		mm.setFocusable(false);
		mm.setEnabled(true);
		mm.setOpaque(true);
		mm.setFont(new Font("Comic Sans", Font.BOLD, 40));
		mm.setBorder(new EmptyBorder(20, 100, 20, 100));
		mm.setBackground(colors[0]);
		mm.setForeground(colors[1]);
		mm.setAlignmentX(CENTER_ALIGNMENT);
		cPanel.add(mm);

		cPanel.add(Box.createVerticalGlue());

		rPanel = new JPanel();
		rPanel.setPreferredSize(new Dimension(1000, 0));
		rPanel.setBorder(new EmptyBorder(100, 20, 100, 20));
		rPanel.setOpaque(false);
		add(rPanel, BorderLayout.EAST);

		BoxLayout rbl = new BoxLayout(rPanel, BoxLayout.Y_AXIS);
		rPanel.setLayout(rbl);
		rPanel.add(pB);
    }
}
