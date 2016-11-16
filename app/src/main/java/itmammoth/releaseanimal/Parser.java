package itmammoth.releaseanimal;

import android.content.res.XmlResourceParser;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

class Parser {

    private final XmlResourceParser parser;
    private final SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");

    Parser(XmlResourceParser parser) {
        this.parser = parser;
    }

    List<ReleaseNote> parse() {
        List<ReleaseNote> result = new ArrayList<>();

        try {
            ReleaseNote note = null;
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                final String name = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        if ("note".equals(name)) {
                            note = new ReleaseNote();
                            note.versionName = new VersionName(parser.getAttributeValue(null, "versionName"));
                            note.date = dateParser.parse(parser.getAttributeValue(null, "date"));
                            note.messages = new ArrayList<>();
                        } else if ("message".equals(name)) {
                            note.messages.add(parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("note".equals(name)) {
                            result.add(note);
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException | IOException | ParseException e) {
            Log.e(Constant.LOG_TAG, e.getLocalizedMessage());
        }

        return result;
    }
}
