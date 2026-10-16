package dev.kosmx.playerAnim.minecraftApi;

import dev.kosmx.playerAnim.api.IPlayable;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import net.minecraft.resources.ResourceLocation;

public class PlayerAnimationRegistry {
    public static IPlayable getAnimation(ResourceLocation id) {
        com.zigythebird.playeranimcore.animation.Animation anim =
            com.zigythebird.playeranim.animation.PlayerAnimResources.getAnimation(id);
        if (anim == null) {
            return null;
        }
        return new KeyframeAnimation(anim, id);
    }
}
