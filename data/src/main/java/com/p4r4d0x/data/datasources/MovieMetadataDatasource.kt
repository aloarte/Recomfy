package com.p4r4d0x.data.datasources

import com.p4r4d0x.data.BackendResult
import com.p4r4d0x.domain.models.BandBo
import com.p4r4d0x.domain.models.MovieBo

interface MovieMetadataDatasource {
    suspend fun queryMovieMetadata(
        movieTitle: String
    ):  BackendResult<MovieBo>
}