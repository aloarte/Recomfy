package com.p4r4d0x.data.api

import com.p4r4d0x.data.dto.MovieMetadataDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {

    companion object {
        const val API_KEY = 24379130
    }

    @GET(".")
    fun getMovieMetadata(
        @Query("t", encoded = true) movieTitle: String,
        @Query("apikey") apiKey: Int = API_KEY

    ): Call<MovieMetadataDto>
}