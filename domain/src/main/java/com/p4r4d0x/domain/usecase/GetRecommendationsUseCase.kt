package com.p4r4d0x.domain.usecase

import com.p4r4d0x.domain.RecommendationType
import com.p4r4d0x.domain.models.ItemDataBo
import com.p4r4d0x.domain.models.RecommendationsBo
import com.p4r4d0x.domain.repository.BandMetadataRepository
import com.p4r4d0x.domain.repository.MovieMetadataRepository
import com.p4r4d0x.domain.repository.SearchRepository
import javax.inject.Inject

class GetRecommendationsUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
    private val bandMetadataRepository: BandMetadataRepository,
    private val movieMetadataRepository: MovieMetadataRepository

) :
    BaseUseCaseParamsResult<GetRecommendationsUseCase.Params, RecommendationsBo?>() {

    class Params(val queryTopics: String)

    override suspend fun run(params: Params): RecommendationsBo? {
        return searchRepository.getRecommendations(params.queryTopics)?.let { recommendations ->
            recommendations.similar = recommendations.similar.map { getMetadata(it) }
            recommendations.info = recommendations.info.map { getMetadata(it) }
            recommendations
        }
    }

    private suspend fun getMetadata(item: ItemDataBo): ItemDataBo {
        return when (item.type) {
            RecommendationType.movie -> {
                movieMetadataRepository.getMovieMetadata(item.name)?.let { movie ->
                    item.copy(bannerImage = movie.poster, genre = movie.genre)
                } ?: run {
                    item
                }
            }
            RecommendationType.music -> {
                bandMetadataRepository.getBandMetadata(item.name)?.let { band ->
                    item.copy(bannerImage = band.wideThumb, genre = band.genre)
                } ?: run {
                    item
                }
            }
            else -> {
                item
            }
        }
    }
}