package com.example

import com.example.commands.GenreUpdateCommand
import com.example.domain.Genre
import com.example.repositories.GenreRepository
import groovy.transform.CompileStatic
import io.micronaut.data.exceptions.DataAccessException
import io.micronaut.data.model.Pageable
import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import io.micronaut.http.annotation.Status
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank

@CompileStatic
@ExecuteOn(TaskExecutors.BLOCKING)
@Controller('/genres')
class GenreController {
    protected final GenreRepository _genreRepository

    GenreController(GenreRepository genreRepository) {
        _genreRepository = genreRepository
    }

    @Get('/{id}')
    Optional<Genre> show(UUID id) {
        _genreRepository.findById(id)
    }

    @Put
    HttpResponse update(@Body @Valid GenreUpdateCommand cmd) {
        _genreRepository.update(cmd.Id(), cmd.Name())
        HttpResponse
                .noContent()
                .header(HttpHeaders.LOCATION, location(cmd.Id()).path)
    }

    @Get
    List<Genre> list(@Valid Pageable pageable) {
        _genreRepository.findAll(pageable).content
    }

    @Post
    HttpResponse<Genre> save(@Body('name') @NotBlank String name) {
        Genre genre = _genreRepository.save(name)
        HttpResponse
                .created(genre)
                .headers {headers -> headers.location(location(genre))}
    }

    @Post('/ex')
    HttpResponse<Genre> saveWithException(String name) {
        try {
            Genre genre = _genreRepository.saveWithException(name)
            HttpResponse
                    .created(genre)
                    .headers {headers -> headers.location(location(genre))}
        } catch (DataAccessException) {
            HttpResponse.noContent()
        }
    }

    @Delete('/{id}')
    @Status(HttpStatus.NO_CONTENT)
    void delete(UUID id) {
        _genreRepository.deleteById(id)
    }

    private static URI location(UUID id) {
        URI.create("/genres/$id")
    }

    private static URI location(Genre genre) {
        location(genre.id)
    }
}
