package wooserk.game;

import java.util.Scanner;

import wooserk.block.Block;
import wooserk.player.Player;

public class Game
{
	public static final int SIZE = 16;
	public static final int SQRTSIZE = 4;
	private Player player;
	private Block[] blocks;
	
	public Game()
	{
		player = new Player();
		blocks = new Block[SIZE];
		for(int i=0; i<SIZE; i++)
		{
			blocks[i] = new Block();
		}
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	public Block[] getBlocks()
	{
		return blocks;
	}
	
	public void setBlocks(Block[] blocks)
	{
		this.blocks = blocks;
	}
	
	public void setGame()
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("당신의 이름은? : ");
		String name = scanner.next();
		player.setName(name);
		
		generate();
		generate();
	}
	
	public void sequence()
	{
		System.out.println(this);
		// 사용자 방향키 입력
		Scanner scanner = new Scanner(System.in);
		boolean result = false;
		boolean rValue = false;
		
		while(!result)
		{
			String str = scanner.next();
			
			switch(str)
			{
			case "w":
			case "a":
			case "s":
			case "d":
				result = true;
				rValue = move(str);
				break;
			default:
				System.out.println("잘못된 입력입니다.");
				break;
			}	
		}
		// 자동 생성(움직인 게 없으면 생성되지 말아야함)
		if(rValue)
		{
			generate();			
		}
	}
	
