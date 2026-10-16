package dev.kosmx.playerAnim.api.layered;

import com.zigythebird.playeranim.animation.PlayerAnimationController;
import com.zigythebird.playeranim.animation.PlayerAnimationData;
import com.zigythebird.playeranimcore.animation.AnimationData;
import com.zigythebird.playeranimcore.animation.AnimationController;
import com.zigythebird.playeranimcore.animation.layered.modifier.AbstractFadeModifier;
import com.zigythebird.playeranimcore.api.firstPerson.FirstPersonConfiguration;
import com.zigythebird.playeranimcore.api.firstPerson.FirstPersonMode;
import com.zigythebird.playeranimcore.bones.PlayerAnimBone;
import com.zigythebird.playeranimcore.easing.EasingType;
import com.zigythebird.playeranimcore.enums.PlayState;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import net.minecraft.client.player.AbstractClientPlayer;

public class KeyframeAnimationPlayer implements IAnimation {
    private final KeyframeAnimation data;
    private int tick;
    private dev.kosmx.playerAnim.api.firstPerson.FirstPersonMode firstPersonMode = dev.kosmx.playerAnim.api.firstPerson.FirstPersonMode.NONE;
    private dev.kosmx.playerAnim.api.firstPerson.FirstPersonConfiguration firstPersonConfiguration = new dev.kosmx.playerAnim.api.firstPerson.FirstPersonConfiguration(true, true, true, true);
    private PlayerAnimationController controller;

    public KeyframeAnimationPlayer(KeyframeAnimation data, int tick, boolean isLoop) {
        this.data = data;
        this.tick = tick;
    }

    public KeyframeAnimationPlayer(KeyframeAnimation data, int tick) {
        this(data, tick, false);
    }

    public KeyframeAnimation getData() {
        return data;
    }

    public int getTick() {
        return tick;
    }

    public KeyframeAnimationPlayer setFirstPersonMode(dev.kosmx.playerAnim.api.firstPerson.FirstPersonMode mode) {
        this.firstPersonMode = mode;
        return this;
    }

    public KeyframeAnimationPlayer setFirstPersonConfiguration(dev.kosmx.playerAnim.api.firstPerson.FirstPersonConfiguration config) {
        this.firstPersonConfiguration = config;
        return this;
    }

    public dev.kosmx.playerAnim.api.firstPerson.FirstPersonMode getFirstPersonMode(float tickDelta) {
        return firstPersonMode;
    }

    public void initController(AbstractClientPlayer player) {
        if (controller != null || data == null || data.getId() == null) {
            return;
        }
        controller = new PlayerAnimationController(player, new AnimationController.AnimationStateHandler() {
            @Override
            public PlayState handle(AnimationController controller, AnimationData animationData, AnimationController.AnimationSetter setter) {
                return PlayState.CONTINUE;
            }
        });
        controller.replaceAnimationWithFade(
            AbstractFadeModifier.standardFadeIn(0, com.zigythebird.playeranimcore.easing.EasingType.LINEAR),
            data.getId(),
            false
        );
    }

    private void initController(AnimationData data) {
        if (controller != null || this.data == null || this.data.getId() == null) {
            return;
        }
        if (data instanceof PlayerAnimationData playerData) {
            initController(playerData.getPlayer());
        }
    }

    @Override
    public boolean isActive() {
        return data != null && (controller == null || controller.isActive());
    }

    @Override
    public void tick(AnimationData data) {
        initController(data);
        if (controller != null) {
            controller.tick(data);
        } else if (isActive()) {
            tick++;
        }
    }

    @Override
    public void setupAnim(AnimationData data) {
        initController(data);
        if (controller != null) {
            controller.setupAnim(data);
        }
    }

    @Override
    public PlayerAnimBone get3DTransform(PlayerAnimBone bone) {
        if (controller != null) {
            return controller.get3DTransform(bone);
        }
        return bone;
    }

    @Override
    public FirstPersonMode getFirstPersonMode() {
        return firstPersonMode == dev.kosmx.playerAnim.api.firstPerson.FirstPersonMode.THIRD_PERSON_MODEL
            ? FirstPersonMode.THIRD_PERSON_MODEL
            : FirstPersonMode.NONE;
    }

    @Override
    public FirstPersonConfiguration getFirstPersonConfiguration() {
        if (controller != null) {
            return controller.getFirstPersonConfiguration();
        }
        return new FirstPersonConfiguration(
            firstPersonConfiguration.isShowRightArm(),
            firstPersonConfiguration.isShowLeftArm(),
            firstPersonConfiguration.isShowRightItem(),
            firstPersonConfiguration.isShowLeftItem()
        );
    }
}
