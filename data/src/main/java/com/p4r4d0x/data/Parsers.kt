package com.p4r4d0x.data

import com.p4r4d0x.data.dto.BandMetadataDto
import com.p4r4d0x.data.dto.MovieMetadataDto
import com.p4r4d0x.data.dto.RecommendationsDto
import com.p4r4d0x.domain.RecommendationType
import com.p4r4d0x.domain.models.BandBo
import com.p4r4d0x.domain.models.ItemDataBo
import com.p4r4d0x.domain.models.MovieBo
import com.p4r4d0x.domain.models.RecommendationsBo

object Parsers {
    fun parseRecommendations(dto: RecommendationsDto?) = dto?.let {
        RecommendationsBo(
            info = dto.similar.info.map {
                ItemDataBo(
                    name = it.name,
                    type = RecommendationType.valueOf(it.type),
                    wTeaser = it.wTeaser,
                    yId = it.yId,
                    wUrl = it.wUrl,
                    yUrl = it.yUrl
                )
            },
            similar = dto.similar.result.map {
                ItemDataBo(
                    name = it.name,
                    type = RecommendationType.valueOf(it.type),
                    wTeaser = it.wTeaser,
                    yId = it.yId,
                    wUrl = it.wUrl,
                    yUrl = it.yUrl
                )
            }
        )
    }

    fun parseBandData(dto: BandMetadataDto?) = dto?.let {
        with(dto.artists.first()) {
            BandBo(
                artistName = strArtist.orEmpty(),
                formedYear = intFormedYear?.toInt() ?: -1,
                disbandedYear = strDisbanded?.toInt() ?: -1,
                style = strStyle.orEmpty(),
                genre = strGenre.orEmpty(),
                mood = strMood.orEmpty(),
                website = strWebsite.orEmpty(),
                biography = strBiographyEN.orEmpty(),
                country = strCountry.orEmpty(),
                banner = strArtistBanner.orEmpty(),
                wideThumb = strArtistWideThumb.orEmpty()
            )
        }
    }

    fun parseMovieMetadata(dto: MovieMetadataDto?) = dto?.let {
        MovieBo(
            actors = dto.Actors.orEmpty(),
            awards = dto.Awards.orEmpty(),
            boxOffice = dto.BoxOffice.orEmpty(),
            country = dto.Country.orEmpty(),
            director = dto.Director.orEmpty(),
            genre = dto.Genre.orEmpty(),
            language = dto.Language.orEmpty(),
            plot = dto.Plot.orEmpty(),
            poster = dto.Poster.orEmpty(),
            production = dto.Production.orEmpty(),
            released = dto.Released.orEmpty(),
            runtime = dto.Runtime.orEmpty(),
            title = dto.Title.orEmpty(),
            type = dto.Type.orEmpty(),
            writer = dto.Writer.orEmpty(),
            year = dto.Year.orEmpty()
        )
    }
}