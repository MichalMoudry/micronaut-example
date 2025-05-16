package com.example.repositories

import com.example.domain.Genre
import groovy.transform.CompileStatic
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.Id
import io.micronaut.data.exceptions.DataAccessException
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository
import jakarta.transaction.Transactional
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@JdbcRepository(dialect = Dialect.POSTGRES)
@CompileStatic
abstract class GenreRepository implements PageableRepository<Genre, Long> {
    abstract Genre save(@NonNull @NotBlank String name)

    @Transactional
    Genre saveWithException(@NonNull @NotBlank String name) {
        save(name)
        throw new DataAccessException('test exception')
    }

    abstract long update(
            @NonNull @NotNull @Id long id,
            @NonNull @NotBlank String name
    )
}
