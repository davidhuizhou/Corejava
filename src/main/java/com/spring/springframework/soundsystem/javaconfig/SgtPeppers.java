package com.spring.springframework.soundsystem.javaconfig;

/**
 * Created by huizhou on 12/20/15.
 */
public class SgtPeppers implements CompactDisc {

    private String title = "Sgt. Pepper's Lonely Hearts Club Band";
    private String artist = "The Beatles";

    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }

}

