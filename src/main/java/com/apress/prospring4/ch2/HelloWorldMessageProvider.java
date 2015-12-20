package com.apress.prospring4.ch2;

/**
 * Created by huizhou on 12/19/15.
 */
public class HelloWorldMessageProvider implements MessageProvider {

    public String getMessage() {
        return "Hello World!";
    }
}
