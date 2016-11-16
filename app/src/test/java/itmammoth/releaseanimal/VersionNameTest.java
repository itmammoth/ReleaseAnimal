package itmammoth.releaseanimal;

import org.junit.Test;

import itmammoth.releaseanimal.VersionName;

import static org.junit.Assert.*;

public class VersionNameTest {

    @Test
    public void compareTo_shouldDelegateToString() throws Exception {
        VersionName v1 = new VersionName("1.0.0");
        VersionName v2 = new VersionName("1.0.1");
        assertEquals("1.0.0".compareTo("1.0.1"), v1.compareTo(v2));
    }

    @Test
    public void greaterThan_withLessOne_1() throws Exception {
        VersionName v1 = new VersionName("1.0.1");
        VersionName v2 = new VersionName("1.0.0");
        assertTrue(v1.greaterThan(v2));
    }

    @Test
    public void greaterThan_withLessOne_2() throws Exception {
        VersionName v1 = new VersionName("1.0.1");
        VersionName v2 = new VersionName("1.0");
        assertTrue(v1.greaterThan(v2));
    }

    @Test
    public void greaterThan_withLessOne_3() throws Exception {
        VersionName v1 = new VersionName("1.1");
        VersionName v2 = new VersionName("1.0.1");
        assertTrue(v1.greaterThan(v2));
    }

    @Test
    public void greaterThan_withLessOne_4() throws Exception {
        VersionName v1 = new VersionName("1.10.0");
        VersionName v2 = new VersionName("1.9.0");
        assertTrue(v1.greaterThan(v2));
    }

    @Test
    public void greaterThan_withGreaterOne_1() throws Exception {
        VersionName v1 = new VersionName("1.0.0");
        VersionName v2 = new VersionName("1.0.1");
        assertFalse(v1.greaterThan(v2));
    }

    @Test
    public void greaterThan_withGreaterOne_2() throws Exception {
        VersionName v1 = new VersionName("1.0.0");
        VersionName v2 = new VersionName("1.1");
        assertFalse(v1.greaterThan(v2));
    }

    @Test
    public void greaterThan_withGreaterOne_3() throws Exception {
        VersionName v1 = new VersionName("1.0");
        VersionName v2 = new VersionName("1.0.1");
        assertFalse(v1.greaterThan(v2));
    }

    @Test
    public void greaterThan_withEqualOne() throws Exception {
        VersionName v1 = new VersionName("1.0.1");
        VersionName v2 = new VersionName("1.0.1");
        assertFalse(v1.greaterThan(v2));
    }

    @Test
    public void greaterThanOrEqualTo_withEqualOne() throws Exception {
        VersionName v1 = new VersionName("1.0.1");
        VersionName v2 = new VersionName("1.0.1");
        assertTrue(v1.greaterThanOrEqualTo(v2));
    }

    @Test
    public void greaterThanOrEqualTo_withGreaterOne() throws Exception {
        VersionName v1 = new VersionName("1.0.1");
        VersionName v2 = new VersionName("1.0.2");
        assertFalse(v1.greaterThanOrEqualTo(v2));
    }

    @Test
    public void greaterThanOrEqualTo_withLessOne() throws Exception {
        VersionName v1 = new VersionName("1.0.1");
        VersionName v2 = new VersionName("1.0.0");
        assertTrue(v1.greaterThanOrEqualTo(v2));
    }
}