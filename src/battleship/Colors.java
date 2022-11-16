package battleship;

import javax.swing.*;
import java.awt.*;

public class Colors
{
    Color[] colors;

    public Colors (String[] theme)
    {
    	colors = new Color[9];

    	if (theme[0] == "retro")
    	{
    		retroTheme(theme[1]);
    	}

    	else if (theme[0] == "beach")
    	{
    		beachTheme(theme[1]);
    	}

    	else if (theme[0] == "docks")
    	{
    		docksTheme(theme[1]);
    	}

    	else if (theme[0] == "sunset")
    	{
    		sunsetTheme(theme[1]);
    	}

    	else if (theme[0] == "neon")
    	{
			neonTheme(theme[1]);
    	}

    	else if (theme[0] == "pastel")
    	{
    		pastelTheme(theme[1]);
    	}
    }

    /*
	background 			0
	foreground 			1
	highlight			2
	menu				3

	board				4
	placeable			5
	!placeable & hit 	6
	!hit				7
	ships				8
	*/

	public void retroTheme (String t)
	{
		if (t == "light")
		{
			colors[0] = new Color(255, 209, 102);//yellow
			colors[1] = Color.black;//black
			colors[2] = new Color(247, 140, 107);//orange
			colors[3] = new Color(17, 138, 178);//blue

			colors[4] = new Color(17, 138, 178);//blue
			colors[5] = new Color(6, 214, 160);//green
			colors[6] = new Color(239, 71, 111);//red
			colors[7] = new Color(142, 202, 230);//light blue
			colors[8] = new Color(7, 59, 76);//dark blue
		}

		if (t == "dark")
		{
			colors[0] = new Color(7, 59, 76);//dark blue
			colors[1] = Color.white;//white
			colors[2] = new Color(247, 140, 107);//orange
			colors[3] = new Color(17, 138, 178);//blue

			colors[4] = new Color(17, 138, 178);//blue
			colors[5] = new Color(6, 214, 160);//green
			colors[6] = new Color(239, 71, 111);//red
			colors[7] = new Color(142, 202, 230);//light blue
			colors[8] = new Color(255, 209, 102);//yelow
		}
	}

	public void beachTheme (String t)
	{
		if (t == "light")
		{
			colors[0] = new Color(241, 227, 211);//cream
			colors[1] = new Color(0, 48, 73);//dark blue
			colors[2] = new Color(242, 208, 169);//orange

			colors[3] = new Color(239, 71, 111);//red
			colors[4] = new Color(6, 214, 160);//green
			colors[5] = new Color(102, 155, 188);//light blue
			colors[6] = new Color(142, 125, 190);//purple
		}

		if (t == "dark")
		{
			colors[0] = new Color(0, 48, 73);//dark blue
			colors[1] = new Color(241, 227, 211);//cream
			colors[2] = new Color(242, 208, 169);//orange

			colors[3] = new Color(239, 71, 111);//red
			colors[4] = new Color(6, 214, 160);//green
			colors[5] = new Color(102, 155, 188);//light blue
			colors[6] = new Color(142, 125, 190);//purple
		}
	}

	public void docksTheme (String t)
	{
		if (t == "light")
		{
			colors[0] = new Color(253, 240, 213);//cream
			colors[1] = new Color(0, 48, 73);//dark blue
			colors[2] = new Color(120, 0, 0);//dark red

			colors[3] = new Color(239, 71, 111);//light red
			colors[4] = new Color(6, 214, 160);//green
			colors[5] = new Color(102, 155, 188);//light blue
			colors[6] = new Color(142, 125, 190);//purple
		}

		if (t == "dark")
		{
			colors[0] = new Color(0, 48, 73);//dark blue
			colors[1] = new Color(253, 240, 213);//cream
			colors[2] = new Color(120, 0, 0);//dark red

			colors[3] = new Color(239, 71, 111);//light red
			colors[4] = new Color(6, 214, 160);//green
			colors[5] = new Color(102, 155, 188);//light blue
			colors[6] = new Color(142, 125, 190);//purple
		}
	}

	public void sunsetTheme (String t)
	{
		if (t == "light")
		{
			colors[0] = new Color(253, 240, 213);//cream
			colors[1] = new Color(2, 48, 71);//dark blue
			colors[2] = new Color(120, 0, 0);//dark red

			colors[3] = new Color(239, 71, 111);//light red
			colors[4] = new Color(6, 214, 160);//green
			colors[5] = new Color(102, 155, 188);//light blue
			colors[6] = new Color(142, 125, 190);//purple
		}

		if (t == "dark")
		{
			colors[0] = new Color(2, 48, 71);//dark blue
			colors[1] = new Color(253, 240, 213);//cream
			colors[2] = new Color(120, 0, 0);//dark red

			colors[3] = new Color(239, 71, 111);//light red
			colors[4] = new Color(6, 214, 160);//green
			colors[5] = new Color(102, 155, 188);//light blue
			colors[6] = new Color(142, 125, 190);//purple
		}
	}

	public void neonTheme (String t)
	{
		if (t == "light")
		{
			colors[0] = new Color(253, 240, 213);//cream
			colors[1] = new Color(0, 48, 73);//dark blue
			colors[2] = new Color(120, 0, 0);//dark red

			colors[3] = new Color(239, 71, 111);//light red
			colors[4] = new Color(6, 214, 160);//green
			colors[5] = new Color(102, 155, 188);//light blue
			colors[6] = new Color(142, 125, 190);//purple
		}

		if (t == "dark")
		{
			colors[0] = new Color(0, 48, 73);//dark blue
			colors[1] = new Color(253, 240, 213);//cream
			colors[2] = new Color(120, 0, 0);//dark red

			colors[3] = new Color(239, 71, 111);//light red
			colors[4] = new Color(6, 214, 160);//green
			colors[5] = new Color(102, 155, 188);//light blue
			colors[6] = new Color(142, 125, 190);//purple
		}
	}

	public void pastelTheme (String t)
	{
		if (t == "light")
		{
			colors[0] = new Color(253, 240, 213);//cream
			colors[1] = new Color(0, 48, 73);//dark blue
			colors[2] = new Color(120, 0, 0);//dark red

			colors[3] = new Color(239, 71, 111);//light red
			colors[4] = new Color(6, 214, 160);//green
			colors[5] = new Color(102, 155, 188);//light blue
			colors[6] = new Color(142, 125, 190);//purple
		}

		if (t == "dark")
		{
			colors[0] = new Color(0, 48, 73);//dark blue
			colors[1] = new Color(253, 240, 213);//cream
			colors[2] = new Color(120, 0, 0);//dark red

			colors[3] = new Color(239, 71, 111);//light red
			colors[4] = new Color(6, 214, 160);//green
			colors[5] = new Color(102, 155, 188);//light blue
			colors[6] = new Color(142, 125, 190);//purple
		}
	}
}
