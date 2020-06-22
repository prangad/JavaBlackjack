public class Card {
	private int rank;
	private int suit;
	
	private boolean flipped = false;
	
	public Card(int rank, int suit, boolean flipped)
	{
		this.rank = rank;
		this.suit = suit;
		this.flipped = flipped;
	}
	
	public int getRank()
	{
		return this.rank;
	}
	
	public int getSuit()
	{
		return this.suit;
	}
	
	public int getValue()
	{
		if (this.rank == 13)
		{
			return 11;
		}
		if (this.rank + 1 >= 10)
		{
			return 10;
		}
		else
		{
			return this.rank + 1;
		}
	}
	
	public String toString()
	{
		String cardNum = Integer.toString(this.rank + 1);
		switch (this.rank)
		{
		case 10:
			cardNum = "Jack";
			break;
		case 11:
			cardNum = "Queen";
			break;
		case 12:
			cardNum = "King";
			break;
		case 13:
			cardNum = "Ace";
			break;
		}
		
		String cardSuit = "";
		
		switch (this.suit)
		{
		case AppConstants.HEARTS:
			cardSuit = "Hearts";
			break;
		case AppConstants.DIAMONDS:
			cardSuit = "Diamonds";
			break;
		case AppConstants.CLUBS:
			cardSuit = "Clubs";
			break;
		case AppConstants.SPADES:
			cardSuit = "Spades";
			break;
		}
		
		return cardNum + " of " + cardSuit;
	}
	
	public boolean getFlipped()
	{
		return this.flipped;
	}
	
	public void setFlipped(boolean flipped)
	{
		this.flipped = flipped;
	}
	
}