package com.example

import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

import java.net.http.HttpClient
import java.net.http.HttpRequest

@MicronautTest
final class HelloControllerSpec extends Specification {
    @Inject
    @Client("/")
    HttpClient client

    void "test hello world response"() {
        when:
        HttpRequest request = HttpRequest.GET('/hello')
        String response = client.toBlocking().retrieve(request)
        then:
        response == "Hello world"
    }
}
