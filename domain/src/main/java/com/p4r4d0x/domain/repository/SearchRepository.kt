package com.p4r4d0x.domain.repository

interface SearchRepository  {
    suspend fun getRecommendations(queryTopics:String)
}