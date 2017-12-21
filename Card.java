
public class Card {
	
	enum Suit { Club, Diamond, Heart, Spade }; // 因為homework3中的 Card.Suit suit= card.getSuit() 所以 enum 要放在card裡面 
		
	    private Suit suit; //Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4
		private int rank;
		
		String[] rankrank = { "Aces","2","3","4","5","6","7","8","9","10","JACK","QUEEN","KING" };//1~13，1 = Aces, 11 = JECK , 12 = QUEEN , 13 = KING
		/**
		 * @param s suit
		 * @param r rank
		 */
		
		public Card( Suit s,int r)
		{
			suit = s;
			rank = r;		
		}
		
		//TODO: 1. Please implement the printCard method (20 points, 10 for suit, 10 for rank
		
		public void printCard()
		{
			//Hint: print (System.out.println) card as suit,rank, for example: print 1,1 as Clubs Ace
			/*
	       //* display ndeck 副牌，suitsuit為花色，rankrank為牌數
	        */
			
			if(suit==Suit.Club) 
			{
			System.out.println( Suit.Club + " , " + rankrank[rank-1] ); 
		    }
			if(suit==Suit.Diamond)
			{
				System.out.println( Suit.Diamond + " , " + rankrank[rank-1] );
			}
			if(suit==Suit.Heart)
			{
				System.out.println( Suit.Heart + " , " + rankrank[rank-1] );
			}
			if(suit==Suit.Spade)
			{
				System.out.println( Suit.Spade+ " , " + rankrank[rank-1]);
			}
			
	    }
			
		public  Suit getSuit()
		{
			return suit;
		}
		public int getRank()
		{
			return rank;
		}
	

}

