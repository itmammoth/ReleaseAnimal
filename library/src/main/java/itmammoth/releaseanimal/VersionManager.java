package itmammoth.releaseanimal;

class VersionManager {

    private final VersionName current;
    private final VersionName lastShown;

    VersionManager(String current, String lastShown) {
        this.current = new VersionName(current != null ? current : Constant.FALLBACK_VERSION_NAME);
        this.lastShown = new VersionName(lastShown != null ? lastShown : Constant.FALLBACK_VERSION_NAME);
    }

    boolean needToShow(VersionName target) {
        return target.greaterThan(lastShown) && current.greaterThanOrEqualTo(target);
    }

    boolean versionUpdated() {
        return current.greaterThan(lastShown);
    }
}
