package itmammoth.releaseanimal;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class PreferenceTest {

    private Context context;

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getTargetContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("releaseanimal", Context.MODE_PRIVATE);
        sharedPreferences
                .edit()
                .putString("last_shown", Constant.FALLBACK_VERSION_NAME)
                .apply();
    }

    @Test
    public void getLastShownVersionName_whenLastShownNotSaved() throws Exception {
        Preference preference = new Preference(context);
        assertEquals(Constant.FALLBACK_VERSION_NAME, preference.getLastShownVersionName());
    }

    @Test
    public void markShown() throws Exception {
        Preference preference = new Preference(context);
        preference.markShown("1.2.3");
        assertEquals("1.2.3", preference.getLastShownVersionName());
    }

    @Test
    public void reset() throws Exception {
        Preference preference = new Preference(context);
        preference.markShown("1.2.3");
        preference.reset();
        assertEquals(Constant.FALLBACK_VERSION_NAME, preference.getLastShownVersionName());
    }
}