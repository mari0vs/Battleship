package battleship;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")

public class ShipPlacement extends JPanel
{
	JPanel leftPanel, selectionPanel, centerPanel, placementPanel, rightPanel, donePanel;
	JLabel playerLabel;
	JButton done;
	ImageIcon[] icons;
	Color[] colors;
	JButton[] placementButtons, selectionButtons;
	Ship[] ships;

	public ShipPlacement (int[] ss, Color[] cs, ImageIcon[] is, Ship[] s, String p)
	{
		Font buttonFont = new Font("Comic Sans", Font.BOLD, 40);
		icons = is;
		colors = cs;

		setLayout(new BorderLayout(ss[4], 0));
		setBackground(colors[0]);

		//------------------------------ Panels ------------------------------

		playerLabel = new JLabel(p, SwingConstants.CENTER);
		playerLabel.setFont(new Font("Comic Sans", Font.BOLD, 80));
		playerLabel.setForeground(colors[1]);
		add(playerLabel, BorderLayout.NORTH);

		JPanel leftPanel = new JPanel(new BorderLayout());
		leftPanel.setPreferredSize(new Dimension(ss[5], 0));
		leftPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		leftPanel.setOpaque(false);
		add(leftPanel, BorderLayout.WEST);

		selectionPanel = new JPanel();
		Border spb = BorderFactory.createMatteBorder(10, 10, 10, 10, colors[2]);
		Border spm = new EmptyBorder(20, 20, 20, 20);
		selectionPanel.setBorder(new CompoundBorder(spb, spm));
		selectionPanel.setBackground(colors[3]);
		leftPanel.add(selectionPanel, BorderLayout.CENTER);

		BoxLayout spbl = new BoxLayout(selectionPanel, BoxLayout.Y_AXIS);
		selectionPanel.setLayout(spbl);

		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		centerPanel.setOpaque(false);
		add(centerPanel, BorderLayout.CENTER);

		placementPanel = new JPanel(new GridLayout(10, 10, 0, 0));
		placementPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		placementPanel.setBackground(colors[2]);
		centerPanel.add(placementPanel, BorderLayout.CENTER);

		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.setPreferredSize(new Dimension(ss[5], 0));
		rightPanel.setBorder(new EmptyBorder(ss[6], 20, 20, 20));
		rightPanel.setOpaque(false);
		add(rightPanel, BorderLayout.EAST);

		JPanel donePanel = new JPanel();
		Border dpb = BorderFactory.createMatteBorder(10, 10, 10, 10, colors[2]);
		Border dpm = new EmptyBorder(20, 20, 20, 20);
		donePanel.setBorder(new CompoundBorder(dpb, dpm));
		donePanel.setBackground(colors[3]);
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
			bs[i].setFont(bf);
			bs[i].setBorder(new EmptyBorder(20, 20, 20, 20));
			bs[i].setBackground(colors[0]);
			bs[i].setForeground(colors[1]);
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

		for (int i = 0; i < ships.length; i++)
		{
			if (ships[i].placed)
			{
				bs[i].setEnabled(false);
			}
		}
	}

//--------------------------------------------------------------------------------------------------------------

	public void setPlacementPanel (JButton[] bs)
	{
		for (int i = 0; i < bs.length; i++)
		{
			bs[i] = new JButton();
			bs[i].setName(Integer.toString(i));
			bs[i].setFocusable(false);
			bs[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, colors[1]));
			bs[i].setBackground(colors[4]);
			placementPanel.add(bs[i]);
		}

		for (Ship ship: ships)
		{
			if (ship.placed)
			{
				for(int i: ship.position)
				{
					bs[i].setBackground(colors[8]);
					bs[i].setEnabled(false);
				}
			}
		}
	}
}
