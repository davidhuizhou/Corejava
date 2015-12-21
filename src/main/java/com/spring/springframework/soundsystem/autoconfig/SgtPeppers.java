package com.spring.springframework.soundsystem.autoconfig;

/**
 * Created by huizhou on 12/20/15.
 */

import com.spring.springframework.soundsystem.autoconfig.CompactDisc;

import org.springframework.stereotype.Component;

@Component("lonelyHeartsClub")
public class SgtPeppers implements CompactDisc {

    private String title = "Sgt. Pepper's Lonely Hearts Club Band";
    private String artist = "The Beatles";

    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }

}
