package com.p4r4d0x.data.api

import com.p4r4d0x.data.dto.BandDataDto
import com.p4r4d0x.data.dto.RecommendationsDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BandDataApi {
    @GET("api/v1/json/2/search.php")
    fun getBandData(
        @Query("s",encoded = true) bandName: String
    ): Call<BandDataDto>

}