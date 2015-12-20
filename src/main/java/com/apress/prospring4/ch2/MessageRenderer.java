package com.apress.prospring4.ch2;

/**
 * Created by huizhou on 12/19/15.
 */
public interface MessageRenderer {
    void render();

    void setMessageProvider(MessageProvider provider);

    MessageProvider getMessageProvider();
}
