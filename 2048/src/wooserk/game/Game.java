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
		
		System.out.print("����� �̸���? : ");
		String name = scanner.next();
		player.setName(name);
		
		generate();
		generate();
	}
	
	public void sequence()
	{
		System.out.println(this);
		// ����� ����Ű �Է�
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
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
			}	
		}
		// �ڵ� ����(������ �� ������ �������� ���ƾ���)
		if(rValue)
		{
			generate();			
		}
	}
	
	public boolean move(String str)
	{
		boolean rValue = false;
		// 2��� 1�� ��, 3��� 2�� �� �� �ٽ� 2��� 1�� ��, 4��� 3�� �� 3��� 2�� �� 2��� 1�� ��
		switch(str)
		{
		case "w":	
			for(int i=0; i<SQRTSIZE; i++) // �� ������ ���
			{
				int cnt = SQRTSIZE-1;
				// curBlock�� nextBlock���� ũ�ų� �۾Ƽ� 
				// nextBlock�� �������� ���ϸ� j++; (0,1,2,3 �񱳿��� 1,2,3 ��)
				// curBlock�� 0�̰ų� curBlock�� nextBlock�� ������
				// cnt--; (0,1,2,3 �񱳿��� 0,1,2 ��<��������Ƿ�>)
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
			for(int i=0; i<SQRTSIZE; i++) // �� ������ ���
			{
				int cnt = SQRTSIZE-1;
				// curBlock�� nextBlock���� ũ�ų� �۾Ƽ� 
				// nextBlock�� �������� ���ϸ� j++; (0,1,2,3 �񱳿��� 1,2,3 ��)
				// curBlock�� 0�̰ų� curBlock�� nextBlock�� ������
				// cnt--; (0,1,2,3 �񱳿��� 0,1,2 ��<��������Ƿ�>)
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
			for(int i=0; i<SQRTSIZE; i++) // �� ������ ���
			{
				int cnt = 0;
				// curBlock�� nextBlock���� ũ�ų� �۾Ƽ� 
				// nextBlock�� �������� ���ϸ� j--; (0,1,2,3 �񱳿��� 0,1,2 ��)
				// curBlock�� 0�̰ų� curBlock�� nextBlock�� ������
				// cnt++; (0,1,2,3 �񱳿��� 1,2,3 ��<��������Ƿ�>)
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
			for(int i=0; i<SQRTSIZE; i++) // �� ������ ���
			{
				int cnt = 0;
				// curBlock�� nextBlock���� ũ�ų� �۾Ƽ� 
				// nextBlock�� �������� ���ϸ� j--; (0,1,2,3 �񱳿��� 0,1,2 ��)
				// curBlock�� 0�̰ų� curBlock�� nextBlock�� ������
				// cnt++; (0,1,2,3 �񱳿��� 1,2,3 ��<��������Ƿ�>)
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
		
		sb.append("\n���� : " + getTotalValue());
		
		return sb.toString();
	}
}
