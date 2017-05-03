package wurmatron.voidrpg.common.cube;

import net.minecraft.block.Block;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import wurmatron.voidrpg.api.cube.ICube;
import wurmatron.voidrpg.common.utils.LogHandler;

public class CubeJson implements ICube {

	private String name;
	private String block;
	private ResourceLocation texture;
	private double weight;
	private int maxDurability;
	private int complexity;
	private int maxAmount;
	private String[] supported;
	private String description;
	private double protection;

	public CubeJson (String name,String block,ResourceLocation texture,double weight,int maxDurability,int complexity,int maxAmount,String[] supported,String description,double protection) {
		this.name = name;
		if (block != null && block.length () > 0 && block.contains (":"))
			this.block = block;
		else
			LogHandler.debug ("Invalid block for json cube found " + name);
		this.texture = texture;
		this.weight = weight;
		this.maxAmount = maxAmount;
		this.complexity = complexity;
		this.maxDurability = maxDurability;
		this.supported = supported;
		this.description = description;
		this.protection = protection;
	}

	@Override
	public String getName () {
		return name;
	}

	@Override
	public Block getBlock () {
		return Block.REGISTRY.getObject (new ResourceLocation (block.substring (0,block.indexOf (":")),block.substring (block.indexOf (":") + 1,block.length ())));
	}

	@Override
	public ResourceLocation getTexture () {
		return texture;
	}

	@Override
	public double getWeight () {
		return weight;
	}

	@Override
	public int getMaxDurability () {
		return maxDurability;
	}

	@Override
	public int getComplexity () {
		return complexity;
	}

	@Override
	public int getMaxAmount (Item item) {
		return maxAmount;
	}

	@Override
	public boolean getSupportedItem (EntityEquipmentSlot slot,Item item) {
		return slot.equals (supported);
	}

	@Override
	public String getDescription () {
		return description;
	}

	@Override
	public boolean hasEffects () {
		return false;
	}

	@Override
	public double getProtectionPercentage (DamageSource source,double amount) {
		return protection;
	}
}
