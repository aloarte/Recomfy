package com.p4r4d0x.data.datasources

import com.p4r4d0x.data.BackendResult
import com.p4r4d0x.domain.models.RecommendationsBo

interface SearchDatasource {
    suspend fun queryRecommendations(queryTopic: String): BackendResult<RecommendationsBo>
}