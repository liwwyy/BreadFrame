package org.polyfrost.keyframe.standalone.mixin.compat;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import org.polyfrost.keyframe.standalone.Channels;
import org.polyfrost.oneconfig.api.hud.v1.Hud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

@Pseudo
@Mixin(targets = "org.polyfrost.evergreenhud.client.hud.FpsHud", remap = false)
abstract class Mixin_EvergreenHudFps {
	@ModifyReturnValue(method = "getFps()I", at = @At("RETURN"), require = 0)
	private int keyframe$recordedFps(int fps) {
		return ((Hud) (Object) this).isReal() ? Channels.evergreenFps(fps) : fps;
	}
}
