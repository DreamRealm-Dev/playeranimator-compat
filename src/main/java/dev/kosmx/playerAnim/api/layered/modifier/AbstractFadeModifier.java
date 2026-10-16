package dev.kosmx.playerAnim.api.layered.modifier;

import dev.kosmx.playerAnim.core.util.Ease;

public class AbstractFadeModifier extends AbstractModifier {
    private final int length;
    private final Ease ease;

    protected AbstractFadeModifier(int length, Ease ease) {
        this.length = length;
        this.ease = ease;
    }

    public static AbstractFadeModifier standardFadeIn(int length, Ease ease) {
        return new AbstractFadeModifier(length, ease);
    }

    public int getLength() {
        return length;
    }

    public Ease getEase() {
        return ease;
    }
}
