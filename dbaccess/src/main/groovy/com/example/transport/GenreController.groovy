package com.example.transport

import com.example.domain.Genre
import com.example.repositories.GenreRepository
import com.example.services.commands.GenreUpdateCommand
import com.example.transport.util.LocationHelper
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
    protected final GenreRepository genreRepository

    GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository
    }

    @Get('/{id}')
    Optional<Genre> show(Long id) {
        genreRepository.findById(id)
    }

    @Put
    HttpResponse update(@Body @Valid GenreUpdateCommand cmd) {
        genreRepository.update(cmd.id, cmd.name)

        HttpResponse
                .noContent()
                .header(HttpHeaders.LOCATION, LocationHelper.location(cmd.id).path)
    }

    @Get
    List<Genre> list(@Valid Pageable pageable) {
        genreRepository.findAll(pageable).content
    }

    @Post
    HttpResponse<Genre> save(@Body('name') @NotBlank String name) {
        Genre genre = genreRepository.save(name)
        HttpResponse
                .created(genre)
                .headers(headers -> headers.location(LocationHelper.location(genre)))
    }

    @Delete('/{id}')
    @Status(HttpStatus.NO_CONTENT)
    def delete(Long id) {
        genreRepository.deleteById(id)
    }

    @Post('ex')
    HttpResponse<Genre> saveWithException(@Body @NotBlank String name) {
        try {
            Genre genre = genreRepository.saveWithException(name)
            HttpResponse
                    .created(genre)
                    .headers(headers -> headers.location(LocationHelper.location(genre)))
        } catch (DataAccessException) {
            HttpResponse.noContent()
        }
    }
}
