import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class Chat {

	private ArrayList<String> chat = new ArrayList<String>();
	private ArrayList<Integer> urgencies = new ArrayList<Integer>();
	
	public void paint(Graphics g, DVector position)
	{
		g.setFont(new Font(null, Font.PLAIN, 17));
		for (int i = 0; i < chat.size(); i++)
		{
			int alpha;
			if (255 - (i * 25) < 0)
			{
				alpha = 0;
			}
			else
			{
				alpha = 255 - (i * 25);
			}
			
			switch (urgencies.get(i))
			{
			case AppConstants.GENERAL:
				g.setColor(new Color(255, 255, 255, alpha));
				break;
			case AppConstants.PLAYER:
				g.setColor(new Color(240, 240, 50, alpha));
				break;
			case AppConstants.HOUSE:
				g.setColor(new Color(150, 50, 255, alpha));
				break;
			case AppConstants.URGENT:
				g.setColor(new Color(255, 0, 0, alpha));
				break;
			}
			
			g.drawString(chat.get(i), (int)position.getX(), (int)position.getY() - (i * 20));
		}
	}
	
	public void add(String stringToAdd, int type)
	{
		chat.add(0, stringToAdd);
		urgencies.add(0, type);
	}
}