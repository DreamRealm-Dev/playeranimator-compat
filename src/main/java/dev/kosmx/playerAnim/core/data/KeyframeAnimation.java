package dev.kosmx.playerAnim.core.data;

import dev.kosmx.playerAnim.api.IPlayable;

import java.util.Objects;
import java.util.UUID;
import net.minecraft.resources.ResourceLocation;

public class KeyframeAnimation implements IPlayable {
    public int beginTick;
    public int endTick;
    public int stopTick;

    private final UUID uuid;
    private final com.zigythebird.playeranimcore.animation.Animation newAnimation;
    private final ResourceLocation id;

    public KeyframeAnimation(com.zigythebird.playeranimcore.animation.Animation animation) {
        this(animation, null);
    }

    public KeyframeAnimation(com.zigythebird.playeranimcore.animation.Animation animation, ResourceLocation id) {
        this.newAnimation = animation;
        this.id = id;
        this.uuid = animation != null ? animation.uuid() : UUID.randomUUID();
        if (animation != null) {
            this.endTick = Math.round(animation.length() * 20.0f);
            this.stopTick = this.endTick;
            Object begin = animation.data().getRaw("beginTick"); if (begin instanceof Number n) this.beginTick = n.intValue();
            Object end = animation.data().getRaw("endTick"); if (end instanceof Number n) this.endTick = n.intValue();
            Object stop = animation.data().getRaw("stopTick"); if (stop instanceof Number n) this.stopTick = n.intValue();
        }
    }

    public KeyframeAnimation(KeyframeAnimation source) {
        this.beginTick = source.beginTick;
        this.endTick = source.endTick;
        this.stopTick = source.stopTick;
        this.uuid = source.uuid;
        this.newAnimation = source.newAnimation;
        this.id = source.id;
    }

    public com.zigythebird.playeranimcore.animation.Animation getNewAnimation() {
        return newAnimation;
    }

    public ResourceLocation getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public AnimationBuilder mutableCopy() {
        return new AnimationBuilder(this);
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KeyframeAnimation that)) return false;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    public static class AnimationBuilder {
        public int beginTick;
        public int endTick;
        public int stopTick;

        public final StateCollection rightItem = new StateCollection();
        public final StateCollection leftItem = new StateCollection();
        public final StateCollection head = new StateCollection();
        public final StateCollection torso = new StateCollection();
        public final StateCollection body = new StateCollection();
        public final StateCollection rightArm = new StateCollection();
        public final StateCollection leftArm = new StateCollection();
        public final StateCollection rightLeg = new StateCollection();
        public final StateCollection leftLeg = new StateCollection();

        private final KeyframeAnimation source;

        public AnimationBuilder(KeyframeAnimation source) {
            this.source = source;
            this.beginTick = source.beginTick;
            this.endTick = source.endTick;
            this.stopTick = source.stopTick;
        }

        public KeyframeAnimation build() {
            KeyframeAnimation result = new KeyframeAnimation(source);
            result.beginTick = this.beginTick;
            result.endTick = this.endTick;
            result.stopTick = this.stopTick;
            return result;
        }
    }

    public static class StateCollection {
        public final State pitch = new State();
        public final State roll = new State();
        public final State yaw = new State();
        public final State x = new State();
        public final State y = new State();
        public final State z = new State();

        public boolean isEnabled() {
            return pitch.enabled || roll.enabled || yaw.enabled || x.enabled || y.enabled || z.enabled;
        }

        public void fullyEnablePart(boolean enabled) {
            pitch.setEnabled(enabled);
            roll.setEnabled(enabled);
            yaw.setEnabled(enabled);
            x.setEnabled(enabled);
            y.setEnabled(enabled);
            z.setEnabled(enabled);
        }

        public static class State {
            public boolean enabled;

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public boolean isEnabled() {
                return enabled;
            }
        }
    }
}
