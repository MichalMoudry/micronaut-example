package com.example

import groovy.transform.CompileStatic
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import io.micronaut.views.View
import jakarta.validation.ConstraintViolationException
import jakarta.validation.Valid

@CompileStatic
@Controller('/books')
class BooksController {
    private final MessageSource _messageSource

    BooksController(MessageSource messageSource) {
        _messageSource = messageSource
    }

    @View('booksCreate')
    @Get('/')
    Map<String, Object> index() {
        [title: '', pages: ''] as Map
    }

    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Post('/save')
    HttpResponse save(@Valid @Body CommandBookSave model) {
        HttpResponse.ok()
    }

    @View('booksCreate')
    @Error(exception = ConstraintViolationException)
    Map<String, Object> onSaveFailed(HttpRequest req, ConstraintViolationException ex) {
        final Map<String, Object> model = [title: '', pages: ''] as Map
        model.errors = _messageSource.violationsMessages(ex.constraintViolations)
        Optional<CommandBookSave> cmd = req.getBody(CommandBookSave) as Optional
        cmd.ifPresent {bookSave -> populateModel(model, bookSave as CommandBookSave)}
        model
    }

    @Produces(MediaType.TEXT_PLAIN)
    @Get('/stock/{isbn}')
    int stock(String isbn) {
        throw new OutOfStockException()
    }

    private static void populateModel(Map<String, Object> model, CommandBookSave cmd) {
        model.title = cmd.title
        model.pages = cmd.pages
    }
}
