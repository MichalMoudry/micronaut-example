package com.example

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("team")
final class TeamConfig {
    String name
    String color
    List<String> playerNames
}
