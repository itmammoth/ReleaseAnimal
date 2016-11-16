package itmammoth.releaseanimal;

import android.support.annotation.NonNull;

import org.apache.maven.artifact.versioning.DefaultArtifactVersion;

import java.util.regex.Pattern;

class VersionName implements Comparable<VersionName> {

    private static final Pattern SEPARATOR = Pattern.compile(".", Pattern.LITERAL);

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
