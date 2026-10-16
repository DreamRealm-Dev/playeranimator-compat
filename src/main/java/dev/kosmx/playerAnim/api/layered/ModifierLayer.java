package dev.kosmx.playerAnim.api.layered;

import dev.kosmx.playerAnim.api.layered.modifier.AbstractFadeModifier;
import dev.kosmx.playerAnim.api.layered.modifier.AbstractModifier;

public class ModifierLayer<T extends IAnimation> extends com.zigythebird.playeranimcore.animation.layered.ModifierLayer<T> implements IAnimation {
    public ModifierLayer(T animation, AbstractModifier... modifiers) {
        super(animation, modifiers);
    }

    public ModifierLayer() {
        super();
    }

    @Override
    public T getAnimation() {
        return super.getAnimation();
    }

    public void setAnimation(T animation) {
        super.setAnimation(animation);
    }

    public void addModifier(AbstractModifier modifier, int index) {
        super.addModifier((com.zigythebird.playeranimcore.animation.layered.modifier.AbstractModifier) modifier, index);
    }

    public void addModifierBefore(AbstractModifier modifier) {
        super.addModifierBefore((com.zigythebird.playeranimcore.animation.layered.modifier.AbstractModifier) modifier);
    }

    public void addModifierLast(AbstractModifier modifier) {
        super.addModifierLast((com.zigythebird.playeranimcore.animation.layered.modifier.AbstractModifier) modifier);
    }

    public void replaceAnimationWithFade(AbstractFadeModifier fade, T animation) {
        // The old fade modifier is not part of the new core; apply the animation directly.
        setAnimation(animation);
    }
}
