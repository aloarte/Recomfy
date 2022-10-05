package com.p4r4d0x.data.datasources

import com.p4r4d0x.data.BackendResult
import com.p4r4d0x.data.enums.RecommendationResultType
import com.p4r4d0x.domain.models.RecommendationsBo

interface SearchDatasource {
    suspend fun queryRecommendations(
        queryTopic: String,
        type: RecommendationResultType? = null,
        limit: Int? = null
    ): BackendResult<RecommendationsBo>
}