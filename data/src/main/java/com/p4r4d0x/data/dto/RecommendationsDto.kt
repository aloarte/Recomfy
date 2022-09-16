package com.p4r4d0x.data.dto

import com.google.gson.annotations.SerializedName

data class RecommendationsDto(
    @SerializedName("Similar")
    val similar: SimilarDto
)

data class SimilarDto(
    @SerializedName("Info")
    val info: List<ItemDataDto>,
    @SerializedName("Results")
    val result: List<ItemDataDto>
)

data class ItemDataDto(
    @SerializedName("Name")
    val name: String = "",
    @SerializedName("Type")
    val type: String = "",
    @SerializedName("wTeaser")
    val wTeaser: String = "",
    @SerializedName("wUrl")
    val wUrl: String = "",
    @SerializedName("yUrl")
    val yUrl: String = "",
    @SerializedName("yId")
    val yId: String = ""
)