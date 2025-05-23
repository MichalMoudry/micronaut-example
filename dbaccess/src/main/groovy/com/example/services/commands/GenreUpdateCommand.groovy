package com.example.services.commands

import groovy.transform.CompileStatic
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@CompileStatic
@Serdeable
final class GenreUpdateCommand {
    @NotNull
    final Long id

    @NotBlank
    final String name

    GenreUpdateCommand(Long id, String name) {
        this.id = id
        this.name = name
    }
}
