package com.p4r4d0x.domain.usecase

import com.p4r4d0x.domain.models.MovieBo
import com.p4r4d0x.domain.repository.MovieMetadataRepository
import javax.inject.Inject

class GetMovieDataUseCase @Inject constructor(private val repository: MovieMetadataRepository) :
    BaseUseCaseParamsResult<GetMovieDataUseCase.Params, MovieBo?>() {

    class Params(val movieTitle: String)

    override suspend fun run(params: Params): MovieBo? {
        return repository.getMovieMetadata(params.movieTitle.lowercase())
    }
}