package com.p4r4d0x.recomfy.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.p4r4d0x.domain.models.ItemDataBo
import com.p4r4d0x.domain.models.RecommendationsBo


@Composable
fun SearchResults(recommendations: RecommendationsBo?, onOpenDetail: (ItemDataBo) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
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
