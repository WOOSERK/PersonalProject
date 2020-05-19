package wooserk.block;

public class Block
{
	private static int count = 1;
	private int index;
	private int value;
	
	public Block() 
	{
		index = count++;
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public void setIndex(int index)
	{
		this.index = index;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void setValue(int value)
	{
		this.value = value;
	}
}
