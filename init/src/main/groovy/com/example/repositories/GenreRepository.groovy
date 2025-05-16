package com.example.repositories

import com.example.domain.Genre
import groovy.transform.CompileStatic
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.Id
import io.micronaut.data.exceptions.DataAccessException
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@CompileStatic
@JdbcRepository(dialect = Dialect.POSTGRES)
abstract class GenreRepository implements PageableRepository<Genre, UUID> {
    abstract Genre save(@NonNull @NotBlank String name)

    Genre saveWithException(@NonNull @NotBlank String name) {
        save(name)
        throw new DataAccessException('test exception')
    }

    abstract UUID update(
            @NonNull @NotNull @Id UUID id,
            @NonNull @NotBlank String name
    )
}
