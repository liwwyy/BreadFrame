package org.polyfrost.keyframe.standalone.mixin.ornithe;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.polyfrost.keyframe.standalone.playback.ScreenReplay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Screen.class, priority = 1100)
abstract class Mixin_Screen_EditorPreview {
	@Inject(method = {"renderBackground()V", "renderBackground(I)V"}, at = @At("HEAD"), cancellable = true)
	private void keyframe$skipPreviewDimmer(CallbackInfo ci) {
		if (ScreenReplay.getDrawingPreview() == (Object) this && Minecraft.getInstance().world != null) {
			ci.cancel();
		}
	}
}
