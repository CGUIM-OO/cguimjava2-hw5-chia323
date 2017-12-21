import java.util.ArrayList;

public class Table {


	public static final int MAXPLAYER = 4;  //玩家人數
	private Deck deck; 
	private Player[] tPlayer;
	private int nDecks;
	private Dealer tbDealer; 
	private ArrayList<Card> playerCard; 
	private int[] pos_betArray = new int[MAXPLAYER] ;

	public void set_player(int pos, Player p)
	{
		
		tPlayer[pos] = p;
		
	}

	public Player[] get_player()
	{
		
	return tPlayer;
	
	}

	public Table(int nDecks)
	{
		
	this.nDecks = nDecks;
	deck = new Deck(nDecks);
	tPlayer = new Player[MAXPLAYER];
	
	}


public void set_dealer(Dealer d)
{
	tbDealer = d;
}

public Card get_face_up_card_of_dealer()
{
	ArrayList<Card>oneRoundCard;
	oneRoundCard = tbDealer.getOneRoundCard();
	return oneRoundCard.get(1);
}

private void ask_each_player_about_bets()
{
	for(int i = 0 ; i <MAXPLAYER ; i++) // 玩家1~4(i)
	{
		tPlayer[i].sayHello(); //玩家i 打招呼+下注
		pos_betArray[i] = tPlayer[i].makeBet(); //pos_betArray把玩家下的賭注存起來
	}
}

private void distribute_cards_to_dealer_and_players()
{
	for(int i=0; i < MAXPLAYER ; i++)
	{
		ArrayList<Card> playerCard = new ArrayList<Card>();
		playerCard.add(deck.getOneCard(true));
		playerCard.add(deck.getOneCard(true));
		tPlayer[i].setOneRoundCard(playerCard);
	}
	
	ArrayList<Card> dealerCard = new ArrayList<Card>();
	dealerCard.add(deck.getOneCard(true));
	dealerCard.add(deck.getOneCard(true));
	tbDealer.setOneRoundCard(dealerCard);
	System.out.print("Dealer's face up card is : ");
	Card c = get_face_up_card_of_dealer();
	c.printCard();
}

private void ask_each_player_about_hits()
{
	for(int i =0 ; i < MAXPLAYER ; i++)
	{
		boolean hit = false; // 攤牌
		
		do
		{
			hit = tPlayer[i].hit_me(this); // 把玩家(i)的
			if(hit)
			{
				
				ArrayList<Card> playerCard = new ArrayList<Card>();
				playerCard = tPlayer[i].getOneRoundCard();
				playerCard.add(deck.getOneCard(true));
				tPlayer[i].setOneRoundCard(playerCard);
				System.out.print("Hit!!");
				System.out.print(tPlayer[i].getName() + "'s Cards now : ");
				
				for(Card c : tPlayer[i].getOneRoundCard())
				{
					c.printCard();
				}
				
			}
			else
			{
				
				System.out.println("Pass hit !! ");
				System.out.println(tPlayer[i].getName() + "'s hit is over !! ");
				System.out.print(tPlayer[i].getName() + " , Card now : ");
				
				for(Card c : tPlayer[i].getOneRoundCard())
				{
					c.printCard();
				}
			}
		}while(hit);
	}
}

private void ask_dealer_about_hits()
{
	boolean hit = false;
	
	do
	{
		
		hit = tbDealer.hit_me(this);
		
		if(hit)
		{
			ArrayList<Card> DealerCard = new ArrayList<Card>();
			DealerCard = tbDealer.getOneRoundCard();
			DealerCard.add(deck.getOneCard(true));
			tbDealer.setOneRoundCard(DealerCard);
			System.out.print("Hit!");
			System.out.print("Dealer's Cards now : ");
			
			for(Card c : tbDealer.getOneRoundCard())
			{
				c.printCard();
			}
		}
		else
		{
			System.out.println("Dealer's hit is over ! ");
			System.out.println("Dealer's Cards now : ");
			
			for(Card c : tbDealer.getOneRoundCard())
			{
				c.printCard();
			}
		}
	}while(hit);
}

private void calculate_chips()
{
	int DealerChip = tbDealer.getTotalValue();
	System.out.print("Dealer's Card value is " + DealerChip + ", Cards : ");
	tbDealer.printAllCard();
	
	for(int i = 0 ; i <MAXPLAYER ; i ++ )
	{
		tPlayer[i].getTotalValue();
		System.out.println(tPlayer[i].getName() + "'s Card : ");
        tPlayer[i].printAllCard();
        System.out.println(tPlayer[i].getName() + "'s Card value is : ");
     
        if(tPlayer[i].getTotalValue()<=21 && tbDealer.getTotalValue()>21)
        {
        	tPlayer[i].increaseChips(pos_betArray[i]);   	
            System.out.print(",Get " + pos_betArray[i] + "Chips, the chips now is : ");
        }
        else if(tPlayer[i].getTotalValue() >21 && tbDealer.getTotalValue() <= 21 )
        {
        	tPlayer[i].increaseChips(-pos_betArray[i]);   	
            System.out.print(",Loss " + pos_betArray[i] + "Chips, the chips now is : ");
        }
        else if(tPlayer[i].getTotalValue() > tbDealer.getTotalValue() && tPlayer[i].getTotalValue() <= 21 )
        {
        	tPlayer[i].increaseChips(pos_betArray[i]);   	
            System.out.print(",Get " + pos_betArray[i] + "Chips, the chips now is : ");
        }
        else if(tPlayer[i].getTotalValue() < tbDealer.getTotalValue() && tbDealer.getTotalValue() <= 21 )
        {
        	tPlayer[i].increaseChips(-pos_betArray[i]);   	
            System.out.print(",Loss " + pos_betArray[i] + "Chips, the chips now is : ");
        }
        else
        {
        	System.out.print(", chip has no change " + pos_betArray[i] + "Chips, the chips now is : ");
        }
        System.out.print(tPlayer[i].getCurrentChips());
	}
}

public int[] get_players_bet()
{
	return pos_betArray;
}

public void play(){
	ask_each_player_about_bets();
	distribute_cards_to_dealer_and_players();
	ask_each_player_about_hits();
	ask_dealer_about_hits();
	calculate_chips();
}
}