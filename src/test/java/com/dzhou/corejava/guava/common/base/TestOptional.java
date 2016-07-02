package com.dzhou.corejava.guava.common.base;


import com.google.common.base.Optional;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Created by huizhou on 2/21/16.
 */
public class TestOptional {

    @Test
    public void testAbsent() {
        Optional<String> optionalName = Optional.absent();
        assertFalse(optionalName.isPresent());
    }

    public void testOf() {
        assertEquals("training", Optional.of("training").get());
    }

    @Test(expected = NullPointerException.class)
    public void testOf_null() {
        Optional.of(null);

    }

    @Test
    public void testFromNullable() {
        Optional<String> optionalName = Optional.fromNullable("bob");
        assertEquals("bob", optionalName.get());
    }

    @Test
    public void testFromNullable_null() {
        assertSame(Optional.absent(), Optional.fromNullable(null));
    }

    @Test
    public void testOr_Optional_present() {
        assertEquals(Optional.of("a"), Optional.of("a").or(Optional.of("fallback")));
    }

    @Test
    public void testOr_Optional_absent() {
        assertEquals(Optional.of("fallback"), Optional.<String>absent().or(Optional.of
                ("fallback")));
    }

    @Test
    public void testOrNull_present() {
        assertEquals("a", Optional.of("a").orNull());
    }

    @Test
    public void teatOrNull_absent() {
        assertNull(Optional.absent().orNull());
    }
}
