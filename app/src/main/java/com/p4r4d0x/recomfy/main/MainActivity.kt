package com.p4r4d0x.recomfy.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.p4r4d0x.recomfy.main.compose.DetailScreen
import com.p4r4d0x.recomfy.main.compose.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity:AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel.searchByTopic("bring+me+the+horizon")
        viewModel.fetchArtistData("bring_me_the_horizon")

        setContent {
            DetailScreen(viewModel)
        }
    }
}