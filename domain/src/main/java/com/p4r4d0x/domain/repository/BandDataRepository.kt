package com.p4r4d0x.domain.repository

import com.p4r4d0x.domain.models.BandDataBo

interface BandDataRepository  {
    suspend fun getBandData(bandName:String) : BandDataBo?
}