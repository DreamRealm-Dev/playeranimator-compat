package dev.kosmx.playerAnim.api.layered.modifier;

import dev.kosmx.playerAnim.api.TransformType;
import dev.kosmx.playerAnim.core.util.Vec3f;

import java.util.Optional;
import java.util.function.Function;

public class AdjustmentModifier extends AbstractModifier {
    protected Function<String, Optional<PartModifier>> source;

    public AdjustmentModifier(Function<String, Optional<PartModifier>> source) {
        super();
        this.source = source;
    }

    public void fadeOut(int length) {
    }

    protected Vec3f transformVector(Vec3f value, TransformType type, PartModifier part, float tickDelta) {
        if (type == TransformType.POSITION && part.offset() != null) {
            return value.add(part.offset());
        } else if (type == TransformType.ROTATION && part.rotation() != null) {
            return value.add(part.rotation());
        }
        return value;
    }

    public static class PartModifier {
        private final Vec3f offset;
        private final Vec3f rotation;

        public PartModifier(Vec3f offset, Vec3f rotation) {
            this.offset = offset;
            this.rotation = rotation;
        }

        public Vec3f offset() {
            return offset;
        }

        public Vec3f rotation() {
            return rotation;
        }
    }
}
