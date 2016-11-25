package itmammoth.releaseanimal;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ParserTest {
    @Test
    public void parse() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        List<ReleaseNote> notes = new Parser(context.getResources().getXml(R.xml.releaseanimal)).parse();

        assertEquals(2, notes.size());

        ReleaseNote note = notes.get(0);
        assertEquals(new VersionName("1.0.0"), note.versionName);
        assertEquals(Parser.SDF.parse("1900-01-01"), note.date);
        assertTrue(note.force);
        assertEquals("All you have to do is to create releaseanimal.xml in res/xml", note.messages.get(0));
        note = notes.get(1);
        assertEquals(new VersionName("2.0.0"), note.versionName);
        assertEquals(Parser.SDF.parse("1900-01-02"), note.date);
        assertTrue(note.force);
        assertEquals("See the https://github.com/itmammoth/ReleaseAnimal for more information", note.messages.get(0));
    }
}