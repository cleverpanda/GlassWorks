package panda.glassworks.util.registry;

import java.util.Map;

import javax.annotation.Nonnull;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;

public interface IMeta {
	
	
	@Nonnull public int getMaxMeta();
	@Nonnull public Map<Integer, ModelResourceLocation> getMetaModelLocations();
	
}
