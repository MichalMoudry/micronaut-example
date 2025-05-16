package com.example

import groovy.transform.CompileStatic
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

@CompileStatic
@Serdeable
final class CommandBookSave {
    @NotBlank
    String title

    @Positive
    int pages
}
