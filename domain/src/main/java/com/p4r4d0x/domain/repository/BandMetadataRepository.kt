package com.p4r4d0x.domain.repository

import com.p4r4d0x.domain.models.BandBo

interface BandMetadataRepository  {
    suspend fun getBandMetadata(bandName:String) : BandBo?
}