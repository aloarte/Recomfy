package com.p4r4d0x.recomfy.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p4r4d0x.domain.models.BandDataBo
import com.p4r4d0x.domain.models.RecommendationsBo
import com.p4r4d0x.domain.usecase.GetBandDataUseCase
import com.p4r4d0x.domain.usecase.GetRecommendationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val recommendationsUseCase: GetRecommendationsUseCase,
    private val bandDataUseCase: GetBandDataUseCase
) :
    ViewModel() {

    val recommendations: MutableState<RecommendationsBo?> = mutableStateOf(null)
    val bandData: MutableState<BandDataBo?> = mutableStateOf(null)


    fun searchByTopic(queryTopic: String) {
        recommendationsUseCase.invoke(
            viewModelScope,
            params = GetRecommendationsUseCase.Params(queryTopic)
        ) {
            recommendations.value = it
        }
    }

    fun startDetail(bandName:String){
        fetchArtistData(bandName)
    }

    fun fetchArtistData(bandName:String){
        bandDataUseCase.invoke(
            viewModelScope,
            params = GetBandDataUseCase.Params(bandName)
        ) {
            bandData.value = it
        }
    }



}
