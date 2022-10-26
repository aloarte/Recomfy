package com.p4r4d0x.domain.models

import com.p4r4d0x.domain.RecommendationType

data class RecommendationsBo(var info: List<ItemDataBo>, var similar: List<ItemDataBo>)

data class ItemDataBo(
    val name: String,
    val type: RecommendationType,
    val bannerImage:String = "",
    val genre :String ="",
    val description: String ="",
    val wTeaser: String="",
    val wUrl: String="",
    val yUrl: String="",
    val yId: String=""
)