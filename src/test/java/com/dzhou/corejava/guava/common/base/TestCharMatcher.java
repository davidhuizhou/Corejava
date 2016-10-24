package com.dzhou.corejava.guava.common.base;

import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Arrays;

import static com.google.common.base.CharMatcher.forPredicate;
import static com.google.common.base.CharMatcher.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


/**
 * Created by huizhou on 3/27/16.
 */
public class TestCharMatcher {

    @Test
    public void teatAllMatches() {
        doTestAllMatches(CharMatcherExt.whitespace(), "\u2002\u3000\r\u0085\u200A\u2005\u2000\u3000"
            + "\u2029\u000B\u3000\u2008\u2003\u205F\u3000\u1680"
            + "\u0009\u0020\u2006\u2001\u202F\u00A0\u000C\u2009"
            + "\u3000\u2004\u3000\u3000\u2028\n\u2007\u3000");
    }


    private void doTestAllMatches(CharMatcher matcher, String s) {
        reallyTestAllMatches(matcher, s);
    }

    private void reallyTestAllMatches(CharMatcher matcher, CharSequence s) {
        assertTrue(matcher.matchesAllOf(s));

    }

//    @Test
//    public void testRemoveWhiteSpace() {
//        String tabsAndSpaces = "String  with      spaces     and           tabs";
//        String expected = "String with spaces and tabs";
//        String scrubbed = CharMatcher.whitespace().collapseFrom(tabsAndSpaces, ' ');
//        assertThat(scrubbed, CoreMatchers.is(expected));
//    }
//
//
//    @Test
//    public void testTrimRemoveWhiteSpace() {
//        String tabsAndSpaces = "    String  with      spaces     and           tabs";
//        String expected = "String with spaces and tabs";
//        String scrubbed = CharMatcher.whitespace().trimAndCollapseFrom(tabsAndSpaces, ' ');
//        assertThat(scrubbed, CoreMatchers.is(expected));
//    }
//
//
//
//    private void reallyTestAllMatches(CharMatcher matcher, CharSequence s) {
//        assertTrue(matcher.matches(s.charAt(0)));
//        assertEquals(0, matcher.indexIn(s));
//        assertEquals(0, matcher.indexIn(s, 0));
//        assertEquals(1, matcher.indexIn(s, 1));
//        assertEquals(-1, matcher.indexIn(s, s.length()));
//        assertEquals(s.length() - 1, matcher.lastIndexIn(s));
//        assertTrue(matcher.matchesAnyOf(s));
////        assertTrue(matcher.matchesAllOf(s));
//        assertFalse(matcher.matchesNoneOf(s));
//        assertEquals("bcdefg", matcher.removeFrom(s));
//        assertEquals("aaaa", matcher.retainFrom(s));
//
//        assertEquals(Strings.repeat("z", s.length()),
//                matcher.replaceFrom(s, 'z'));
//        assertEquals(Strings.repeat("ZZ", s.length()),
//                matcher.replaceFrom(s, "ZZ"));
//        assertEquals("", matcher.trimFrom(s));
//        assertEquals(s.length(), matcher.countIn(s));
//    }

}
