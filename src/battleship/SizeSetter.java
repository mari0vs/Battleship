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

		sizes = new int[6];

		if ((screenWidth/screenHeight) > 1.5)
		{
			sizes[0] = (int) Math.round(screenWidth*0.5);
			sizes[1] = (int) Math.round(screenHeight*0.5);

			sizes[2] = (int) Math.round(screenHeight*0.3);
			sizes[3] = (int) Math.round(screenWidth*0.2);

			sizes[4] = (int) Math.round(screenHeight*0.05);
			sizes[5] = (int) Math.round(screenWidth*0.05);
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