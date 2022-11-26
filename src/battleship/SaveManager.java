package battleship;

import java.io.*;

@SuppressWarnings("serial")

public class SaveManager
{
	File file;
	ObjectOutputStream output;
	ObjectInputStream input;

	public SaveManager (int save, String fileName) throws IOException
	{
		file = new File("sfs/save" + save + "/" + fileName);
	}

	public void fileSave (int turn)
	{
		try
		{
			output = new ObjectOutputStream(new FileOutputStream(file));
			output.writeObject(turn);
			output.close();
		}

		catch (IOException e)
		{
			System.out.println("Error initializing stream");
		}
	}

	public void fileSave (boolean[] shots)
	{
		try
		{
			output = new ObjectOutputStream(new FileOutputStream(file));
			output.writeObject(shots);
			output.close();
		}

		catch (IOException e)
		{
			System.out.println("Error initializing stream");
		}
	}

	public void fileSave (Ship[] ships)
	{
		try
		{
			output = new ObjectOutputStream(new FileOutputStream(file));
			output.writeObject(ships);
			output.close();
		}

		catch (IOException e)
		{
			System.out.println("Error initializing stream");
		}
	}

	public int fileLoad (int turn)
	{
		try
		{
			input = new ObjectInputStream((new FileInputStream(file)));
			turn = (int) input.readObject();
			input.close();
		}

		catch (FileNotFoundException e)
		{
			System.out.println("File not found");
		}

		catch (IOException e)
		{
			System.out.println("Error initializing stream");
		}

		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		return turn;
	}

	public boolean[] fileLoad (boolean[] shots)
	{
		try
		{
			input = new ObjectInputStream((new FileInputStream(file)));
			shots = (boolean[]) input.readObject();
			input.close();
		}

		catch (FileNotFoundException e)
		{
			System.out.println("File not found");
		}

		catch (IOException e)
		{
			System.out.println("Error initializing stream");
		}

		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		return shots;
	}

	public Ship[] fileLoad (Ship[] ships)
	{
		try
		{
			input = new ObjectInputStream((new FileInputStream(file)));
			ships = (Ship[]) input.readObject();
			input.close();
		}

		catch (FileNotFoundException e)
		{
			System.out.println("File not found");
		}

		catch (IOException e)
		{
			System.out.println("Error initializing stream");
		}

		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		return ships;
	}

	public boolean fileExistance ()
	{
		boolean exists = false;

		if (file.isFile())
		{
			exists = true;
		}

		return exists;
	}
}

/*
	if !shipsA.allShipsPlaced
	if !shipsB.allShipsPlaced

	if (file empty)
		menuItems.setEnabeled(false);
*/