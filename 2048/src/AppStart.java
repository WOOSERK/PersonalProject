import java.util.Scanner;

import wooserk.block.Block;
import wooserk.game.Game;
import wooserk.player.Player;

public class AppStart
{
	public static void main(String[] args)
	{
		Game game = new Game();
		
		game.setGame();
		
		while(true)
		{
			game.sequence();			
		}
	}
}
