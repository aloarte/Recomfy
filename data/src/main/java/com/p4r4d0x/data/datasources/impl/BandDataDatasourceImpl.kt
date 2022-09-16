package com.p4r4d0x.data.datasources.impl

import com.p4r4d0x.data.BackendResult
import com.p4r4d0x.data.Parsers.parseBandData
import com.p4r4d0x.data.api.BandDataApi
import com.p4r4d0x.data.datasources.BandDataDatasource
import com.p4r4d0x.domain.models.BandDataBo
import retrofit2.awaitResponse
import javax.inject.Inject

class BandDataDatasourceImpl @Inject constructor(private val bandDataApi: BandDataApi) :
    BandDataDatasource {
    override suspend fun queryBandData(
        bandName: String
    ): BackendResult<BandDataBo> {
        return try {
            val response = bandDataApi.getBandData(bandName).awaitResponse()
            if (response.isSuccessful) {
                parseBandData(response.body())?.let {
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