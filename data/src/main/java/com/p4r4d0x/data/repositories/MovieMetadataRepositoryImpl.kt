package com.p4r4d0x.data.repositories

import com.p4r4d0x.data.BackendResult
import com.p4r4d0x.data.datasources.MovieMetadataDatasource
import com.p4r4d0x.data.room.MovieMetadataDao
import com.p4r4d0x.data.room.toDomain
import com.p4r4d0x.data.room.toEntity
import com.p4r4d0x.domain.models.MovieBo
import com.p4r4d0x.domain.repository.MovieMetadataRepository
import javax.inject.Inject

class MovieMetadataRepositoryImpl @Inject constructor(
    private val datasource: MovieMetadataDatasource,
    private val dao: MovieMetadataDao
) :
    MovieMetadataRepository {

    override suspend fun getMovieMetadata(movieTitle: String): MovieBo? {
        return dao.getMetadataFrom(movieTitle)?.toDomain() ?: run {
            when (val recommendations = datasource.queryMovieMetadata(movieTitle)) {
                is BackendResult.Error -> {
                    null
                }
                is BackendResult.Success -> {
                    with(recommendations.data) {
                        dao.insertMetadata(this.toEntity())
                        this
                    }
                }
            }
        }


    }
}