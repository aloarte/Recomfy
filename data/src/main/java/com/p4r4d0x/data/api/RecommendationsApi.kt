package com.p4r4d0x.data.api

import retrofit2.http.GET
import retrofit2.http.Path

interface RecommendationsApi {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String?)
}
