package panda.glassworks.items;

public class EnchItemBase extends ItemBase{
	public EnchItemBase(String name)
    {
    	super(name);
    }
	
	 @Override 
	 public int getItemEnchantability()
	 {
	    	return 35;
	 }
		 
}
