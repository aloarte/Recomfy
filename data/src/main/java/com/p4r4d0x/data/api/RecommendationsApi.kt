package com.p4r4d0x.data.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

interface RecommendationsApi {

    @GET("api/similar")
    fun listRepos(
        @Query("q",encoded = true) queryTopic: String?,
        @Query("verbose") mode: Int? = 1
    ): Call<RecommendationsDto>

}
