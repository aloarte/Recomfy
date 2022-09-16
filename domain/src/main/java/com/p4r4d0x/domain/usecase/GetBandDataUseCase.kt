package com.p4r4d0x.domain.usecase

import com.p4r4d0x.domain.models.BandDataBo
import com.p4r4d0x.domain.repository.BandDataRepository
import javax.inject.Inject

class GetBandDataUseCase @Inject constructor(private val repository: BandDataRepository) :
    BaseUseCaseParamsResult<GetBandDataUseCase.Params, BandDataBo?>() {

    class Params(val bandName: String)

    override suspend fun run(params: Params): BandDataBo? {
        return repository.getBandData(params.bandName)
    }
}