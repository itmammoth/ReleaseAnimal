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
public class ReleaseNotesTest {

    private Context context;

    @Before
    public void setUp() {
        this.context = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void resetVersion() throws Exception {
        SharedPreferences sharedPreferences = context.getSharedPreferences("releaseanimal", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("latest_shown", "9.9.9.9").apply();

        ReleaseNotes.resetVersion(context);
        assertEquals("0.0.0.0", sharedPreferences.getString("last_shown", null));
    }
}