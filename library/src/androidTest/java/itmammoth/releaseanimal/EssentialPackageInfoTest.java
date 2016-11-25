package itmammoth.releaseanimal;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class EssentialPackageInfoTest {

    private Context context;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void getInstance() throws Exception {
        EssentialPackageInfo essentialPackageInfo = EssentialPackageInfo.getInstance(context);
        assertTrue(essentialPackageInfo != null);
        // PackageInfo#versionName always returns null in test environment...
        assertNull(essentialPackageInfo.versionName);
        assertNotNull(essentialPackageInfo.firstInstallDate);
    }
}