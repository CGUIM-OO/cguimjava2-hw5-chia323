import java.util.ArrayList;

public class Player extends Person {

	
	private String name; //玩家姓名
	private int chips; // 玩家所有的籌碼
	private int bet; // 玩家此局下注的籌碼
	
	public Player(String name, int chips)
	{
		this.name = name;
		this.chips = chips;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int makeBet()
	{
		bet = 1;
		if(bet<1)
		return 0;
		else
		return bet;
			
	}
	
	public int getCurrentChips()
	{
		return chips;
	}
	
	public void increaseChips (int diff) 
	{
		chips+=diff;
	}
	
	public void sayHello()
	{
		System.out.println("Hello, I am " + name + "." );
		System.out.println("I have " + chips + " chips." );
	}

	public boolean hit_me(Table table)
	{
		
			int total_value = getTotalValue(); 
			
			if (total_value < 17)
				return true;
			else if (total_value == 17 && hasAce()) 
			{
				return true;
			} 
			else 
			{
				if (total_value >= 21)
					return false;
				else {
					Player[] players = table.get_player();
					int lose_count = 0;
					int v_count = 0;
					int[] betArray = table.get_players_bet();
					for (int i = 0; i < players.length; i++) {
						if (players[i] == null) {
							continue;
						}
						if (players[i].getTotalValue() != 0) {
							if (total_value < players[i].getTotalValue()) {
								lose_count += betArray[i];
							} else if (total_value > players[i].getTotalValue()) {
								v_count += betArray[i];
							}
						}
					}
					if (v_count < lose_count)
						return true;
					else
						return false;
			}
			}	
	}
}
