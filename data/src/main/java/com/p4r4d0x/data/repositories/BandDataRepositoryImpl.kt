package com.p4r4d0x.data.repositories

import com.p4r4d0x.data.BackendResult
import com.p4r4d0x.data.datasources.BandDataDatasource
import com.p4r4d0x.domain.models.BandDataBo
import com.p4r4d0x.domain.repository.BandDataRepository
import javax.inject.Inject

class BandDataRepositoryImpl @Inject constructor(private val datasource: BandDataDatasource) :
    BandDataRepository {

    override suspend fun getBandData(bandName: String): BandDataBo? {
        return when (val recommendations = datasource.queryBandData(bandName)) {
            is BackendResult.Error -> {
                null
            }
            is BackendResult.Success -> {
                recommendations.data
            }
        }
    }
}