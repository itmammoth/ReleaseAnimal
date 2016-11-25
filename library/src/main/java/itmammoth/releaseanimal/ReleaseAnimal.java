package itmammoth.releaseanimal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

public class ReleaseAnimal {

    /**
     * Reset latest shown version stored in shared preference.
     * @param context Context
     */
    public static void resetVersion(@NonNull Context context) {
        ReleaseNotesManager.resetVersion(context);
    }

    public static class Builder extends AlertDialog.Builder {

        private final Context context;
        private ReleaseNotesManager releaseNotesManager;

        public Builder(@NonNull Context context) {
            super(context);
            this.context = context;
            setDefaultAttributes();
        }

        private void setDefaultAttributes() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams")
            final View layout = inflater.inflate(R.layout.release_dialog, null)
                    .findViewById(R.id.release_dialog_container);
            setView(layout);
            setTitle(R.string.releaseanimal_dialog_title);
            setCancelable(false);
            setPositiveButton(R.string.releaseanimal_close, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    releaseNotesManager.markShown();
                }
            });
        }

        @Override
        @Nullable
        public AlertDialog show() {
            releaseNotesManager = new ReleaseNotesManager(context);
            if (releaseNotesManager.hasUnshownNotes()) {
                setMessage(releaseNotesManager.getUnshownMessages());
                return super.show();
            }
            return null;
        }
    }
}
