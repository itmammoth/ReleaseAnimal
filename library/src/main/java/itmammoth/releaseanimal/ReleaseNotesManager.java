package itmammoth.releaseanimal;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class ReleaseNotesManager {

    private final Preference preference;
    private final EssentialPackageInfo packageInfo;
    private final VersionManager versionManager;
    private final List<ReleaseNote> notes;

    ReleaseNotesManager(@NonNull Context context) {
        preference = new Preference(context);
        packageInfo = EssentialPackageInfo.getInstance(context);
        versionManager = new VersionManager(packageInfo.versionName, preference.getLastShownVersionName());
        notes = new Parser(context.getResources().getXml(R.xml.releaseanimal)).parse();
    }

    ReleaseNotesManager(
            @NonNull Preference preference,
            @NonNull EssentialPackageInfo packageInfo,
            @NonNull VersionManager versionManager,
            @NonNull List<ReleaseNote> notes) {
        this.preference = preference;
        this.packageInfo = packageInfo;
        this.versionManager = versionManager;
        this.notes = notes;
    }

    /**
     * Reset the last shown version in the shared preferences.
     * @param context Context
     */
    static void resetVersion(@NonNull Context context) {
        new Preference(context).reset();
    }

    /**
     * Returns whether there are any notes that haven not been shown yet.
     * @return whether there are or not
     */
    boolean hasUnshownNotes() {
        return hasForceNotes() || (versionManager.versionUpdated() && getUnshownNotes().size() > 0);
    }

    /**
     * Returns messages that haven not shown yet
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
        preference.markShown(newLatestShownVersionName.toString());
    }

    private boolean hasForceNotes() {
        for (ReleaseNote note : notes) {
            if (note.force) return true;
        }
        return false;
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

    private boolean needToShow(ReleaseNote note) {
        return note.force || (
                versionManager.needToShow(note.versionName)
            &&  note.date.compareTo(packageInfo.firstInstallDate) >= 0
            &&  note.date.compareTo(new Date()) <= 0
        );
    }
}
