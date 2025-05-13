package com.example.services

import groovy.transform.CompileStatic
import jakarta.inject.Singleton

@Singleton
@CompileStatic
final class MessageService {
    String compose() {
        "Hello, World!\n${UUID.randomUUID()}"
    }
}
