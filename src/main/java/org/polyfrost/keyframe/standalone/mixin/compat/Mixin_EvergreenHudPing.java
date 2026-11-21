package org.polyfrost.keyframe.standalone.mixin.compat;

import org.polyfrost.keyframe.standalone.Channels;
import org.polyfrost.oneconfig.api.hud.v1.Hud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Pseudo
@Mixin(targets = "org.polyfrost.evergreenhud.client.hud.PingHud", remap = false)
abstract class Mixin_EvergreenHudPing {
	@ModifyVariable(method = "getText()Ljava/lang/String;", at = @At("STORE"), ordinal = 0, require = 0)
	private Long keyframe$replayPing(Long ping) {
		return ((Hud) (Object) this).isReal() ? Channels.evergreenPing(ping) : ping;
	}
}
