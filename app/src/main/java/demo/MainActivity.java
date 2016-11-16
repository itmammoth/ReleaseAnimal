package demo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import itmammoth.releaseanimal.R;
import itmammoth.releaseanimal.ReleaseDialogBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setCurrentVersionName();
        setLatestShownVersionName();

        new ReleaseDialogBuilder(this).show();
    }

    private void setCurrentVersionName() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String currentVersion = "Current version: " + packageInfo.versionName;
            ((TextView) findViewById(R.id.txt_current)).setText(currentVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setLatestShownVersionName() {
        SharedPreferences pref = getSharedPreferences("release_animal", Context.MODE_PRIVATE);
        String latestShown = "Latest shown version: " + pref.getString("latest_shown", "0.0.0.0");
        ((TextView) findViewById(R.id.txt_latest_shown)).setText(latestShown);
    }
}
