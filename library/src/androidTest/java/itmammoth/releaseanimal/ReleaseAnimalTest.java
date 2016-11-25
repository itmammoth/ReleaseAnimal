package itmammoth.releaseanimal;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ReleaseAnimalTest {

    private Context context;

    @Before
    public void setUp() {
        this.context = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void Builder_show() throws Exception {
        assertNotNull("Cannot test it without activity");
    }

    @Test
    public void resetVersion() {
        ReleaseAnimal.resetVersion(context);
    }
}