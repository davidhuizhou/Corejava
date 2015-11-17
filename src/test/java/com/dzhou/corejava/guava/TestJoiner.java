package com.dzhou.corejava.guava;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by huizhou on 11/14/15.
 */
public class TestJoiner {

    @Test
    public void testJoinString() {
        String[] values = new String[]{"foo", "bar", "baz"};
        String returned = Joiner.on("|").join(values);
        assertThat(returned, is("foo|bar|baz"));
    }

    @Test
    public void testJoinStringBuilder() {
        String[] values = new String[]{"foo", "bar", "baz"};
        StringBuilder builder = new StringBuilder();
        StringBuilder returned = Joiner.on("|").appendTo(builder, values);
        assertThat(returned, is(builder));
        assertThat(returned.toString(), is("foo|bar|baz"));
    }

    @Test
    public void testMapJoiner() {
        String expectedString = "Washington D.C=Redskins#New York City=Giants#Philadelphia=Eagles#Dallas=Cowboys";
        Map<String, String> testMap = Maps.newLinkedHashMap();
        testMap.put("Washington D.C", "Redskins");
        testMap.put("New York City", "Giants");
        testMap.put("Philadelphia", "Eagles");
        testMap.put("Dallas", "Cowboys");
        String returnedString = Joiner.on("#").withKeyValueSeparator("=").join(testMap);
        assertThat(returnedString, is(expectedString));
    }


}
