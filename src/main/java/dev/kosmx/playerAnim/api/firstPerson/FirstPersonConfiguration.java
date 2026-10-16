package dev.kosmx.playerAnim.api.firstPerson;

public class FirstPersonConfiguration {
    private boolean showRightArm;
    private boolean showLeftArm;
    private boolean showRightItem;
    private boolean showLeftItem;

    public FirstPersonConfiguration() {
        this(true, true, true, true);
    }

    public FirstPersonConfiguration(boolean showRightArm, boolean showLeftArm, boolean showRightItem, boolean showLeftItem) {
        this.showRightArm = showRightArm;
        this.showLeftArm = showLeftArm;
        this.showRightItem = showRightItem;
        this.showLeftItem = showLeftItem;
    }

    public boolean isShowRightArm() { return showRightArm; }
    public boolean isShowLeftArm() { return showLeftArm; }
    public boolean isShowRightItem() { return showRightItem; }
    public boolean isShowLeftItem() { return showLeftItem; }

    public FirstPersonConfiguration setShowRightArm(boolean v) { this.showRightArm = v; return this; }
    public FirstPersonConfiguration setShowLeftArm(boolean v) { this.showLeftArm = v; return this; }
    public FirstPersonConfiguration setShowRightItem(boolean v) { this.showRightItem = v; return this; }
    public FirstPersonConfiguration setShowLeftItem(boolean v) { this.showLeftItem = v; return this; }
}
