package com.p4r4d0x.domain.repository

import com.p4r4d0x.domain.models.RecommendationsBo

interface SearchRepository  {
    suspend fun getRecommendations(queryTopics:String) : RecommendationsBo?
}