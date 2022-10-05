package com.p4r4d0x.data.datasources.impl

import com.p4r4d0x.data.BackendResult
import com.p4r4d0x.data.Parsers.parseBandData
import com.p4r4d0x.data.api.TheAudioDbApi
import com.p4r4d0x.data.datasources.BandMetadataDatasource
import com.p4r4d0x.domain.models.BandBo
import retrofit2.awaitResponse
import javax.inject.Inject

class BandMetadataDatasourceImpl @Inject constructor(private val bandDataApi: TheAudioDbApi) :
    BandMetadataDatasource {
    override suspend fun queryBandMetadata(
        bandName: String
    ): BackendResult<BandBo> {
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