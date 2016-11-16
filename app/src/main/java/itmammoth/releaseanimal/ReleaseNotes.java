package itmammoth.releaseanimal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class ReleaseNotes {

    private static final String PREF_KEY = "release_animal";
    private static final String PREF_V_KEY_LATEST_SHOWN = "latest_shown";

    private final Context context;
    private final SharedPreferences pref;
    private final PackageInfo packageInfo;
    private final VersionName currentVersionName;
    private final VersionName latestShownVersionName;
    private final List<ReleaseNote> notes;

    ReleaseNotes(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        packageInfo = getPackageInfo();
        currentVersionName = new VersionName(packageInfo.versionName);
        latestShownVersionName = new VersionName(pref.getString(PREF_V_KEY_LATEST_SHOWN, "0.0.0.0"));
        notes = new Parser(context.getResources().getXml(R.xml.release_animal)).parse();
        Log.d(Constant.LOG_TAG, notes.toString());
    }

    /**
     * Returns whether there are any messages that haven't been shown yet.
     * @return whether there are or not
     */
    boolean hasUnshownMessages() {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            VersionName current = new VersionName(packageInfo.versionName);
            if (current.greaterThan(latestShownVersionName)) {
                return hasUnshownNotes();
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(Constant.LOG_TAG, e.getLocalizedMessage());
        }
        return false;
    }

    /**
     * Returns messages that haven't shown yet
     * @return Not shown messages
     */
    String getUnshownMessages() {
        StringBuilder messages = new StringBuilder();
        for (ReleaseNote releaseNote : getUnshownNotes()) {
            messages.append(releaseNote.versionName.toString());
            messages.append("\n");
            for (String m : releaseNote.messages) {
                messages.append(m);
                messages.append("\n");
            }
            messages.append("\n\n");
        }
        return messages.toString();
    }

    /**
     * Marks shown on release note messages whose version name is less than or equal to current one.
     */
    void markShown() {
        List<ReleaseNote> unshownNotes = getUnshownNotes();
        VersionName newLatestShownVersionName = unshownNotes.get(unshownNotes.size() - 1).versionName;
        pref.edit()
            .putString(PREF_V_KEY_LATEST_SHOWN, newLatestShownVersionName.toString())
            .apply();
    }

    private PackageInfo getPackageInfo() {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(Constant.LOG_TAG, e.getLocalizedMessage());
        }
        return null;
    }

    private List<ReleaseNote> getUnshownNotes() {
        List<ReleaseNote> notShownNotes = new ArrayList<>();
        for (ReleaseNote note : notes) {
            if (needToShow(note)) {
                notShownNotes.add(note);
            }
        }
        return notShownNotes;
    }

    private boolean hasUnshownNotes() {
        return getUnshownNotes().size() > 0;
    }

    private boolean needToShow(ReleaseNote note) {
        return      note.versionName.greaterThan(latestShownVersionName)
                &&  currentVersionName.greaterThanOrEqualTo(note.versionName)
                &&  note.date.compareTo(getFirstInstallDate()) >= 0
                &&  note.date.compareTo(new Date()) <= 0;
    }

    private Date getFirstInstallDate() {
        Date d = new Date(packageInfo.firstInstallTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-DD");
        try {
            return sdf.parse(sdf.format(d));
        } catch (ParseException e) {
            Log.e(Constant.LOG_TAG, e.getLocalizedMessage());
        }
        return null;
    }
}
