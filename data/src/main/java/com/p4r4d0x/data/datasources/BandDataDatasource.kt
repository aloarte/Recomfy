package com.p4r4d0x.data.datasources

import com.p4r4d0x.data.BackendResult
import com.p4r4d0x.data.api.RecommendationResultType
import com.p4r4d0x.domain.models.BandDataBo
import com.p4r4d0x.domain.models.RecommendationsBo

interface BandDataDatasource {
    suspend fun queryBandData(
        bandName: String
    ):  BackendResult<BandDataBo>
}