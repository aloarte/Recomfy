package com.p4r4d0x.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.p4r4d0x.domain.models.MovieBo

@Entity(
    tableName = "movies_metadata_table",
)
data class MovieMetadataEntity(
    val actors: String,
    val awards: String,
    val boxOffice: String,
    val country: String,
    val director: String,
    val genre: String,
    val language: String,
    val plot: String,
    val poster: String,
    val production: String,
    val released: String,
    val runtime: String,
    @PrimaryKey
    val title: String,
    val type: String,
    val writer: String,
    val year: String,
    )

fun MovieMetadataEntity.toDomain() = MovieBo(
    actors = actors,
    awards = awards,
    boxOffice = boxOffice,
    country = country,
    director = director,
    genre = genre,
    language = language,
    plot = plot,
    poster = poster,
    production = production,
    released = released,
    runtime = runtime ,
    title = title,
    type = type,
    writer = writer,
    year = year
)

fun MovieBo.toEntity() = MovieMetadataEntity(
    actors = this.actors,
    awards = this.awards,
    boxOffice = this.boxOffice,
    country = this.country,
    director = this.director,
    genre = this.genre,
    language = this.language,
    plot = this.plot,
    poster = this.poster,
    production = this.production,
    released = this.released,
    runtime = this.runtime,
    title = this.title,
    type = this.type,
    writer = this.writer,
    year = this.year
)


