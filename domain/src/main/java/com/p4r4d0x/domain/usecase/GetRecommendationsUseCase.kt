package com.p4r4d0x.domain.usecase

import com.p4r4d0x.domain.models.RecommendationsBo
import com.p4r4d0x.domain.repository.SearchRepository
import javax.inject.Inject

class GetRecommendationsUseCase @Inject constructor(private val repository: SearchRepository) :
    BaseUseCaseParamsResult<GetRecommendationsUseCase.Params, RecommendationsBo?>() {

    class Params(val queryTopics: String)

    override suspend fun run(params: Params): RecommendationsBo? {
        return repository.getRecommendations(params.queryTopics)
    }
}