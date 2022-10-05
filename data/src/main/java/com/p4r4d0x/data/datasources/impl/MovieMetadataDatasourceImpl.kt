package com.p4r4d0x.data.datasources.impl

import com.p4r4d0x.data.BackendResult
import com.p4r4d0x.data.Parsers.parseMovieMetadata
import com.p4r4d0x.data.api.OmdbApi
import com.p4r4d0x.data.datasources.MovieMetadataDatasource
import com.p4r4d0x.domain.models.MovieBo
import retrofit2.awaitResponse
import javax.inject.Inject

class MovieMetadataDatasourceImpl @Inject constructor(private val metadataApi: OmdbApi) :
    MovieMetadataDatasource {

    override suspend fun queryMovieMetadata(
        movieTitle: String
    ): BackendResult<MovieBo> {
        return try {
            val response = metadataApi.getMovieMetadata(movieTitle).awaitResponse()
            if (response.isSuccessful) {
                parseMovieMetadata(response.body())?.let {
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