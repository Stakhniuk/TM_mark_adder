package com.tm;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;

@ConfigMapping(prefix = "greeting")
public interface Config {

    @WithName("message")
    String message();
}