import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;

public class Deck {
	private BufferedImage deckSprite;
	ArrayList<Card> deckList = new ArrayList<Card>();
	private int cardIndex = 0;
	
	public Deck()
	{
		try
		{
			deckSprite = ImageIO.read(new File("src/Cards.gif"));
			
			for (int i = 0; i <= 3; i++)
			{
				for (int j = 1; j <= 13; j++)
				{
					deckList.add(new Card(j, i, false));
				}
			}
			
			this.shuffle();
			
		}
		catch (IOException e)
		{
			System.out.println("There was an error grabbing the deck sprite sheet.");
		}
	}
	
	public BufferedImage getCardImage(Card card)
	{
		BufferedImage cardImage;
		if (!card.getFlipped())
		{
			cardImage = deckSprite.getSubimage((card.getRank() - 1) * 81, card.getSuit() * 117, 81, 117);
		}
		else
		{
			cardImage = deckSprite.getSubimage(0, 469, 81, 117);
		}
		return cardImage;
	}
	
	private void shuffle()
	{
		Collections.shuffle(deckList);
	}
	
	public Card draw()
	{
		//Replace with constant drawing. (i.e. first card, second card, etc.)
		Card cardToReturn = deckList.get(cardIndex);
		cardIndex++;
		return cardToReturn;
	}
	
	public void reset()
	{
		for (int i = 0; i < deckList.size(); i++)
		{
			deckList.get(i).setFlipped(false);
		}
		this.shuffle();
		this.cardIndex = 0;
	}
}