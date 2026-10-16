package dev.kosmx.playerAnim.api.layered.modifier;

import com.zigythebird.playeranimcore.animation.AnimationData;

/**
 * Legacy PlayerAnimator SpeedModifier bridge.
 *
 * Better Combat's TransmissionSpeedModifier extends this class and writes the
 * public {@code speed} field through the old API. The new Player Animation
 * Library calls {@link #tick(AnimationData)} / {@link #setupAnim(AnimationData)}
 * on the modifier stack, so this bridge must apply {@code speed} to the
 * animation timeline there. Without this, the swing tick and the animation tick
 * drift apart (Better Combat attacks feel half a beat late).
 */
public class SpeedModifier extends AbstractModifier {
    public float speed = 1.0f;

    private float delta;
    private float shiftedDelta;

    public SpeedModifier() {
        super();
    }

    // Legacy methods used by old Better Combat code. The new library drives the
    // actual animation timing through the AnimationData overloads below.
    public void tick() {
    }

    public void setupAnim(float tickDelta) {
    }

    @Override
    public void tick(AnimationData data) {
        float step = 1.0F - this.delta;
        this.delta = 0.0F;
        this.step(step, data);
    }

    @Override
    public void setupAnim(AnimationData data) {
        float partialTick = data.getPartialTick();
        float step = partialTick - this.delta;
        this.delta = partialTick;
        this.step(step, data);
        super.setupAnim(data);
    }

    private void step(float delta, AnimationData data) {
        delta *= this.speed;
        delta += this.shiftedDelta;

        while (delta > 1.0F) {
            delta -= 1.0F;
            super.tick(data);
        }

        data.setPartialTick(delta);
        this.shiftedDelta = delta;
    }
}
