package org.polyfrost.keyframe.standalone.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.polyfrost.keyframe.standalone.export.Export;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

@Pseudo
@Mixin(targets = "org.polyfrost.polyblur.client.blur.FrameClock", remap = false)
abstract class Mixin_FrameClock_PolyBlur {
	@ModifyExpressionValue(method = "tick", at = @At(value = "INVOKE", target = "Ljava/lang/System;nanoTime()J"), require = 0)
	private static long keyframe$exportClock(long nanos) {
		return Export.nanos();
	}
}
