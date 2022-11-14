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
	JButton ng, ex;
	Color[] colors;

    public Winner (String p, JPanel pP, JPanel eP, Color[] c)
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
		lPanel.add(pP);

		cPanel = new JPanel();
		Border b = BorderFactory.createMatteBorder(20, 20, 20, 20, colors[0]);
		Border m = BorderFactory.createMatteBorder(5, 5, 5, 5, colors[2]);
		cPanel.setBorder(new CompoundBorder(b, m));
		cPanel.setOpaque(false);
		add(cPanel, BorderLayout.WEST);

		BoxLayout cbl = new BoxLayout(cPanel, BoxLayout.Y_AXIS);
		cPanel.setLayout(cbl);

		cPanel.add(Box.createVerticalGlue());

		ng = new JButton("New Game");
		ng.setFocusable(false);
		ng.setEnabled(true);
		ng.setOpaque(true);
		ng.setFont(new Font("Comic Sans", Font.BOLD, 40));
		ng.setBackground(colors[0]);
		ng.setForeground(colors[1]);
		// ng.addActionListener(this);
		ng.setAlignmentX(CENTER_ALIGNMENT);
		cPanel.add(ng);

		cPanel.add(Box.createVerticalGlue());

		ex = new JButton("Exit");
		ex.setFocusable(false);
		ex.setEnabled(true);
		ex.setOpaque(true);
		ex.setFont(new Font("Comic Sans", Font.BOLD, 40));
		ex.setBackground(colors[0]);
		ex.setForeground(colors[1]);
		// ex.addActionListener(this);
		ex.setAlignmentX(CENTER_ALIGNMENT);
		cPanel.add(ex);

		cPanel.add(Box.createVerticalGlue());

		rPanel = new JPanel();
		rPanel.setPreferredSize(new Dimension(1000, 0));
		rPanel.setBorder(new EmptyBorder(100, 20, 100, 20));
		rPanel.setOpaque(false);
		add(rPanel, BorderLayout.EAST);

		BoxLayout rbl = new BoxLayout(rPanel, BoxLayout.Y_AXIS);
		rPanel.setLayout(rbl);
		rPanel.add(eP);
    }
}
