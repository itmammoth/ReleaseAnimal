package itmammoth.releaseanimal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class ReleaseNotes {

    private final Preference pref;
    private final EssentialPackageInfo packageInfo;
    private final VersionManager versionManager;
    private final List<ReleaseNote> notes;

    ReleaseNotes(@NonNull Context context) {
        pref = new Preference(context);
        packageInfo = EssentialPackageInfo.getInstance(context);
        versionManager = new VersionManager(packageInfo.versionName, pref.getLastShownVersionName());
        notes = new Parser(context.getResources().getXml(R.xml.releaseanimal)).parse();
        Log.d(Constant.LOG_TAG, notes.toString());
    }

    /**
     * Reset the last shown version in the shared preferences.
     * @param context Context
     */
    static void resetVersion(@NonNull Context context) {
        new Preference(context).reset();
    }

    /**
     * Returns whether there are any messages that haven't been shown yet.
     * @return whether there are or not
     */
    boolean hasUnshownMessages() {
        return versionManager.versionUpdated() && hasUnshownNotes();
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
        pref.markShown(newLatestShownVersionName.toString());
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
        return      versionManager.needToShow(note.versionName)
                &&  note.date.compareTo(packageInfo.firstInstallDate) >= 0
                &&  note.date.compareTo(new Date()) <= 0;
    }
}
