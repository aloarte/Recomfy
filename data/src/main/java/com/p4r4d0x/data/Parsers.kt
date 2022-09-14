package com.p4r4d0x.data

import com.p4r4d0x.data.api.RecommendationsDto
import com.p4r4d0x.domain.models.ItemDataBo
import com.p4r4d0x.domain.models.RecommendationsBo

object Parsers {
    fun parseRecommendations(dto: RecommendationsDto?) = dto?.let{
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

}