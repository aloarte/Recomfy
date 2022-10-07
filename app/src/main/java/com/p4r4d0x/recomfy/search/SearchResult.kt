package com.p4r4d0x.recomfy.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.p4r4d0x.domain.RecommendationType
import com.p4r4d0x.domain.models.ItemDataBo
import com.p4r4d0x.domain.models.RecommendationsBo
import com.p4r4d0x.recomfy.main.compose.RecomfySurface
import com.p4r4d0x.recomfy.theme.RecomfyTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Preview(showBackground = true)
@Composable
private fun SearchResultsPreview() {
    RecomfyTheme {
        SearchResults(
            recommendations = RecommendationsBo(
                info = listOf(
                    ItemDataBo(
                        name = "Pulp fiction",
                        genre = "Action",
                        type = RecommendationType.movie,
                        bannerImage = "https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg",
                        wUrl = "",
                        yUrl = "",
                        yId = "",
                        wTeaser = ""
                    )
                ), similar = listOf(
                    ItemDataBo(
                        name = "Metallica",
                        type = RecommendationType.music,
                        genre = "Metal",
                        wUrl = "",
                        yUrl = "",
                        yId = "",
                        wTeaser = ""
                    ),
                    ItemDataBo(
                        name = "Reservoir Dogs",
                        genre = "Action",
                        bannerImage = "https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg",
                        type = RecommendationType.movie,
                        wUrl = "",
                        yUrl = "",
                        yId = "",
                        wTeaser = ""
                    )
                )
            ),
            onOpenDetail = {})

    }
}

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
                        Spacer(modifier = Modifier.height(5.dp))
                        ItemCard(itemDataBo = recommendation, onOpenDetail = onOpenDetail)
                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    itemDataBo: ItemDataBo,
    onOpenDetail: (ItemDataBo) -> Unit
) {
    RecomfySurface {
        Card(
            contentColor = RecomfyTheme.colors.textSecondary,
            backgroundColor = RecomfyTheme.colors.uiBackground,
            shape = RoundedCornerShape(15.dp),
            elevation = 4.dp,
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.primary),
            onClick = { onOpenDetail(itemDataBo) }
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 15.dp)
            ) {
                Box(
                    Modifier
                        .fillMaxWidth(),
                ) {

                    GlideImage(
                        modifier = Modifier.align(Alignment.CenterStart),
                        imageModel = itemDataBo.bannerImage,
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center
                        )
                    )
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        textAlign = TextAlign.Left,
                        text = itemDataBo.name,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.subtitle1,

                    )

                    Text(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        text = itemDataBo.genre,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.primaryVariant,

                    )
                }
            }
        }
    }
}
