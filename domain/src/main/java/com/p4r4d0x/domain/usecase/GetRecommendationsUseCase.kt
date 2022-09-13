package com.p4r4d0x.domain.usecase

import com.p4r4d0x.domain.repository.SearchRepository
import javax.inject.Inject

class GetRecommendationsUseCase @Inject constructor( private val getRecommendationsRepository: SearchRepository)
    : BaseUseCaseParamsResult<GetRecommendationsUseCase.Params,String>(){

    class Params(val queryTopics:String)

    override suspend fun run(params: Params): String {
        getRecommendationsRepository.getRecommendations(params.queryTopics)
        return ""
    }
}