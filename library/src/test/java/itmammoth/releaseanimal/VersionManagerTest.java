package itmammoth.releaseanimal;

import org.junit.Test;

import static org.junit.Assert.*;

public class VersionManagerTest {

    @Test
    public void needToShow_withUpdatedVersion() throws Exception {
        VersionManager versionManager = new VersionManager("1.0.1", "1.0.0");
        assertTrue(versionManager.needToShow(new VersionName("1.0.1")));
    }

    @Test
    public void needToShow_withShownVersion() throws Exception {
        VersionManager versionManager = new VersionManager("1.0.1", "1.0.1");
        assertFalse(versionManager.needToShow(new VersionName("1.0.1")));
    }

    @Test
    public void versionUpdated_whenCurrentIsNewerThanLastShown() throws Exception {
        VersionManager versionManager = new VersionManager("1.0.1", "1.0.0");
        assertTrue(versionManager.versionUpdated());
    }

    @Test
    public void versionUpdated_whenCurrentIsTheSameAsLastShown() throws Exception {
        VersionManager versionManager = new VersionManager("1.0.1", "1.0.1");
        assertFalse(versionManager.versionUpdated());
    }
}