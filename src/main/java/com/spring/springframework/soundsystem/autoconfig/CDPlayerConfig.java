package com.spring.springframework.soundsystem.autoconfig;

/**
 * Created by huizhou on 12/20/15.
 */

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class CDPlayerConfig {
    static {
        System.out.println("In CDPlayerConfig");
    }

}
