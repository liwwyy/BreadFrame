package org.polyfrost.keyframe.standalone.mixin.ornithe;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.screens.Screen;
import org.polyfrost.keyframe.standalone.playback.ScreenReplay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

@Pseudo
@Mixin(targets = "eu.midnightdust.blur.BlurRenderer", remap = false)
abstract class Mixin_LegacyGuiBlur_EditorPreview {
	// Legacy GUI Blur is an optional, already remapped target in a production Ornithe client.
	private static final String NAMED_SCREEN = "Lnet/minecraft/client/Minecraft;screen:Lnet/minecraft/client/gui/screens/Screen;";
	private static final String INTERMEDIARY_SCREEN = "Lnet/minecraft/client/Minecraft;f_70816363:Lnet/minecraft/unmapped/C_85740840;";

	@ModifyExpressionValue(method = "render()V", at = {
		@At(value = "FIELD", target = NAMED_SCREEN, remap = false),
		@At(value = "FIELD", target = INTERMEDIARY_SCREEN, remap = false),
	}, require = 0)
	private static Screen keyframe$previewBlurTarget(Screen screen) {
		return ScreenReplay.screenForPreviewBackground(screen);
	}

	@ModifyExpressionValue(method = "renderBackground", at = {
		@At(value = "FIELD", target = NAMED_SCREEN, remap = false),
		@At(value = "FIELD", target = INTERMEDIARY_SCREEN, remap = false),
	}, require = 0)
	private static Screen keyframe$previewBackgroundTarget(Screen screen) {
		Screen preview = ScreenReplay.getDrawingPreview();
		return preview != null ? preview : screen;
	}
}