	public boolean move(String str)
	{
		boolean rValue = false;
		// 2행과 1행 비교, 3행과 2행 비교 후 다시 2행과 1행 비교, 4행과 3행 비교 3행과 2행 비교 2행과 1행 비교
		switch(str)
		{
		case "w":	
			for(int i=0; i<SQRTSIZE; i++) // 열 순서로 고려
			{
				int cnt = SQRTSIZE-1;
				// curBlock이 nextBlock보다 크거나 작아서 
				// nextBlock이 움직이지 못하면 j++; (0,1,2,3 비교에서 1,2,3 비교)
				// curBlock이 0이거나 curBlock이 nextBlock과 같으면
				// cnt--; (0,1,2,3 비교에서 0,1,2 비교<당겨졌으므로>)
				for(int j=0; j<cnt;)
				{
					Block curBlock = blocks[SQRTSIZE*j+i];
					Block nextBlock = blocks[SQRTSIZE*(j+1)+i];
					
					if(curBlock.getValue() == 0)
					{
						for(int k=j+1; k<SQRTSIZE; k++)
						{
							nextBlock = blocks[SQRTSIZE*k+i];
							if(!rValue && nextBlock.getValue() != 0)
							{
								rValue = true;
							}
							curBlock.setValue(nextBlock.getValue());
							nextBlock.setValue(0);
							curBlock = nextBlock;
						}
						cnt--;
					}
					else if(nextBlock.getValue() == 0)
					{
						curBlock = nextBlock;
						for(int k=j+2; k<SQRTSIZE; k++)
						{
							nextBlock = blocks[SQRTSIZE*k+i];
							if(!rValue && nextBlock.getValue() != 0)
							{
								rValue = true;
							}
							curBlock.setValue(nextBlock.getValue());
							nextBlock.setValue(0);
							curBlock = nextBlock;
						}
						cnt--;
					}
					else if(curBlock.getValue() == nextBlock.getValue())
					{
						rValue = true;
						curBlock.setValue(curBlock.getValue() * 2);
						curBlock = nextBlock;
						for(int k=j+2; k<SQRTSIZE; k++)
						{
							nextBlock = blocks[SQRTSIZE*k+i];
							curBlock.setValue(nextBlock.getValue());
							nextBlock.setValue(0);
							curBlock = nextBlock;
						}
						cnt--;
					}
					else
					{
						j++;
					}
				}
			}
			break;
		case "a":
			for(int i=0; i<SQRTSIZE; i++) // 열 순서로 고려
			{
				int cnt = SQRTSIZE-1;
				// curBlock이 nextBlock보다 크거나 작아서 
				// nextBlock이 움직이지 못하면 j++; (0,1,2,3 비교에서 1,2,3 비교)
				// curBlock이 0이거나 curBlock이 nextBlock과 같으면
				// cnt--; (0,1,2,3 비교에서 0,1,2 비교<당겨졌으므로>)
				for(int j=0; j<cnt;)
				{
					Block curBlock = blocks[SQRTSIZE*i+j];
					Block nextBlock = blocks[SQRTSIZE*i+j+1];
					
					if(curBlock.getValue() == 0)
					{
						for(int k=j+1; k<SQRTSIZE; k++)
						{
							nextBlock = blocks[SQRTSIZE*i+k];
							if(!rValue && nextBlock.getValue() != 0)
							{
								rValue = true;
							}
							curBlock.setValue(nextBlock.getValue());
							nextBlock.setValue(0);
							curBlock = nextBlock;
						}
						cnt--;
					}
					else if(nextBlock.getValue() == 0)
					{
						curBlock = nextBlock;
						for(int k=j+2; k<SQRTSIZE; k++)
						{
							nextBlock = blocks[SQRTSIZE*i+k];
							if(!rValue && nextBlock.getValue() != 0)
							{
								rValue = true;
							}
							curBlock.setValue(nextBlock.getValue());
							nextBlock.setValue(0);
							curBlock = nextBlock;
						}
						cnt--;
					}
					else if(curBlock.getValue() == nextBlock.getValue())
					{
						rValue = true;
						curBlock.setValue(curBlock.getValue() * 2);
						curBlock = nextBlock;
						for(int k=j+2; k<SQRTSIZE; k++)
						{
							nextBlock = blocks[SQRTSIZE*i+k];
							curBlock.setValue(nextBlock.getValue());
							nextBlock.setValue(0);
							curBlock = nextBlock;
						}
						cnt--;
					}
					else
					{
						j++;
					}
				}
			}
			break;
		case "s":
			for(int i=0; i<SQRTSIZE; i++) // 열 순서로 고려
			{
				int cnt = 0;
				// curBlock이 nextBlock보다 크거나 작아서 
				// nextBlock이 움직이지 못하면 j--; (0,1,2,3 비교에서 0,1,2 비교)
				// curBlock이 0이거나 curBlock이 nextBlock과 같으면
				// cnt++; (0,1,2,3 비교에서 1,2,3 비교<당겨졌으므로>)
				for(int j=SQRTSIZE-1; j>cnt;)
				{
					Block curBlock = blocks[SQRTSIZE*j+i];
					Block nextBlock = blocks[SQRTSIZE*(j-1)+i];
					
					if(curBlock.getValue() == 0)
					{
						for(int k=j-1; k>=0; k--)
						{
							nextBlock = blocks[SQRTSIZE*k+i];
							if(!rValue && nextBlock.getValue() != 0)
							{
								rValue = true;
							}
							curBlock.setValue(nextBlock.getValue());
							nextBlock.setValue(0);
							curBlock = nextBlock;
						}
						cnt++;
					}
					else if(nextBlock.getValue() == 0)
					{
						curBlock = nextBlock;
						for(int k=j-2; k>=0; k--)
						{
							nextBlock = blocks[SQRTSIZE*k+i];
							if(!rValue && nextBlock.getValue() != 0)
							{
								rValue = true;
							}
							curBlock.setValue(nextBlock.getValue());
							nextBlock.setValue(0);
							curBlock = nextBlock;
						}
						cnt++;
					}
					else if(curBlock.getValue() == nextBlock.getValue())
					{
						rValue = true;
						curBlock.setValue(curBlock.getValue() * 2);
						curBlock = nextBlock;
						for(int k=j-2; k>=0; k--)
						{
							nextBlock = blocks[SQRTSIZE*k+i];
							curBlock.setValue(nextBlock.getValue());
							nextBlock.setValue(0);
							curBlock = nextBlock;
						}
						cnt++;
					}
					else
					{
						j--;
					}
				}
			}
			break;
		case "d":
			for(int i=0; i<SQRTSIZE; i++) // 열 순서로 고려
			{
				int cnt = 0;
				// curBlock이 nextBlock보다 크거나 작아서 
				// nextBlock이 움직이지 못하면 j--; (0,1,2,3 비교에서 0,1,2 비교)
				// curBlock이 0이거나 curBlock이 nextBlock과 같으면
				// cnt++; (0,1,2,3 비교에서 1,2,3 비교<당겨졌으므로>)
				for(int j=SQRTSIZE-1; j>cnt;)
				{
					Block curBlock = blocks[SQRTSIZE*i+j];
					Block nextBlock = blocks[SQRTSIZE*i+j-1];
					
					if(curBlock.getValue() == 0)
					{
						for(int k=j-1; k>=0; k--)
						{
							nextBlock = blocks[SQRTSIZE*i+k];
							if(!rValue && nextBlock.getValue() != 0)
							{
								rValue = true;
							}
							curBlock.setValue(nextBlock.getValue());
							nextBlock.setValue(0);
							curBlock = nextBlock;
						}
						cnt++;
					}
					else if(nextBlock.getValue() == 0)
					{
						curBlock = nextBlock;
						for(int k=j-2; k>=0; k--)
						{
							nextBlock = blocks[SQRTSIZE*i+k];
							if(!rValue && nextBlock.getValue() != 0)
							{
								rValue = true;
							}
							curBlock.setValue(nextBlock.getValue());
							nextBlock.setValue(0);
							curBlock = nextBlock;
						}
						cnt++;
					}
					else if(curBlock.getValue() == nextBlock.getValue())
					{
						rValue = true;
						curBlock.setValue(curBlock.getValue() * 2);
						curBlock = nextBlock;
						for(int k=j-2; k>=0; k--)
						{
							nextBlock = blocks[SQRTSIZE*i+k];
							curBlock.setValue(nextBlock.getValue());
							nextBlock.setValue(0);
							curBlock = nextBlock;
						}
						cnt++;
					}
					else
					{
						j--;
					}
				}
			}
			break;
		}
		return rValue;
	}
	
	public int getTotalValue()
	{
		int value = 0;
		
		for(int i=0; i<SIZE; i++)
		{
			value += blocks[i].getValue();
		}
		
		return value;
	}
	
	public void generate()
	{
		int value;
		
		int rand = (int)(Math.random() * 2);
		boolean result = false;
		
		if(rand == 0)
		{
			value = 2;
		}
		else
		{
			value = 4;
		}
		
		while(!result)
		{
			int i = (int)(Math.random() * 16);
			
			if(blocks[i].getValue() == 0)
			{
				blocks[i].setValue(value);
				result = true;
			}
		}
	}
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append("Player name = " + player.getName() + "\n");
		
		for(int i=0; i<SIZE; i++)
		{
			if(i%4==0)
			{
				sb.append("\n");
			}
			sb.append(" " + blocks[i].getValue() + " ");
		}
		
		sb.append("\n총점 : " + getTotalValue());
		
		return sb.toString();
	}
}
