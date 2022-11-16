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

			sizes[4] = (int) Math.round(screenWidth*0.035);// w border layout padding

			sizes[5] = (int) Math.round(screenWidth*0.22);// w selection panel
			sizes[6] = (int) Math.round(screenHeight*0.6);// h done panel

			sizes[7] = (int) Math.round(screenWidth*0.22);// w player ships panel
			sizes[8] = (int) Math.round(screenHeight*0.47);// h player ships panel

			sizes[9] = (int) Math.round(screenWidth*0.4);// w winner player panels
			sizes[10] = (int) Math.round(screenHeight*0.1);// h winner player panels
			sizes[11] = (int) Math.round(screenHeight*0.35);// h winner main menu panel
		}

		else
		{
			sizes[0] = (int) Math.round(screenHeight*0.2);// h next turn panel
			sizes[1] = (int) Math.round(screenWidth*0.1);// w next turn panel
			sizes[2] = (int) Math.round(screenHeight*0.05);// h next turn button
			sizes[3] = (int) Math.round(screenWidth*0.05);// w next turn button

			sizes[4] = (int) Math.round(screenWidth*0.02);// w border layout padding
			sizes[5] = (int) Math.round(screenWidth*0.2);// w selection panel
			sizes[6] = (int) Math.round(screenHeight*0.58);// h done panel

			sizes[7] = (int) Math.round(screenWidth*0.15);// w player ships panel
			sizes[8] = (int) Math.round(screenHeight*0.48);// h player ships panel

			sizes[9] = (int) Math.round(screenWidth*0.3);// w winner player panels
			sizes[10] = (int) Math.round(screenHeight*0.14);// h winner player panels
			sizes[11] = (int) Math.round(screenHeight*0.25);// h winner main menu panel
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