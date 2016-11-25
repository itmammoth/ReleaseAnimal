package itmammoth.releaseanimal;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ReleaseNotesManagerTest {

    private Context context;

    @Before
    public void setUp() {
        this.context = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void resetVersion() throws Exception {
        SharedPreferences sharedPreferences = context.getSharedPreferences("releaseanimal", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("latest_shown", "9.9.9.9").apply();

        ReleaseNotesManager.resetVersion(context);
        assertEquals("0.0.0.0", sharedPreferences.getString("last_shown", null));
    }

    @Test
    public void hasUnshownMessages_whenHavingUnshownNotes() throws Exception {
        Preference preference = new Preference(context);
        EssentialPackageInfo packageInfo = EssentialPackageInfo.getInstance(context);
        packageInfo.versionName = "0.0.0.1";
        VersionManager versionManager = new VersionManager("0.0.0.1", "0.0.0.0");
        List<ReleaseNote> notes = new ArrayList<>();
        ReleaseNote note = new ReleaseNote();
        note.versionName = new VersionName("0.0.0.1");
        note.date = new Date();
        notes.add(note);
        ReleaseNotesManager releaseNotesManager = new ReleaseNotesManager(preference, packageInfo, versionManager, notes);
        assertTrue(releaseNotesManager.hasUnshownNotes());
    }

    @Test
    public void hasUnshownMessages_whenNotHavingUnshownNotes() throws Exception {
        Preference preference = new Preference(context);
        EssentialPackageInfo packageInfo = EssentialPackageInfo.getInstance(context);
        packageInfo.versionName = "0.0.0.2";
        VersionManager versionManager = new VersionManager("0.0.0.2", "0.0.0.1");
        List<ReleaseNote> notes = new ArrayList<>();
        ReleaseNote note = new ReleaseNote();
        note.versionName = new VersionName("0.0.0.1");
        note.date = new Date();
        notes.add(note);
        ReleaseNotesManager releaseNotesManager = new ReleaseNotesManager(preference, packageInfo, versionManager, notes);
        assertFalse(releaseNotesManager.hasUnshownNotes());
    }

    @Test
    public void hasUnshownMessages_whenHavingForceNotes() throws Exception {
        Preference preference = new Preference(context);
        EssentialPackageInfo packageInfo = EssentialPackageInfo.getInstance(context);
        packageInfo.versionName = "0.0.0.3";
        VersionManager versionManager = new VersionManager("0.0.0.3", "0.0.0.3");
        List<ReleaseNote> notes = new ArrayList<>();
        ReleaseNote note = new ReleaseNote();
        note.versionName = new VersionName("0.0.0.1");
        note.date = new Date();
        note.force = true;
        notes.add(note);
        ReleaseNotesManager releaseNotesManager = new ReleaseNotesManager(preference, packageInfo, versionManager, notes);
        assertTrue(releaseNotesManager.hasUnshownNotes());
    }

    @Test
    public void getUnshownMessages() throws Exception {
        Preference preference = new Preference(context);
        EssentialPackageInfo packageInfo = EssentialPackageInfo.getInstance(context);
        packageInfo.versionName = "0.0.0.2";
        VersionManager versionManager = new VersionManager("0.0.0.2", "0.0.0.1");
        List<ReleaseNote> notes = new ArrayList<>();
        ReleaseNote note;

        note = new ReleaseNote();
        note.versionName = new VersionName("0.0.0.1");
        note.date = new Date();
        note.messages.add("shown");
        notes.add(note);

        note = new ReleaseNote();
        note.versionName = new VersionName("0.0.0.1");
        note.date = new Date();
        note.force = true;
        note.messages.add("force");
        notes.add(note);

        note = new ReleaseNote();
        note.versionName = new VersionName("0.0.0.2");
        note.date = new Date();
        note.messages.add("not shown");
        notes.add(note);

        ReleaseNotesManager releaseNotesManager = new ReleaseNotesManager(preference, packageInfo, versionManager, notes);
        assertEquals("0.0.0.1\nforce\n\n\n0.0.0.2\nnot shown\n\n\n", releaseNotesManager.getUnshownMessages());
    }

    @Test
    public void markShown() throws Exception {
        Preference preference = new Preference(context);
        EssentialPackageInfo packageInfo = EssentialPackageInfo.getInstance(context);
        packageInfo.versionName = "0.0.0.2";
        VersionManager versionManager = new VersionManager("0.0.0.2", "0.0.0.1");
        List<ReleaseNote> notes = new ArrayList<>();
        ReleaseNote note = new ReleaseNote();
        note.versionName = new VersionName("0.0.0.2");
        note.date = new Date();
        notes.add(note);
        ReleaseNotesManager releaseNotesManager = new ReleaseNotesManager(preference, packageInfo, versionManager, notes);
        releaseNotesManager.markShown();
        assertEquals("0.0.0.2", preference.getLastShownVersionName());
    }
}