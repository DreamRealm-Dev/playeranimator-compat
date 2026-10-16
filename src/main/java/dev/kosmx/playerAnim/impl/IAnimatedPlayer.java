package dev.kosmx.playerAnim.impl;

import dev.kosmx.playerAnim.api.layered.AnimationStack;
import dev.kosmx.playerAnim.impl.animation.AnimationApplier;

public interface IAnimatedPlayer {
    AnimationStack getAnimationStack();
    AnimationApplier playerAnimator_getAnimation();
}
