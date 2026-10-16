package dev.kosmx.playerAnim.impl.animation;

import dev.kosmx.playerAnim.api.layered.AnimationStack;

public class AnimationApplier {
    private final AnimationStack stack;

    public AnimationApplier() {
        this(null);
    }

    public AnimationApplier(AnimationStack stack) {
        this.stack = stack;
    }

    public boolean isActive() {
        return stack != null && stack.isActive();
    }
}
