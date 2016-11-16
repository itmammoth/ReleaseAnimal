package itmammoth.releaseanimal;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ParserTest {
    @Test
    public void parse() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        List<ReleaseNote> notes = new Parser(context.getResources().getXml(R.xml.releaseanimal)).parse();

        assertEquals(4, notes.size());

        ReleaseNote note = notes.get(0);
        assertEquals(new VersionName("0.0.0.1"), note.versionName);
        assertEquals(Parser.SDF.parse("1900-01-01"), note.date);
        assertEquals("- Never show the notes that had been released before installing the app", note.messages.get(0));
    }
}