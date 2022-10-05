package com.p4r4d0x.data.repositories

import com.p4r4d0x.data.BackendResult
import com.p4r4d0x.data.enums.RecommendationResultType
import com.p4r4d0x.data.datasources.SearchDatasource
import com.p4r4d0x.domain.models.RecommendationsBo
import com.p4r4d0x.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val datasource: SearchDatasource) :
    SearchRepository {

    override suspend fun getRecommendations(queryTopics: String): RecommendationsBo? {

        return when(val recommendations = datasource.queryRecommendations(queryTopics, limit= 8)){
            is BackendResult.Error -> {
                null
            }
            is BackendResult.Success -> {
                recommendations.data
            }
        }

    }
}