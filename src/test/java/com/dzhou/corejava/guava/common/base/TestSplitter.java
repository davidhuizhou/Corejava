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
}
