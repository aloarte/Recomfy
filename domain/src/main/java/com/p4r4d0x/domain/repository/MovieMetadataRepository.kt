package com.p4r4d0x.domain.repository

import com.p4r4d0x.domain.models.MovieBo

interface MovieMetadataRepository {
    suspend fun getMovieMetadata(movieTitle: String): MovieBo?
}