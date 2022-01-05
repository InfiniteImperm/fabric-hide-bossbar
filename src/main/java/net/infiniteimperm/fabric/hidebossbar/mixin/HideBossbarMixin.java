package net.infiniteimperm.fabric.hidebossbar.mixin;

import net.infiniteimperm.fabric.hidebossbar.HideBossbar;
import net.minecraft.client.gui.hud.BossBarHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BossBarHud.class)
public class HideBossbarMixin {
    @Inject(at = @At("HEAD"), method = "render", cancellable = true)
    private void hideRender(MatrixStack matrices, CallbackInfo callbackInfo) {
        if (HideBossbar.getInstance().isHideBossbar())
            callbackInfo.cancel();
    }
}
