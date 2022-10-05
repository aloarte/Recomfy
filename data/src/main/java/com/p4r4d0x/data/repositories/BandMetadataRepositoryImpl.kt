package com.p4r4d0x.data.repositories

import com.p4r4d0x.data.BackendResult
import com.p4r4d0x.data.datasources.BandMetadataDatasource
import com.p4r4d0x.domain.models.BandBo
import com.p4r4d0x.domain.repository.BandMetadataRepository
import javax.inject.Inject

class BandMetadataRepositoryImpl @Inject constructor(private val datasource: BandMetadataDatasource) :
    BandMetadataRepository {

    override suspend fun getBandMetadata(bandName: String): BandBo? {
        return when (val recommendations = datasource.queryBandMetadata(bandName)) {
            is BackendResult.Error -> {
                null
            }
            is BackendResult.Success -> {
                recommendations.data
            }
        }
    }
}