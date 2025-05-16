package com.example

import groovy.transform.CompileStatic
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces

@CompileStatic
@Controller("/hello")
final class HelloController {
    @Get
    @Produces(MediaType.TEXT_PLAIN)
    String index() {
        return "Hello, world!"
    }
}
