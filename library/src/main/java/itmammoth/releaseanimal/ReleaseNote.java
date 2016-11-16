package itmammoth.releaseanimal;

import java.util.Date;
import java.util.List;

class ReleaseNote {
    VersionName versionName;
    Date date;
    List<String> messages;

    @Override
    public String toString() {
        return "ReleaseNote{" +
                "versionName=" + versionName +
                ", date=" + date +
                ", messages=" + messages +
                '}';
    }
}
