package battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")

public class MenuInterface extends JFrame implements ActionListener
{
	JPanel menuPanel;
	JLabel menuTitle, blank;
	JButton newGame, loadGame, exit;

	public MenuInterface ()
	{
		Font font = new Font("Comic Sans", Font.BOLD, 30);

		//Frame
		setTitle("Menu");
		setSize(400, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Content Pane
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.black);
		contentPane.setLayout(null);

		//Panels
		menuPanel = new JPanel(new GridLayout(5, 1, 0, 50));
		menuPanel.setBounds(10, 10, 365, 745);
		menuPanel.setBackground(Color.gray);
		contentPane.add(menuPanel);

		menuTitle = new JLabel("Battleship", SwingConstants.CENTER);
		menuTitle.setFont(new Font("Comic Sans", Font.BOLD, 60));
		menuTitle.setForeground(Color.white);
		menuPanel.add(menuTitle);

		newGame = new JButton("New Game");
		newGame.setFocusable(false);
		newGame.setOpaque(true);
		newGame.setFont(font);
		newGame.setBackground(Color.darkGray);
		newGame.setForeground(Color.white);
		newGame.addActionListener(this);
		menuPanel.add(newGame);

		loadGame = new JButton("Load Game");
		loadGame.setFocusable(false);
		loadGame.setOpaque(true);
		loadGame.setFont(font);
		loadGame.setBackground(Color.darkGray);
		loadGame.setForeground(Color.white);
		loadGame.addActionListener(this);
		menuPanel.add(loadGame);

		exit = new JButton("Exit");
		exit.setFocusable(false);
		exit.setOpaque(true);
		exit.setFont(font);
		exit.setBackground(Color.darkGray);
		exit.setForeground(Color.white);
		exit.addActionListener(this);
		menuPanel.add(exit);

		blank = new JLabel();
		menuPanel.add(blank);
	}

	public void actionPerformed (ActionEvent e)
	{
		GameInterface gi;

		if (e.getSource() instanceof JButton)
		{
			JButton clickedButton = (JButton) e.getSource();
			String buttonText = clickedButton.getText();

			if (buttonText == "New Game")
			{
				gi = new GameInterface();
				gi.exit.addActionListener(this);
				gi.newGame.addActionListener(this);
				gi.setVisible(true);
				setVisible(false);
			}

			else if (buttonText == "Load Game")
			{
				gi = new GameInterface();
				gi.exit.addActionListener(this);
				gi.newGame.addActionListener(this);
				gi.setVisible(true);
				setVisible(false);
			}

			else
			{
				System.exit(0);
			}
		}

		else
		{
			JMenuItem clickedItem = (JMenuItem) e.getSource();
			String clickedItemText = clickedItem.getText();

			if (clickedItemText == "New Game")
			{
				gi = new GameInterface();
				gi.exit.addActionListener(this);
				gi.newGame.addActionListener(this);
			}

			else if (clickedItemText == "Load Game")
			{
				gi = new GameInterface();
				gi.exit.addActionListener(this);
				gi.newGame.addActionListener(this);
			}

			else if (clickedItemText == "Exit")
			{
				gi = null;
				System.gc();
				setVisible(true);
			}
		}
	}

	public static void main (String[] args)
	{
		MenuInterface mi = new MenuInterface();
		mi.setVisible(true);
	}
}
