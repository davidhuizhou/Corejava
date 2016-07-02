package com.dzhou.corejava.guava.common.base;

import org.junit.Test;
import  com.google.common.base.Preconditions;
import com.google.inject.spi.Message;

import com.sun.webkit.perf.PerfLogger;

import junit.framework.AssertionFailedError;

import static junit.framework.TestCase.assertSame;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertFalse;
import static com.google.common.truth.Truth.assertThat;

/**
 * Created by huizhou on 2/23/16.
 */
public class TestPreconditions {

    @Test
    public void testCheckArgument_simple_success(){
        Preconditions.checkArgument(true);
    }

    @Test (expected=IllegalArgumentException.class)
    public void testCheckArgument_simple_failure(){
        Preconditions.checkArgument(false);
    }

    @Test
    public void testCheckArgument_simpleMessage_success(){
        Preconditions.checkArgument(true, IGNORE_ME);
    }

    @Test
    public void testCheckArgument_simpleMessage_failure(){
        try {
            Preconditions.checkArgument(false, new Message());
            fail("no exception thrown");
        } catch(IllegalArgumentException expected){
            verifySimpleMessage(expected);
        }
    }

    @Test
    public void testCheckArgument_nullMessage_failure(){
        try{
            Preconditions.checkArgument(false, null);
            fail("no exception thrown");
        } catch(IllegalArgumentException expected){
            assertThat(expected).hasMessage("null");
        }
    }

    @Test
    public void testCheckArgument_complexMessage_failure(){
        try{
            Preconditions.checkArgument(false, FORMAT, 5);
            fail("no exception thrown");
        } catch(IllegalArgumentException expected){
            verifyComplexMessage(expected);
        }
    }

    private static final String NON_NULL_STRING = "foo";

    @Test
    public void testCheckNotNull_simple_success(){
        String result = Preconditions.checkNotNull(NON_NULL_STRING);
        assertSame(NON_NULL_STRING, result);
    }


    @Test
    public void testCheckNotNull_simple_failure(){
        try{
            Preconditions.checkNotNull(null);
            fail("no exception thrown");
        } catch(NullPointerException expected){

        }
    }

    @Test
    public void testCheckNotNull_simpleMessage_failure() {
        try {
            Preconditions.checkNotNull(null, new Message());
            fail("no exception thrown");
        } catch (NullPointerException expected) {
            verifySimpleMessage(expected);
        }
    }

    @Test
    public void testCheckNotNull_complexMessage_failure(){
        try{
            Preconditions.checkNotNull(null, FORMAT, 5);
            fail("no exception thrown");
        } catch(NullPointerException expected){
            verifyComplexMessage(expected);
        }
    }

    @Test
    public void testCheckState_simple_success(){
        Preconditions.checkState(true);
    }

    @Test
    public void testCheckState_simple_failure(){
        try{
            Preconditions.checkState(false);
            fail("no exception thrown");
        } catch(IllegalStateException expected){

        }
    }

    @Test
    public void testCheckState_complexMessage_failure(){
        try{
            Preconditions.checkState(false, FORMAT, 5);
            fail("no exception thrown");
        } catch(IllegalStateException expected){
            verifyComplexMessage(expected);
        }
    }

    @Test
    public void testCheckElementIndex_negative() {
        try{
            Preconditions.checkElementIndex(-1, 1);
            fail();
        } catch(IndexOutOfBoundsException expected) {
            assertThat(expected).hasMessage("index (-1) must not be negative");
        }
    }

    @Test
    public void testCheckElementIndex_withDesc_negative(){
        try{
            Preconditions.checkElementIndex(-1, 1, "foo");
            fail();
        } catch(IndexOutOfBoundsException expected){
            assertThat(expected).hasMessage("foo (-1) must not be negative");
        }
    }

    @Test
    public void testCheckElementIndex_tooHigh() {
        try{
            Preconditions.checkElementIndex(1, 1);
            fail();
        } catch(IndexOutOfBoundsException expected){
            assertThat(expected).hasMessage("index (1) must be less than size (1)");
        }
    }

    @Test
    public void testCheckElementIndex_withDesc_tooHigh() {
        try{
            Preconditions.checkElementIndex(1, 1, "foo");
            fail();
        } catch(IndexOutOfBoundsException expected){
            assertThat(expected).hasMessage("foo (1) must be less than size (1)");
        }
    }

    private static final Object IGNORE_ME = new Object(){
        @Override
        public String toString(){
            throw new AssertionFailedError();
        }
    };

    private static class Message {
        boolean invoked;
        @Override
        public String toString(){
            assertFalse(invoked);
            invoked = true;
            return "A message";
        }
    }

    private static final String FORMAT = "I ate %s pies.";

    private static void verifySimpleMessage(Exception e){
        assertThat(e).hasMessage("A message");
    }

    private static void verifyComplexMessage(Exception e){
        assertThat(e).hasMessage("I ate 5 pies.");
    }

}
