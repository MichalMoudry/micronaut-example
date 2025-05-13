package com.example

import groovy.transform.CompileStatic
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error
import io.micronaut.http.hateoas.JsonError
import io.micronaut.http.hateoas.Link
import io.micronaut.views.ViewsRenderer

@CompileStatic
@Controller('/notfound')
final class NotFoundController {
    private final ViewsRenderer _viewsRenderer

    NotFoundController(ViewsRenderer viewsRenderer) {
        _viewsRenderer = viewsRenderer
    }

    @Error(status = HttpStatus.NOT_FOUND, global = true)
    HttpResponse notFound(HttpRequest request) {
        MediaType doesAcceptHtml = request.headers
                .accept()
                .find(i -> i.name.contains(MediaType.TEXT_HTML))
        if (doesAcceptHtml) {
            return HttpResponse
                .ok(_viewsRenderer.render('notFound', Collections.emptyMap(), request))
                .contentType(MediaType.TEXT_HTML)
        }

        JsonError err = new JsonError('Page Not Found').link(
            Link.SELF,
            Link.of(request.uri)
        )
        return HttpResponse.<JsonError>notFound().body(err)
    }
}
