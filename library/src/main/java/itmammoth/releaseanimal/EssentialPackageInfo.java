package itmammoth.releaseanimal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.util.Log;

import java.text.ParseException;
import java.util.Date;

class EssentialPackageInfo {

    private static EssentialPackageInfo instance;

    String versionName;
    Date firstInstallDate;

    static EssentialPackageInfo getInstance(@NonNull Context context) {
        if (instance == null) {
            instance = new EssentialPackageInfo();
            try {
                PackageInfo packageInfo =
                        context.getPackageManager()
                                .getPackageInfo(context.getPackageName(), 0);
                instance.versionName = packageInfo.versionName;
                instance.firstInstallDate = Parser.SDF.parse(Parser.SDF.format(new Date(packageInfo.firstInstallTime)));
            } catch (PackageManager.NameNotFoundException | ParseException e) {
                Log.e(Constant.LOG_TAG, e.getLocalizedMessage());
            }
        }
        return instance;
    }

    private EssentialPackageInfo() {}
}
