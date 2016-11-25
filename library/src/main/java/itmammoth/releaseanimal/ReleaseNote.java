package itmammoth.releaseanimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class ReleaseNote {
    VersionName versionName;
    Date date;
    boolean force;
    List<String> messages = new ArrayList<>();

    @Override
    public String toString() {
        return "ReleaseNote{" +
                "versionName=" + versionName +
                ", date=" + date +
                ", force=" + force +
                ", messages=" + messages +
                '}';
    }
}
