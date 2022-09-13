package com.p4r4d0x.data.datasources

interface SearchDatasource {
    suspend fun queryRecommendations(queryTopic:String)
}