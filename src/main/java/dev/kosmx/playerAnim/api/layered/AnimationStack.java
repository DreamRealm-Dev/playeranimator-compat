package dev.kosmx.playerAnim.api.layered;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Old PlayerAnimator AnimationStack bridge.
 * Lazily resolves the new Player Animation Library stack so it works even if
 * the player's PlayerAnimManager is not available during entity construction.
 */
public class AnimationStack {
    private final Supplier<com.zigythebird.playeranimcore.animation.layered.AnimationStack> delegateSupplier;
    private final Consumer<IAnimation> onAdd;

    public AnimationStack() {
        this(() -> new com.zigythebird.playeranimcore.animation.layered.AnimationStack(), null);
    }

    public AnimationStack(com.zigythebird.playeranimcore.animation.layered.AnimationStack delegate) {
        this(() -> delegate, null);
    }

    public AnimationStack(Supplier<com.zigythebird.playeranimcore.animation.layered.AnimationStack> delegateSupplier) {
        this(delegateSupplier, null);
    }

    public AnimationStack(Supplier<com.zigythebird.playeranimcore.animation.layered.AnimationStack> delegateSupplier, Consumer<IAnimation> onAdd) {
        this.delegateSupplier = delegateSupplier;
        this.onAdd = onAdd;
    }

    private com.zigythebird.playeranimcore.animation.layered.AnimationStack delegate() {
        com.zigythebird.playeranimcore.animation.layered.AnimationStack stack = delegateSupplier.get();
        return stack != null ? stack : new com.zigythebird.playeranimcore.animation.layered.AnimationStack();
    }

    public void addAnimLayer(int priority, IAnimation animation) {
        delegate().addAnimLayer(priority, (com.zigythebird.playeranimcore.animation.layered.IAnimation) animation);
        if (onAdd != null) {
            onAdd.accept(animation);
        }
    }

    public boolean removeLayer(IAnimation animation) {
        return delegate().removeLayer((com.zigythebird.playeranimcore.animation.layered.IAnimation) animation);
    }

    public boolean removeLayer(int priority) {
        return delegate().removeLayer(priority);
    }

    public boolean isActive() {
        return delegate().isActive();
    }

    public void tick(com.zigythebird.playeranimcore.animation.AnimationData data) {
        delegate().tick(data);
    }

    public void setupAnim(com.zigythebird.playeranimcore.animation.AnimationData data) {
        delegate().setupAnim(data);
    }

    public com.zigythebird.playeranimcore.api.firstPerson.FirstPersonMode getFirstPersonMode() {
        return delegate().getFirstPersonMode();
    }

    public com.zigythebird.playeranimcore.api.firstPerson.FirstPersonConfiguration getFirstPersonConfiguration() {
        return delegate().getFirstPersonConfiguration();
    }
}
