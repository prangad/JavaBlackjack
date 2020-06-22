import java.awt.Graphics;
import java.util.ArrayList;

public class Table {
	
	Hand houseHand = new Hand();
	Hand playerHand = new Hand();
	
	Chat chat = new Chat();
	
	DVector houseHandPosition = new DVector(AppConstants.WINDOW_WIDTH/2, 100);
	DVector playerHandPosition = new DVector(AppConstants.WINDOW_WIDTH/2, 380);
	DVector chatPosition = new DVector(20, AppConstants.WINDOW_HEIGHT - 40);
	
	private int status = AppConstants.RUNNING;
	
	public void paint(Graphics g)
	{
		houseHand.paint(g, houseHandPosition);
		playerHand.paint(g, playerHandPosition);
		chat.paint(g, chatPosition);
	}
	
	public void start()
	{
		Card drawnCard = AppConstants.deck.draw();
		chat.add("[START] Player drew a " + drawnCard.toString() + ".", AppConstants.PLAYER);
		playerHand.add(drawnCard);
		
		drawnCard = AppConstants.deck.draw();
		chat.add("[START] House drew a hidden card.", AppConstants.HOUSE);
		drawnCard.setFlipped(true);
		houseHand.add(drawnCard);
		
		drawnCard = AppConstants.deck.draw();
		chat.add("[START] Player drew a " + drawnCard.toString() + ".", AppConstants.PLAYER);
		playerHand.add(drawnCard);
		
		drawnCard = AppConstants.deck.draw();
		chat.add("[START] House drew a " + drawnCard.toString() + ".", AppConstants.HOUSE);
		houseHand.add(drawnCard);
		
		if (playerHand.getStatus() == AppConstants.BLACKJACK)
		{
			playerStand();
		}
	}
	
	public void playerHit()
	{
		Card drawnCard = AppConstants.deck.draw();
		chat.add("[HIT] Player drew a " + drawnCard.toString() + ".", AppConstants.PLAYER);
		playerHand.add(drawnCard);
		if (playerHand.getStatus() == AppConstants.BUST)
		{
			checkStatus();
		}
	}
	
	public void playerStand()
	{
		Card drawnCard;
		houseHand.get(0).setFlipped(false);
		
		while (houseHand.getHighestValue() < 17)
		{
			drawnCard = AppConstants.deck.draw();
			chat.add("[HIT] House drew a " + drawnCard.toString() + ".", AppConstants.HOUSE);
			houseHand.add(drawnCard);
		}
		checkStatus();
	}
	
	private void checkStatus()
	{
		if (playerHand.getStatus() == AppConstants.BLACKJACK
				&& houseHand.getStatus() != AppConstants.BLACKJACK)
		{
			chat.add("PLAYER BLACKJACK! PLAYER WINS!", AppConstants.URGENT);
			status = AppConstants.PLAYER_WINS;
		}
		else if (playerHand.getStatus() == AppConstants.BUST)
		{
			chat.add("PLAYER BUST. HOUSE WINS.", AppConstants.URGENT);
			status = AppConstants.HOUSE_WINS;
		}
		else if (houseHand.getStatus() == AppConstants.BUST)
		{
			chat.add("HOUSE BUST. PLAYER WINS.", AppConstants.URGENT);
			status = AppConstants.PLAYER_WINS;
		}
		else if (houseHand.getHighestValue() > playerHand.getHighestValue())
		{
			chat.add("HOUSE HAND IS BETTER THAN PLAYER HAND. HOUSE WINS.", AppConstants.URGENT);
			status = AppConstants.HOUSE_WINS;
		}
		else if (houseHand.getHighestValue() < playerHand.getHighestValue())
		{
			chat.add("PLAYER HAND IS BETTER THAN HOUSE HAND. PLAYER WINS.", AppConstants.URGENT);
			status = AppConstants.PLAYER_WINS;
		}
		else if (houseHand.getHighestValue() == playerHand.getHighestValue())
		{
			chat.add("THE GAME IS A TIE. PUSH.", AppConstants.URGENT);
			status = AppConstants.PUSH;
		}
		
		houseHand.flipAllUp();
	}
	
	public void reset()
	{
		chat.add("-----=== NEW GAME ===-----", AppConstants.GENERAL);
		this.status = AppConstants.RUNNING;
		playerHand.reset();
		houseHand.reset();
		start();
	}
	
	public int getStatus()
	{
		return this.status;
	}
}