package com.example

import io.micronaut.runtime.Micronaut
import groovy.transform.CompileStatic

@CompileStatic
final class Application {
    static void main(String[] args) {
        Micronaut.run(Application, args)
    }
}
