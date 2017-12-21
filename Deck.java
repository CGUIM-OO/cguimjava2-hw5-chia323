import java.util.ArrayList;
import java.util.Random;


	public class Deck 
	{
		

		private ArrayList<Card> cards; //宣告ArrayList<Card> cards  private filed
		public ArrayList<Card> usedCards = new ArrayList<Card>() ; // 宣告useCards instance field 並實體化
		private ArrayList<Card> openCard;
		public int nUsed; // 宣告nUsed instance field constructor:nDeck 
		
			 
			public Deck(int nDeck){
	
				cards = new ArrayList<Card>();
				usedCards = new ArrayList<Card>(); 
				openCard = new ArrayList<Card>();
	//1 Deck have 52 cards, https://en.wikipedia.org/wiki/Poker
	//Hint: Use new Card(x,y) and 3 for loops to add card into deck
	//Sample code start
	//Card card=new Card(1,1); ->means new card as clubs ace
	//cards.add(card);
	//Sample code end
	
				for( int i = 1 ; i <= nDeck ; i++ ) // 要給幾(nDeck)副牌 
				{
					for( int x = 1 ; x <= 4 ; x++ ) //花色(Clubs=1, Diamonds=2, Hearts=3, Spades=4)
					{
						for( int k = 1 ;  k <= 13 ; k++ ) //牌上面的數(1~13)
						{
		    		
		    		
							if( x == 1 ) //當花色為club
							{
								Card card = new Card ( Card.Suit.Club , k);  //new 一個 clubs clubs 在按照順序從1排到13 
								cards.add(card); // 把clubs 花色印出來
							}
							if( x == 2 ) //當花色為Diamond
							{
								Card card = new Card ( Card.Suit.Diamond , k); // new 一個 diamond 並按照順序從1排到13
								cards.add(card); // 把Diamond 花色印出來
							}
							if( x == 3 ) //當花色為Heart
							{
								Card card = new Card ( Card.Suit.Heart , k); // new 一個 heart 並按照順序從1排到13
								cards.add(card); // 把Heart 花色印出來
							}
							if( x == 4 ) //當花色為Spade
							{
								Card card = new Card ( Card.Suit.Spade , k); // new 一個 spade 並按照順序從1排到13
								cards.add(card); // 把Spade 花色印出來
							}
		    	
		    		 
						}
					}
				}
				shuffle();
	}	

	//TODO: Please implement the method to print all cards on screen (10 points)

	public void printDeck()
    {
	//Hint: print all items in ArrayList<Card> cards, 
	//TODO: please implement and reuse printCard method in Card class (5 points)
	
		for (int n = 0 ; n < cards.size() ; n++)
		{
		 cards.get(n).printCard(); //
		}
	
    }

	public ArrayList<Card> getAllCards()
	{
	 return cards;
    }

	public Card getOneCard(boolean isOpened)
    {
	
		if (cards.size()==0)
		{
		shuffle();
		if(isOpened)  //用過的牌要加在打開的排裡面
		{
			openCard.add(cards.get(nUsed));
		}
		
		}
	
	Card drawer = cards.get(0);
	cards.remove(0);
	usedCards.add(drawer);
	nUsed+=1;
	return drawer;
	
    }

	public void shuffle() //把發出去的牌(useCards)收回去
	{
		
		for (int i = 0 ; i < nUsed ; i++ )
		cards.add(usedCards.get(i));
		
		for(int i = 0 ; i<1000 ; i ++ )//要洗幾次牌
		{

			Random md = new Random(); // 隨機抽取
			int j = md.nextInt(cards.size()); // 抽取的數字不能超過(card.size不超過的數字) J 那個是任意位置
			Card temp = cards.get(j);  //把J放進去temp裡面
			cards.remove(cards.get(j)); //把j那個牌雲本的位置除掉
			cards.add(temp); //把temp那個牌放進去原本被抽出來的位置
		}
		
		nUsed = 0; //
		usedCards.clear(); //
		openCard.clear();
	}

	public ArrayList<Card> getOpenedCard()
	{
		return openCard;
	}
	
	
	
}

