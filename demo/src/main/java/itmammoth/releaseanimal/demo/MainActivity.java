package itmammoth.releaseanimal.demo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import itmammoth.releaseanimal.ReleaseAnimal;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setCurrentVersionName();
        setLastShownVersionName();

        ReleaseAnimal.resetVersion(this);    // Comment this out if necessary
        new ReleaseAnimal.Builder(this).show();
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

    private void setLastShownVersionName() {
        SharedPreferences pref = getSharedPreferences("releaseanimal", Context.MODE_PRIVATE);
        String lastShown = "Last shown version: " + pref.getString("last_shown", "0.0.0.0");
        ((TextView) findViewById(R.id.txt_latest_shown)).setText(lastShown);
    }
}
