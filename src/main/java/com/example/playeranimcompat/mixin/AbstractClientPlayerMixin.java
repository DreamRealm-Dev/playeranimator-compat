package com.example.playeranimcompat.mixin;

import com.zigythebird.playeranim.api.PlayerAnimationAccess;
import com.zigythebird.playeranim.animation.PlayerAnimManager;
import dev.kosmx.playerAnim.api.layered.AnimationStack;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.impl.IAnimatedPlayer;
import dev.kosmx.playerAnim.impl.animation.AnimationApplier;
import net.minecraft.client.player.AbstractClientPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin implements IAnimatedPlayer {
    @Unique
    private AnimationStack playeranimatorcompat$stack;

    @Override
    public AnimationStack getAnimationStack() {
        if (playeranimatorcompat$stack == null) {
            AbstractClientPlayer self = (AbstractClientPlayer) (Object) this;
            playeranimatorcompat$stack = new AnimationStack(() -> {
                try {
                    return PlayerAnimationAccess.getPlayerAnimManager(self);
                } catch (Exception e) {
                    return new com.zigythebird.playeranimcore.animation.layered.AnimationStack();
                }
            }, animation -> playeranimatorcompat$initAnimation(self, animation));
        }
        return playeranimatorcompat$stack;
    }

    @Unique
    private static void playeranimatorcompat$initAnimation(AbstractClientPlayer player, IAnimation animation) {
        if (animation instanceof KeyframeAnimationPlayer keyframe) {
            keyframe.initController(player);
        } else if (animation instanceof ModifierLayer<?> layer) {
            IAnimation inner = layer.getAnimation();
            if (inner instanceof KeyframeAnimationPlayer keyframe) {
                keyframe.initController(player);
            }
        }
    }

    @Override
    public AnimationApplier playerAnimator_getAnimation() {
        return new AnimationApplier(getAnimationStack());
    }
}
