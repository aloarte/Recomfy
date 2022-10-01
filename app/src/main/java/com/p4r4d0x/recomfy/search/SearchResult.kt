package com.p4r4d0x.recomfy.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.p4r4d0x.domain.models.ItemDataBo
import com.p4r4d0x.domain.models.RecommendationsBo
import com.p4r4d0x.recomfy.main.compose.RecomfySurface
import com.p4r4d0x.recomfy.theme.RecomfyTheme


@Composable
fun SearchResults(recommendations: RecommendationsBo?, onOpenDetail: (ItemDataBo) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        recommendations?.similar?.let {
            LazyColumn {
                item {
                    recommendations.similar.forEach { recommendation ->
                        ItemCard(itemDataBo = recommendation, onOpenDetail = onOpenDetail)
                        Spacer(modifier = Modifier.height(5.dp))

                    }

                }
            }

        }
    }
}

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    itemDataBo: ItemDataBo,
    onOpenDetail: (ItemDataBo) -> Unit
) {
    RecomfySurface{
        Card(
            backgroundColor = RecomfyTheme.colors.uiBackground,
            elevation = 10.dp,
            modifier = modifier
                .size(
                    width = 300.dp,
                    height = 80.dp
                )
                .padding(bottom = 0.dp)

        ) {
            Column(
                modifier = Modifier
                    .clickable(onClick = { onOpenDetail(itemDataBo) })
                    .fillMaxSize()
            ) {

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = itemDataBo.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h6,
                    color = RecomfyTheme.colors.textSecondary,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = itemDataBo.type,
                    style = MaterialTheme.typography.body1,
                    color = RecomfyTheme.colors.textHelp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }


}
