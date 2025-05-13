package com.example

import com.example.services.MessageService
import groovy.transform.CompileStatic
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces

@CompileStatic
@Controller("/constructor")
final class MessageController {
    private final MessageService msgService

    MessageController(MessageService messageService) {
        msgService = messageService
    }

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    String index() {
        msgService.compose()
    }
}
