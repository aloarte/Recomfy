package com.p4r4d0x.domain.usecase

import com.p4r4d0x.domain.models.BandBo
import com.p4r4d0x.domain.repository.BandMetadataRepository
import javax.inject.Inject

class GetBandDataUseCase @Inject constructor(private val repository: BandMetadataRepository) :
    BaseUseCaseParamsResult<GetBandDataUseCase.Params, BandBo?>() {

    class Params(val bandName: String)

    override suspend fun run(params: Params): BandBo? {
        return repository.getBandMetadata(params.bandName.lowercase())
    }
}