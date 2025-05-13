package com.example

import groovy.transform.CompileStatic
import jakarta.inject.Singleton
import jakarta.validation.ConstraintViolation
import jakarta.validation.Path

@CompileStatic
@Singleton
final class MessageSource {
    List<String> violationsMessages(Set<ConstraintViolation<?>> violations) {
        violations.collect {violationMessage(it)}
    }

    private static String violationMessage(ConstraintViolation violation) {
        StringBuilder sb = new StringBuilder()
        Path.Node lastNode = lastNode(violation.propertyPath)
        if (lastNode) {
            sb << lastNode.name << ' '
        }
        sb << violation.message
        sb
    }

    private static Path.Node lastNode(Path path) {
        Path.Node lastNode = null
        for (final Path.Node node : path) {
            lastNode = node
        }
        lastNode
    }
}
