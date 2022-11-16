package battleship;

import java.awt.*;

public class SizeSetter
{
	GraphicsDevice graphicsDevice;
	float screenWidth, screenHeight;
	int[] sizes;

	public SizeSetter ()
	{
		graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		screenWidth = graphicsDevice.getDisplayMode().getWidth();
		screenHeight = graphicsDevice.getDisplayMode().getHeight();

		System.out.println(screenWidth + " x " + screenHeight);
		System.out.println(screenWidth/screenHeight);

		sizes = new int[13];

		if ((screenWidth/screenHeight) > 1.5)
		{
			sizes[0] = (int) Math.round(screenHeight*0.3);// h next turn panel
			sizes[1] = (int) Math.round(screenWidth*0.2);// w next turn panel

			sizes[2] = (int) Math.round(screenHeight*0.05);// h next turn button
			sizes[3] = (int) Math.round(screenWidth*0.05);// w next turn button

			sizes[4] = (int) Math.round(screenWidth*0.04);// w shooting

			sizes[5] = (int) Math.round(screenWidth*0.2);// w player ships panel

			sizes[6] = (int) Math.round(screenHeight*0.53);// h player ships panel

			sizes[7] = (int) Math.round(screenWidth*0.04);// w ship placement padding

			sizes[8] = (int) Math.round(screenWidth*0.18);// w selection panel

			sizes[9] = (int) Math.round(screenHeight*0.35);// h done panel

			sizes[10] = (int) Math.round(screenWidth*0.4);// w winner player panels

			sizes[11] = (int) Math.round(screenHeight*0.1);// h winner player panels

			sizes[12] = (int) Math.round(screenHeight*0.35);// h winner main menu panel
		}

		else
		{
			sizes[0] = (int) Math.round(screenHeight*0.2);// h next turn panel
			sizes[1] = (int) Math.round(screenWidth*0.1);// w next turn panel

			sizes[2] = (int) Math.round(screenHeight*0.05);// h next turn button
			sizes[3] = (int) Math.round(screenWidth*0.05);// w next turn button

			sizes[4] = (int) Math.round(screenWidth*0.04);// w shooting

			sizes[5] = (int) Math.round(screenWidth*0.2);// w player ships panel

			sizes[6] = (int) Math.round(screenHeight*0.53);// h player ships panel

			sizes[7] = (int) Math.round(screenWidth*0.04);// w ship placement padding

			sizes[8] = (int) Math.round(screenWidth*0.18);// w selection panel

			sizes[9] = (int) Math.round(screenHeight*0.35);// h done panel

			sizes[10] = (int) Math.round(screenWidth*0.4);// w winner player panels

			sizes[11] = (int) Math.round(screenHeight*0.1);// h winner player panels

			sizes[12] = (int) Math.round(screenHeight*0.35);// h winner main menu panel
		}

		print();
	}

	public void print()
	{
		for (int size: sizes)
		{
			System.out.println(size);
		}
	}
}