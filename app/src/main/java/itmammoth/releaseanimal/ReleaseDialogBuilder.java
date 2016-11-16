package itmammoth.releaseanimal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

public class ReleaseDialogBuilder extends AlertDialog.Builder {

    private ReleaseNotes releaseNotes;

    public ReleaseDialogBuilder(@NonNull Context context) {
        super(context);
        setDefaultAttributes();
    }

    private void setDefaultAttributes() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") final View layout = inflater.inflate(R.layout.release_dialog, null)
                .findViewById(R.id.release_dialog_container);
        setView(layout);
        setTitle("アップデート情報");
        setCancelable(false);
        setPositiveButton("閉じる", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                releaseNotes.markShown();
            }
        });
    }

    @Override
    public AlertDialog show() {
        releaseNotes = new ReleaseNotes(getContext());
        if (releaseNotes.hasUnshownMessages()) {
            setMessage(releaseNotes.getUnshownMessages());
            return super.show();
        }
        return null;
    }
}

