package com.p4r4d0x.recomfy.main.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.p4r4d0x.recomfy.main.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        val recommendations = viewModel.recommendations.value
        recommendations?.similar?.let{
            LazyColumn {
                item {
                    recommendations.similar.forEach { recommendation ->
                        Text(
                            recommendation.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.h3,
                            modifier = Modifier
                                .padding(vertical = 15.dp, horizontal = 10.dp)
                        )
                        Spacer(modifier = Modifier.height(5.dp))

                    }

                }
            }

        }
    }
}

@Composable
fun DetailScreen(viewModel: MainViewModel) {

    val bandDetail = viewModel.bandData.value

    bandDetail?.let{
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ){
            Text(
                bandDetail.artistName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .padding(vertical = 15.dp, horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                bandDetail.biography,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .padding(vertical = 15.dp, horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                bandDetail.genre,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .padding(vertical = 15.dp, horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                bandDetail.mood,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .padding(vertical = 15.dp, horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
        }
    }

}

