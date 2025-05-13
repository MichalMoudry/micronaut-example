package com.example

import groovy.transform.CompileStatic
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import jakarta.inject.Singleton

@CompileStatic
@Produces
@Singleton
@Requires(classes = [OutOfStockException, ExceptionHandler])
final class OutOfStockExceptionHandler implements ExceptionHandler<OutOfStockException, HttpResponse> {
    @Override
    HttpResponse handle(HttpRequest request, OutOfStockException exception) {
        HttpResponse.ok(0)
    }
}
