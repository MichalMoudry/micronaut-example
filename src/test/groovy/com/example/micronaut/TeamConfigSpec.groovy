package com.example.micronaut

import com.example.TeamConfig
import io.micronaut.context.ApplicationContext
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification

@MicronautTest
final class TeamConfigSpec extends Specification {
    void "test team config"() {
        given:
        ApplicationContext ctx = ApplicationContext.run(ApplicationContext, [
                "team.name": 'evolution',
                "team.color": 'green',
                "team.player-names": ['Nirav Assar', 'Lionel Messi']
        ])

        when:
        TeamConfig cfg = ctx.getBean(TeamConfig)

        then:
        cfg.name == "evolution"
        cfg.color == "green"
        cfg.playerNames[0] == "Nirav Assar"
        cfg.playerNames[1] == "Lionel Messi"

        cleanup:
        ctx.close()
    }
}
