package com.apress.prospring4.ch2;

/**
 * Created by huizhou on 12/19/15.
 */
public class StandardOutMessageRenderer implements MessageRenderer {
    private MessageProvider messageProvider;


    public void render() {
        if (messageProvider == null) {
            throw new RuntimeException(
                    "You must set the property messageProvider of class:"
                            + StandardOutMessageRenderer.class.getName());
        }

        System.out.println(messageProvider.getMessage());
    }


    public void setMessageProvider(MessageProvider provider) {
        this.messageProvider = provider;
    }


    public MessageProvider getMessageProvider() {
        return this.messageProvider;
    }
}
