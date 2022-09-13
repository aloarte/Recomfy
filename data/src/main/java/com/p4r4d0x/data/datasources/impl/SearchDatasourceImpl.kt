package com.p4r4d0x.data.datasources.impl

import com.p4r4d0x.data.api.RecommendationsApi
import com.p4r4d0x.data.datasources.SearchDatasource
import javax.inject.Inject

class SearchDatasourceImpl @Inject constructor(/*private val searchApi:RecommendationsApi*/) : SearchDatasource {
    override suspend fun queryRecommendations(queryTopic: String) {
//        val response = searchApi.listRepos()
    }
}