package battleship;

import java.io.*;

@SuppressWarnings("serial")

public class SaveManager
{
	String filePath;
	File file;
	ObjectOutputStream output;
	ObjectInputStream input;

	public SaveManager (int save) throws IOException
	{
		filePath = "sfs/save" + save + "/";

		if (!fileExistance(new File(filePath + "shipsA")))
		{
			fileSave("A", new Ship[] {new Ship(2), new Ship(2), new Ship(3), new Ship(3), new Ship(4)});
		}

		if (!fileExistance(new File(filePath + "shipsB")))
		{
			fileSave("B", new Ship[] {new Ship(2), new Ship(2), new Ship(3), new Ship(3), new Ship(4)});
		}

		if (!fileExistance(new File(filePath + "shotsA")))
		{
			fileSave("A", new boolean[100]);
		}

		if (!fileExistance(new File(filePath + "shotsB")))
		{
			fileSave("B", new boolean[100]);
		}

		if (!fileExistance(new File(filePath + "turn")))
		{
			fileSave(true);
		}
	}

	public boolean fileExistance (File f)
	{
		boolean exists = false;

		if (f.isFile())
		{
			exists = true;
		}

		return exists;
	}

	public void fileSave (String player, Ship[] ships)
	{
		try
		{
			file = new File(filePath + "ships" + player);
			output = new ObjectOutputStream(new FileOutputStream(file));
			output.writeObject(ships);
			output.close();
		}

		catch (IOException e)
		{
			System.out.println("Error initializing stream");
		}
	}

	public void fileSave (String player, boolean[] shots)
	{
		try
		{
			file = new File(filePath + "shots" + player);
			output = new ObjectOutputStream(new FileOutputStream(file));
			output.writeObject(shots);
			output.close();
		}

		catch (IOException e)
		{
			System.out.println("Error initializing stream");
		}
	}

	public void fileSave (boolean turn)
	{
		try
		{
			file = new File(filePath + "turn");
			output = new ObjectOutputStream(new FileOutputStream(file));
			output.writeObject(turn);
			output.close();
		}

		catch (IOException e)
		{
			System.out.println("Error initializing stream");
		}
	}

	public boolean[] fileLoad (String player, boolean[] shots)
	{
		try
		{
			file = new File(filePath + "shots" + player);
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

	public Ship[] fileLoad (String player, Ship[] ships)
	{
		try
		{
			file = new File(filePath + "ships" + player);
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

	public boolean fileLoad (boolean turn)
	{
		try
		{
			file = new File(filePath + "turn");
			input = new ObjectInputStream((new FileInputStream(file)));
			turn = (boolean) input.readObject();
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
}