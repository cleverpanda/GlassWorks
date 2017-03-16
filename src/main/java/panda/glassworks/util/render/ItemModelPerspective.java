package panda.glassworks.util.render;

import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.IPerspectiveAwareModel;

public class ItemModelPerspective implements IPerspectiveAwareModel {
	public final IBakedModel Model2d, Model3d;
	// private final ItemCameraTransforms cameraTransforms;
	// private final ItemOverrideList overrideList;

	// public static final ModelResourceLocation MODEL2D = new
	// ModelResourceLocation(GlassWorks.MODID + ":" + "blowpipe2d",
	// "inventory");
	// public static final ModelResourceLocation MODEL3D = new
	// ModelResourceLocation(GlassWorks.MODID + ":" + "blowpipe3d",
	// "inventory");

	public ItemModelPerspective(IBakedModel model2d, IBakedModel model3d) {
		super();
		Model2d = model2d;
		Model3d = model3d;
		// this.cameraTransforms = ItemCameraTransforms.DEFAULT;
		// this.overrideList = ItemOverrideList.NONE;
	}

	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(TransformType cameraTransformType) {

		Matrix4f transform = null;

		switch (cameraTransformType) {

		case GUI:
			if (Model2d instanceof IPerspectiveAwareModel) {
				Pair<? extends IBakedModel, Matrix4f> result = ((IPerspectiveAwareModel) Model2d)
						.handlePerspective(cameraTransformType);
				return result;
			}
			return Pair.of(this.Model2d, transform);

		case GROUND:
			if (Model3d instanceof IPerspectiveAwareModel) {
				Pair<? extends IBakedModel, Matrix4f> result = ((IPerspectiveAwareModel) Model3d)
						.handlePerspective(cameraTransformType);
				return result;
			}
			return Pair.of(this.Model3d, transform);

		default:
			if (Model3d instanceof IPerspectiveAwareModel) {
				Pair<? extends IBakedModel, Matrix4f> result = ((IPerspectiveAwareModel) Model3d)
						.handlePerspective(cameraTransformType);
				return result;
			}
			// ItemCameraTransforms.offsetRotationX

			return Pair.of(this.Model3d, transform);
		}
	}

	@Override
	public boolean isAmbientOcclusion() {
		return false;
	}

	@Override
	public boolean isGui3d() {
		return false;
	}

	@Override
	public boolean isBuiltInRenderer() {
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return null;
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms() {
		return ItemCameraTransforms.DEFAULT;
	}

	@Override
	public ItemOverrideList getOverrides() {
		return ItemOverrideList.NONE;
	}

	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		return null;
	}
}
