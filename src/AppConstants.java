public class AppConstants {
	
	//Window Variables
	public static String WINDOW_TITLE = "Blackjack";
	public static int WINDOW_WIDTH = 1000;
	public static int WINDOW_HEIGHT = 600;
	
	//Application Variables
	public static double FRAME_RATE = 60;
	
	//Other Variables
	public static Deck deck = new Deck();
	public static final int HEARTS = 0;
	public static final int DIAMONDS = 1;
	public static final int CLUBS = 2;
	public static final int SPADES = 3;
	
	//Hand Status Variables
	public static final int NOTHING = 0;
	public static final int BUST = 1;
	public static final int BLACKJACK = 2;
	
	//Game Status Variables
	public static final int RUNNING = 0;
	public static final int PUSH = 111;
	public static final int HOUSE_WINS = 222;
	public static final int PLAYER_WINS = 333;
	
	//Chat Variables
	public static final int GENERAL = 0;
	public static final int PLAYER = 1;
	public static final int HOUSE = 2;
	public static final int URGENT = 3;
}
