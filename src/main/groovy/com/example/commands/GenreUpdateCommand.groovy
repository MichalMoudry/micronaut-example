package com.example.commands

import groovy.transform.CompileStatic
import io.micronaut.serde.annotation.Serdeable

@CompileStatic
@Serdeable
record GenreUpdateCommand(UUID Id, String Name) {}