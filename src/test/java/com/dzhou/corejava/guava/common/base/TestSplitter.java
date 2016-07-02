package com.dzhou.corejava.guava.common.base;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by huizhou on 12/11/15.
 */
public class TestSplitter {

    @Test
    public void testSplitOnCharacter() {
        char delimiter = '|';
        String text = "foo|bar|baz";
        String[] expected = new String[]{"foo", "bar", "baz"};
        Iterable<String> values = Splitter.on(delimiter).split(text);
        int index = 0;
        for (String value : values) {
            assertThat(value, is(expected[index++]));
        }

    }
//
//    @Test
//    public void testSplit() {
//        String delimiter = "&";
//        String text = "foo&bar&baz";
//        String[] expected = new String[]{"foo", "bar", "baz"};
//        Iterable<String> values = Splitter.on(delimiter).split(text);
//        int index = 0;
//        for (String value : values) {
//            assertThat(value, is(expected[index++]));
//        }
//    }
//
//    @Test
//    public void testSplitTrimResults() {
//        String delimiter = "&";
//        String text = "foo   &  bar&   baz  ";
//        String[] expected = new String[]{"foo", "bar", "baz"};
//        Iterable<String> values = Splitter.on(delimiter).trimResults().split(text);
//        int index = 0;
//        for (String value : values) {
//            assertThat(value, is(expected[index++]));
//        }
//    }
//
//    @Test
//    public void testSplitTrimResultsII() {
//        String delimiter = "&";
//        String text = "1foo&bar2&2baz3";
//        String[] expected = new String[]{"foo", "bar", "baz"};
//        Iterable<String> values = Splitter.on(delimiter).trimResults(CharMatcher.JAVA_DIGIT)
//                .split(text);
//        int index = 0;
//        for (String value : values) {
//            assertThat(value, is(expected[index++]));
//        }
//    }
//
//
//    @Test
//    public void testSplitOnCharacterKeepMissing() {
//        char delimiter = '|';
//        String text = "foo|bar|||baz";
//        String[] expected = new String[]{"foo", "bar", "", "", "baz"};
//        Iterable<String> values = Splitter.on(delimiter).split(text);
//        int index = 0;
//        for (String value : values) {
//            assertThat(value, is(expected[index++]));
//        }
//    }
//
//    @Test
//    public void testSplitOnCharacterOmitMissing() {
//        char delimiter = '|';
//        String text = "foo|bar|||baz";
//        String[] expected = new String[]{"foo", "bar", "baz"};
//        Iterable<String> values = Splitter.on(delimiter).omitEmptyStrings().split(text);
//        int index = 0;
//        for (String value : values) {
//            assertThat(value, is(expected[index++]));
//        }
//    }
//
//    @Test
//    public void testSplitPattern() {
//        String pattern = "\\d+";
//        String text = "foo123bar45678baz";
//        String[] expected = new String[]{"foo", "bar", "baz"};
//        Iterable<String> values = Splitter.on(Pattern.compile(pattern)).split(text);
//        int index = 0;
//        for (String value : values) {
//            assertThat(value, is(expected[index++]));
//        }
//    }
//
//    @Test
//    public void testSplitStringPattern() {
//        String pattern = "\\d+";
//        String text = "foo123bar45678baz";
//        String[] expected = new String[]{"foo", "bar", "baz"};
//        Iterable<String> values = Splitter.onPattern(pattern).split(text);
//        int index = 0;
//        for (String value : values) {
//            assertThat(value, is(expected[index++]));
//        }
//    }
//
//    @Test
//    public void testFixedLength() {
//        String text = "abcde";
//        String[] expected = new String[]{"ab", "cd", "e"};
//        Iterable<String> values = Splitter.fixedLength(2).split(text);
//        int index = 0;
//        for (String value : values) {
//            assertThat(value, is(expected[index++]));
//        }
//
//
//    }
//
//    @Test
//    public void testLimit() {
//        String text = "a,b,c,d";
//        String[] expected = new String[]{"a", "b", "c,d"};
//        Iterable<String> values = Splitter.on(',').limit(3).split(text);
//        int index = 0;
//        for (String value : values) {
//            assertThat(value, is(expected[index++]));
//        }
//
//    }


}
