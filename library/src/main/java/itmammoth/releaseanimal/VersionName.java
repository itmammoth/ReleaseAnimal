package itmammoth.releaseanimal;

import android.support.annotation.NonNull;

import org.apache.maven.artifact.versioning.DefaultArtifactVersion;

class VersionName implements Comparable<VersionName> {

    private final String value;

    VersionName(String versionName) {
        value = versionName;
    }

    @Override
    public int compareTo(@NonNull VersionName that) {
        DefaultArtifactVersion thisVersion = new DefaultArtifactVersion(value);
        DefaultArtifactVersion thatVersion = new DefaultArtifactVersion(that.value);
        return thisVersion.compareTo(thatVersion);
    }

    @Override
    public boolean equals(Object that) {
        if (that instanceof VersionName) {
            return value.equals(((VersionName) that).value);
        }
        return super.equals(that);
    }

    boolean greaterThan(VersionName other) {
        return compareTo(other) > 0;
    }

    boolean greaterThanOrEqualTo(VersionName other) {
        return compareTo(other) >= 0;
    }

    @Override
    public String toString() {
        return value;
    }
}
