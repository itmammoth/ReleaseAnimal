package itmammoth.releaseanimal;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

class Preference {

    private static final String PREF_KEY = "releaseanimal";
    private static final String PREF_V_KEY_LAST_SHOWN = "last_shown";

    private final SharedPreferences sharedPreferences;
    private String lastShown;

    Preference(@NonNull Context context) {
        sharedPreferences = getSharedPreferences(context);
        lastShown = sharedPreferences.getString(
                PREF_V_KEY_LAST_SHOWN, Constant.FALLBACK_VERSION_NAME);
    }

    String getLastShownVersionName() {
        return lastShown;
    }

    void markShown(@NonNull String versionName) {
        sharedPreferences
                .edit()
                .putString(PREF_V_KEY_LAST_SHOWN, versionName)
                .apply();
        lastShown = versionName;
    }

    void reset() {
        sharedPreferences
                .edit()
                .putString(PREF_V_KEY_LAST_SHOWN, Constant.FALLBACK_VERSION_NAME)
                .apply();
        lastShown = Constant.FALLBACK_VERSION_NAME;
    }

    private static SharedPreferences getSharedPreferences(@NonNull Context context) {
        return context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
    }
}
