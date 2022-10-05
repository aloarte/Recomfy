package com.p4r4d0x.data.api

import com.p4r4d0x.data.dto.BandMetadataDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheAudioDbApi {
    @GET("api/v1/json/2/search.php")
    fun getBandData(
        @Query("s",encoded = true) bandName: String
    ): Call<BandMetadataDto>

}