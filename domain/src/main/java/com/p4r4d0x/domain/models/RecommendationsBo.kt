package com.p4r4d0x.domain.models

data class RecommendationsBo(val info: List<ItemDataBo>, val similar: List<ItemDataBo>)

data class ItemDataBo(
    val name: String,
    val type: String,
    val wTeaser: String,
    val wUrl: String,
    val yUrl: String,
    val yId: String
)