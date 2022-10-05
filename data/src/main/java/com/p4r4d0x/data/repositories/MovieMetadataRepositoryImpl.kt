package com.p4r4d0x.data.repositories

import android.util.Log
import com.p4r4d0x.data.BackendResult
import com.p4r4d0x.data.datasources.MovieMetadataDatasource
import com.p4r4d0x.domain.models.MovieBo
import com.p4r4d0x.domain.repository.MovieMetadataRepository
import javax.inject.Inject

class MovieMetadataRepositoryImpl @Inject constructor(private val datasource: MovieMetadataDatasource) :
    MovieMetadataRepository {

    override suspend fun getMovieMetadata(movieTitle: String): MovieBo? {
        return when (val recommendations = datasource.queryMovieMetadata(movieTitle)) {
            is BackendResult.Error -> {
                Log.d("ALRALR","MovieMetadataRepositoryImpl ERROR")
                null
            }
            is BackendResult.Success -> {
                Log.d("ALRALR","MovieMetadataRepositoryImpl SUCCESS")

                recommendations.data
            }
        }
    }
}