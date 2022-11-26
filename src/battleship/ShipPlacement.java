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
	JButton placementButtons[], selectionButtons[], done;
	Ship[] ships;

	public ShipPlacement (SizeSetter sis, Assets ass, Ship[] ss, String p)
	{
		setLayout(new BorderLayout(sis.sizes[4], 0));
		setBackground(ass.colors[0]);
		playerLabel = new JLabel(p, SwingConstants.CENTER);
		playerLabel.setFont(ass.fonts[0]);
		playerLabel.setForeground(ass.colors[1]);
		add(playerLabel, BorderLayout.NORTH);

//------------------------------ Panels ------------------------------
		JPanel leftPanel = new JPanel(new BorderLayout());
		leftPanel.setPreferredSize(new Dimension(sis.sizes[5], 0));
		leftPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		leftPanel.setOpaque(false);
		add(leftPanel, BorderLayout.WEST);

		selectionPanel = new JPanel();
		Border selectionPanelb = BorderFactory.createMatteBorder(10, 10, 10, 10, ass.colors[2]);
		Border selectionPanelm = new EmptyBorder(20, 20, 20, 20);
		selectionPanel.setBorder(new CompoundBorder(selectionPanelb, selectionPanelm));
		selectionPanel.setBackground(ass.colors[3]);
		leftPanel.add(selectionPanel, BorderLayout.CENTER);

		BoxLayout spbl = new BoxLayout(selectionPanel, BoxLayout.Y_AXIS);
		selectionPanel.setLayout(spbl);

		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		centerPanel.setOpaque(false);
		add(centerPanel, BorderLayout.CENTER);

		placementPanel = new JPanel(new GridLayout(10, 10, 0, 0));
		placementPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		placementPanel.setBackground(ass.colors[2]);
		centerPanel.add(placementPanel, BorderLayout.CENTER);

		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.setPreferredSize(new Dimension(sis.sizes[5], 0));
		rightPanel.setBorder(new EmptyBorder(sis.sizes[6], 20, 20, 20));
		rightPanel.setOpaque(false);
		add(rightPanel, BorderLayout.EAST);

		JPanel donePanel = new JPanel();
		Border donePanelb = BorderFactory.createMatteBorder(10, 10, 10, 10, ass.colors[2]);
		Border donePanelm = new EmptyBorder(20, 20, 20, 20);
		donePanel.setBorder(new CompoundBorder(donePanelb, donePanelm));
		donePanel.setBackground(ass.colors[3]);
		rightPanel.add(donePanel, BorderLayout.CENTER);

		BoxLayout rpbl = new BoxLayout(donePanel, BoxLayout.Y_AXIS);
		donePanel.setLayout(rpbl);

//------------------------------ Content ------------------------------

		ships = ss;

		selectionButtons = new JButton[ships.length];
		setSelectionPanel(selectionButtons, ass);

		donePanel.add(Box.createVerticalGlue());

		done = new JButton("Done");
		done.setFocusable(false);
		done.setEnabled(true);//--------------------------------------------------------------------------false
		done.setFont(ass.fonts[0]);
		done.setBorder(new EmptyBorder(20, 60, 20, 60));
		done.setBackground(ass.colors[0]);
		done.setForeground(ass.colors[1]);
		done.setAlignmentX(CENTER_ALIGNMENT);
		donePanel.add(done);

		donePanel.add(Box.createVerticalGlue());

		placementButtons = new JButton[100];
		setPlacementPanel(placementButtons, ass);
	}

//--------------------------------------------------------------------------------------------------------------

	public void setSelectionPanel (JButton[] bs, Assets ass)
	{
		for (int i = 0; i < bs.length; i++)
		{
			bs[i] = new JButton();
			bs[i].setName(Integer.toString(i));
			bs[i].setFocusable(false);
			bs[i].setFont(ass.fonts[1]);
			bs[i].setBorder(new EmptyBorder(20, 20, 20, 20));
			bs[i].setBackground(ass.colors[0]);
			bs[i].setForeground(ass.colors[1]);
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

	public void setPlacementPanel (JButton[] bs, Assets ass)
	{
		for (int i = 0; i < bs.length; i++)
		{
			bs[i] = new JButton();
			bs[i].setName(Integer.toString(i));
			bs[i].setFocusable(false);
			bs[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ass.colors[1]));
			bs[i].setBackground(ass.colors[4]);
			placementPanel.add(bs[i]);
		}

		for (Ship ship: ships)
		{
			if (ship.placed)
			{
				for(int i: ship.position)
				{
					bs[i].setBackground(ass.colors[8]);
					bs[i].setEnabled(false);
				}
			}
		}
	}
}
