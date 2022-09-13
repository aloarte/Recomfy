package com.p4r4d0x.recomfy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p4r4d0x.domain.usecase.GetRecommendationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getRecommendationsUseCase: GetRecommendationsUseCase) :
    ViewModel() {

    fun searchByTopic(queryTopic: String) {
        getRecommendationsUseCase.invoke(
            viewModelScope,
            params = GetRecommendationsUseCase.Params(queryTopic)
        )
    }

}
