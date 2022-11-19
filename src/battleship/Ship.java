package battleship;

import java.io.*;

@SuppressWarnings("serial")

public class Ship implements Serializable
{
	int size;
	int[] position;
	boolean orientation, selected, placed, sunk;
	boolean[] hits;

	public Ship (int shipSize)
	{
		size = shipSize;
		position = new int[size];
		hits = new boolean[size];
		orientation = true;
		selected = false;
		placed = false;
		sunk = false;
	}

	public void print ()
	{
		System.out.println("size: " + size);
		System.out.println();

		for (int i = 0; i < size; i++)
		{
			System.out.println("position: " + position[i]);
			System.out.println("hits: " + hits[i]);
		}
		System.out.println();
		
		System.out.println("orientation: " + orientation);
		System.out.println("selected: " + selected);
		System.out.println("placed: " + placed);
		System.out.println("sunk: " + sunk);
		System.out.println();
	}
}
