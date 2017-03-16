package panda.glassworks.util.events;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import panda.glassworks.util.registry.MasterRegistrar;
import panda.glassworks.util.render.ItemModelPerspective;

public class ItemModelHandler {
	@SubscribeEvent
	public void modelBakeEvent(ModelBakeEvent event) {

		IBakedModel model2d = event.getModelRegistry().getObject(MasterRegistrar.model2d);
		IBakedModel model3d = event.getModelRegistry().getObject(MasterRegistrar.model3d);

		ItemModelPerspective customModel = new ItemModelPerspective(model2d, model3d);

		event.getModelRegistry().putObject(MasterRegistrar.model2d, customModel);

	}

}
