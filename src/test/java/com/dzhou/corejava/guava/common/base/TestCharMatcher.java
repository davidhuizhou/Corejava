package com.dzhou.corejava.guava.common.base;

import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;

import org.junit.Test;

import static com.google.common.base.CharMatcher.forPredicate;
import static com.google.common.base.CharMatcher.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by huizhou on 3/27/16.
 */
public class TestCharMatcher {

    @Test
    public void teatAllMatches() {
//        doTestAllMatches(is('a'), "aaaa");
        doTestAllMatches(is('a'), "aabcadefga");
//        doTestAllMatches(CharMatcher.any(), "blah");
    }


    private void doTestAllMatches(CharMatcher matcher, String s) {
        reallyTestAllMatches(matcher, s);
//        reallyTestAllMatches(matcher.precomputed(), s);
//        reallyTestAllMatches(forPredicate(matcher), s);
//        reallyTestAllMatches(matcher, new StringBuilder(s));
    }

    private void reallyTestAllMatches(CharMatcher matcher, CharSequence s) {
        assertTrue(matcher.matches(s.charAt(0)));
        assertEquals(0, matcher.indexIn(s));
        assertEquals(0, matcher.indexIn(s, 0));
        assertEquals(1, matcher.indexIn(s, 1));
        assertEquals(-1, matcher.indexIn(s, s.length()));
        assertEquals(s.length() - 1, matcher.lastIndexIn(s));
        assertTrue(matcher.matchesAnyOf(s));
//        assertTrue(matcher.matchesAllOf(s));
        assertFalse(matcher.matchesNoneOf(s));
        assertEquals("bcdefg", matcher.removeFrom(s));
        assertEquals("aaaa", matcher.retainFrom(s));

        assertEquals(Strings.repeat("z", s.length()),
                matcher.replaceFrom(s, 'z'));
        assertEquals(Strings.repeat("ZZ", s.length()),
                matcher.replaceFrom(s, "ZZ"));
        assertEquals("", matcher.trimFrom(s));
        assertEquals(s.length(), matcher.countIn(s));
    }

}
