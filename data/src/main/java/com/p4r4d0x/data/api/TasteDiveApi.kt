package com.p4r4d0x.data.api

import com.p4r4d0x.data.dto.RecommendationsDto
import com.p4r4d0x.data.enums.RecommendationResultType
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TasteDiveApi {

    @GET("api/similar")
    fun listRecommendations(
        @Query("q",encoded = true) queryTopic: String?,
        @Query("type") type: RecommendationResultType?,
        @Query("limit") limit: Int?,
        @Query("verbose") mode: Int? = 1
    ): Call<RecommendationsDto>

}
