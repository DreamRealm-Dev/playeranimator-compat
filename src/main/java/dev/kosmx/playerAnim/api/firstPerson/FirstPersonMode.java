package dev.kosmx.playerAnim.api.firstPerson;

public enum FirstPersonMode {
    NONE,
    VANILLA,
    THIRD_PERSON_MODEL,
    DISABLED;

    public boolean isEnabled() {
        return this != NONE && this != DISABLED;
    }

    public static boolean isFirstPersonPass() {
        return com.zigythebird.playeranimcore.api.firstPerson.FirstPersonMode.isFirstPersonPass();
    }
}
