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

	public void docksTheme (String t)
	{
		if (t == "light")
		{
			colors[0] = new Color(241, 250, 238);//off white
			colors[1] = new Color(29, 53, 87);//background blue
			colors[2] = new Color(230, 57, 70);//red
			colors[3] = new Color(69, 123, 157);//dark blue

			colors[4] = new Color(168, 218, 220);//blue
			colors[5] = new Color(6, 214, 160);//green
			colors[6] = new Color(239, 71, 111);//red
			colors[7] = new Color(142, 202, 230);//light blue
			colors[8] = new Color(241, 250, 238);//off white
		}

		if (t == "dark")
		{
			colors[0] = new Color(29, 53, 87);//background blue
			colors[1] = new Color(241, 250, 238);//off white
			colors[2] = new Color(230, 57, 70);//red
			colors[3] = new Color(69, 123, 157);//dark blue

			colors[4] = new Color(168, 218, 220);//blue
			colors[5] = new Color(6, 214, 160);//green
			colors[6] = new Color(239, 71, 111);//red
			colors[7] = new Color(142, 202, 230);//light blue
			colors[8] = new Color(29, 53, 87);//background blue
		}
	}

	public void sunsetTheme (String t)
	{
		if (t == "light")
		{
			colors[0] = new Color(255, 183, 3);//yellow
			colors[1] = new Color(2, 48, 71);//background blue
			colors[2] = new Color(251, 133, 0);//orange
			colors[3] = new Color(33, 158, 188);//dark blue

			colors[4] = new Color(142, 202, 230);//blue
			colors[5] = new Color(6, 214, 160);//green
			colors[6] = new Color(239, 71, 111);//red
			colors[7] = new Color(142, 202, 230);//light blue
			colors[8] = new Color(255, 183, 3);//yellow
		}

		if (t == "dark")
		{
			colors[0] = new Color(2, 48, 71);//background blue
			colors[1] = new Color(255, 183, 3);//yellow
			colors[2] = new Color(251, 133, 0);//orange
			colors[3] = new Color(33, 158, 188);//dark blue

			colors[4] = new Color(142, 202, 230);//blue
			colors[5] = new Color(6, 214, 160);//green
			colors[6] = new Color(239, 71, 111);//red
			colors[7] = new Color(142, 202, 230);//light blue
			colors[8] = new Color(2, 48, 71);//background blue
		}
	}

	public void neonTheme (String t)
	{
		if (t == "light")
		{
			colors[0] = new Color(247, 37, 133);//pink
			colors[1] = new Color(58, 12, 163);//dark purple
			colors[2] = new Color(114, 9, 183);//light purple
			colors[3] = new Color(67, 97, 238);//dark blue

			colors[4] = new Color(76, 201, 240);//blue
			colors[5] = new Color(6, 214, 160);//green
			colors[6] = new Color(239, 71, 111);//red
			colors[7] = new Color(142, 202, 230);//light blue
			colors[8] = new Color(247, 37, 133);//pink
		}

		if (t == "dark")
		{
			colors[0] = new Color(58, 12, 163);//dark purple
			colors[1] = new Color(247, 37, 133);//pink
			colors[2] = new Color(114, 9, 183);//light purple
			colors[3] = new Color(67, 97, 238);//dark blue

			colors[4] = new Color(76, 201, 240);//blue
			colors[5] = new Color(6, 214, 160);//green
			colors[6] = new Color(239, 71, 111);//red
			colors[7] = new Color(142, 202, 230);//light blue
			colors[8] = new Color(58, 12, 163);//dark purple
		}
	}

	public void pastelTheme (String t)
	{
		if (t == "light")
		{
			colors[0] = new Color(241, 227, 211);//light pink
			colors[1] = new Color(205, 180, 219);//purple
			colors[2] = new Color(255, 175, 204);//dark pink
			colors[3] = new Color(162, 210, 255);//dark blue

			colors[4] = new Color(189, 224, 254);//blue
			colors[5] = new Color(6, 214, 160);//green
			colors[6] = new Color(239, 71, 111);//red
			colors[7] = new Color(142, 202, 230);//light blue
			colors[8] = new Color(255, 200, 221);//pink
		}

		if (t == "dark")
		{
			colors[0] = new Color(205, 180, 219);//purple
			colors[1] = new Color(241, 227, 211);//light pink
			colors[2] = new Color(255, 175, 204);//dark pink
			colors[3] = new Color(162, 210, 255);//dark blue

			colors[4] = new Color(189, 224, 254);//blue
			colors[5] = new Color(6, 214, 160);//green
			colors[6] = new Color(239, 71, 111);//red
			colors[7] = new Color(142, 202, 230);//light blue
			colors[8] = new Color(205, 180, 219);//purple
		}
	}
}
