package com.example.transport.util

import com.example.domain.Genre
import groovy.transform.CompileStatic

@CompileStatic
final class LocationHelper {
    static URI location(Long id) {
        URI.create("/genres/$id")
    }

    static URI location(Genre genre) {
        location(genre.id)
    }
}
