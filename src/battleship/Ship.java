package battleship;

public class Ship
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
	    System.out.println(Integer.toString(size));

	    for (int i = 0; i < size; i++)
	    {
	        System.out.println(Integer.toString(position[i]));
	        System.out.println(hits[i]);
	    }
	    
	    System.out.println(orientation);
	    System.out.println(selected);
	    System.out.println(placed);
	    System.out.println(sunk);
	}
}
