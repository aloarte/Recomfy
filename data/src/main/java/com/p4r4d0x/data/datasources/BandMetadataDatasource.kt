package com.p4r4d0x.data.datasources

import com.p4r4d0x.data.BackendResult
import com.p4r4d0x.domain.models.BandBo

interface BandMetadataDatasource {
    suspend fun queryBandMetadata(
        bandName: String
    ):  BackendResult<BandBo>
}