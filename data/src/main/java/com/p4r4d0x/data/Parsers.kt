package com.p4r4d0x.data

import android.util.Log
import com.p4r4d0x.data.dto.BandDataDto
import com.p4r4d0x.data.dto.RecommendationsDto
import com.p4r4d0x.domain.models.BandDataBo
import com.p4r4d0x.domain.models.ItemDataBo
import com.p4r4d0x.domain.models.RecommendationsBo

object Parsers {
    fun parseRecommendations(dto: RecommendationsDto?) = dto?.let {
        RecommendationsBo(
            info = dto.similar.info.map {
                ItemDataBo(
                    name = it.name,
                    type = it.type,
                    wTeaser = it.wTeaser,
                    yId = it.yId,
                    wUrl = it.wUrl,
                    yUrl = it.yUrl
                )
            },
            similar = dto.similar.result.map {
                ItemDataBo(
                    name = it.name,
                    type = it.type,
                    wTeaser = it.wTeaser,
                    yId = it.yId,
                    wUrl = it.wUrl,
                    yUrl = it.yUrl
                )
            }
        )
    }

    fun parseBandData(dto: BandDataDto?) = dto?.let {
        with(dto.artists.first()) {
            BandDataBo(
                artistName = strArtist.orEmpty(),
                formedYear = intFormedYear?.toInt()?:-1,
                disbandedYear = strDisbanded?.toInt()?:-1,
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

}