package com.p4r4d0x.data.repositories

import android.util.Log
import com.p4r4d0x.data.datasources.SearchDatasource
import com.p4r4d0x.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val datasource: SearchDatasource): SearchRepository {

    override suspend fun getRecommendations(queryTopics:String){
       val a = datasource.queryRecommendations(queryTopics)
    }
}