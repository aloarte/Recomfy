package com.p4r4d0x.data.datasources.impl

import com.p4r4d0x.data.BackendResult
import com.p4r4d0x.data.Parsers.parseRecommendations
import com.p4r4d0x.data.enums.RecommendationResultType
import com.p4r4d0x.data.api.TasteDiveApi
import com.p4r4d0x.data.datasources.SearchDatasource
import com.p4r4d0x.domain.models.RecommendationsBo
import retrofit2.awaitResponse
import javax.inject.Inject

class SearchDatasourceImpl @Inject constructor(private val searchApi: TasteDiveApi) :
    SearchDatasource {
    override suspend fun queryRecommendations(
        queryTopic: String,
        type: RecommendationResultType?,
        limit: Int?
    ): BackendResult<RecommendationsBo> {
        return try {
            val response = searchApi.listRecommendations(queryTopic, type, limit).awaitResponse()
            if (response.isSuccessful) {
                parseRecommendations(response.body())?.let {
                    BackendResult.Success(data = it)
                } ?: run {
                    BackendResult.Error(-1)
                }

            } else {
                BackendResult.Error(-1)
            }
        } catch (e: Exception) {
            BackendResult.Error(-1)
        }
    }
}