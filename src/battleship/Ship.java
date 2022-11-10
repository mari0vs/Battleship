package battleship;

public class Ship
{
	int size;
	int[] position;
	boolean orientation, selected, placed, dead;

	public Ship (int shipSize)
	{
		size = shipSize;
        position = new int[size];
	    orientation = true;
	    selected = false;
	    placed = false;
	    dead = false;
	}

	public void print ()
	{
	    System.out.println(Integer.toString(size));

	    for (int i: position)
	    {
	        System.out.println(Integer.toString(size));
	    }
	    
	    System.out.println(orientation);
	    System.out.println(selected);
	    System.out.println(placed);
	    System.out.println(dead);
	}
}
