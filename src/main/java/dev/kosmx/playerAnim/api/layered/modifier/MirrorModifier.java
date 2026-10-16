package dev.kosmx.playerAnim.api.layered.modifier;

public class MirrorModifier extends AbstractModifier {
    public boolean enabled;

    public MirrorModifier() {
        super();
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
