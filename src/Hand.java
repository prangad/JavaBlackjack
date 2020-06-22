import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

public class Hand {
	
	private ArrayList<Card> hand = new ArrayList<Card>();
	private Font font = new Font(null, Font.BOLD, 20);
	
	private boolean containsAce = false;
	
	public void add(Card cardToAdd)
	{
		if (!cardToAdd.getFlipped() && cardToAdd.getRank() == 13)
		{
			containsAce = true;
		}
		hand.add(cardToAdd);
	}
	
	public int getHighestValue()
	{
		ArrayList<Integer> values = this.getValue();
		if (values.size() > 1 && values.get(0) < values.get(1))
		{
			return values.get(1);
		}
		else
		{
			return values.get(0);
		}
	}
	
	public ArrayList<Integer> getValue()
	{
		int value = 0;
		ArrayList<Integer> values = new ArrayList<Integer>();
		boolean doubleValue = false;
		
		if (containsAce)
		{
			int aceCount = 0;
			for (int i = 0; i < hand.size(); i++)
			{
				if (!hand.get(i).getFlipped()
						&& hand.get(i).getRank() != 13)
				{
					value += hand.get(i).getValue();
				}
				else if (!hand.get(i).getFlipped())
				{
					aceCount++;
				}
			}
			
			value += aceCount;
			
			for (int i = 0; i < aceCount; i++)
			{
				if (21 - value >= 10)
				{
					doubleValue = true;
					value += 10;
					values.add(value);
				}
				else
				{
					break;
				}
			}
			
			if (doubleValue)
				values.add(value - 10);
			else
				values.add(value);
		}
		else
		{
			for (int i = 0; i < hand.size(); i++)
			{
				if (!hand.get(i).getFlipped())
					value += hand.get(i).getValue();
			}
			
			values.add(value);
		}
		
		return values;
	}
	
	public String getStringValue()
	{
		ArrayList<Integer> values = this.getValue();
		if (values.size() > 1)
		{
			return values.get(0) + "/" + values.get(1);
		}
		else
		{
			return Integer.toString(values.get(0));
		}
	}
	
	public void paint(Graphics g, DVector position)
	{
		for (int i = 0; i < hand.size(); i++)
		{
			g.drawImage(AppConstants.deck.getCardImage(hand.get(i)),
					(int)(position.getX() - 40 - ((hand.size() - 1) * 12) + (i * 24)),
					(int)position.getY() + (i * 6),
					null);
			drawCenteredText(g, getStringValue(),
					(int)position.getX(),
					(int)position.getY());
		}
	}
	
	public void drawCenteredText(Graphics g, String string, int ox, int oy)
	{
		g.setFont(font);
		FontMetrics metrics = g.getFontMetrics(font);
		
		int x = ox - metrics.stringWidth(string) / 2;
		int y = oy - metrics.getHeight() / 2;
				
		g.drawString(string, x, y);
	}
	
	public int getStatus()
	{
		ArrayList<Integer> values = this.getValue();
		
		if (values.size() > 1 && values.get(0) == 21)
		{
			return AppConstants.BLACKJACK;
		}
		else if (values.get(0) > 21)
		{
			return AppConstants.BUST;
		}
		else
		{
			return AppConstants.NOTHING;
		}
	}
	
	public void reset()
	{
		containsAce = false;
		hand.clear();
	}
	
	public Card get(int index)
	{
		return hand.get(index);
	}
	
	public void flipAllUp()
	{
		for (Card card : hand)
		{
			card.setFlipped(false);
		}
	}
	
}