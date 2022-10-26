package com.p4r4d0x.recomfy.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.p4r4d0x.domain.RecommendationType
import com.p4r4d0x.domain.models.ItemDataBo
import com.p4r4d0x.domain.models.RecommendationsBo
import com.p4r4d0x.recomfy.R
import com.p4r4d0x.recomfy.main.compose.RecomfyDivider
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
                        genre = "Action, Police, Thriller",
                        description = "Tells the story of Benjamin Button, a man who starts aging backwards with consequences",
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
                        genre = "Metal, Trash Metal",
                        wUrl = "",
                        yUrl = "",
                        yId = "",
                        wTeaser = ""
                    ),
                    ItemDataBo(
                        name = "Reservoir Dogs",
                        genre = "Action, Police, Thriller",
                        description = "Tells the story of Benjamin Button, a man who starts aging backwards with consequences",
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
            .fillMaxWidth().padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        recommendations?.similar?.let {
            LazyColumn {
                item {
                    recommendations.similar.forEach { recommendation ->
                        Spacer(modifier = Modifier.height(5.dp))
                        recommendation?.let{
                            ItemCard(itemDataBo = recommendation, onOpenDetail = onOpenDetail)

                        }
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
            modifier = Modifier
                .height(140.dp)
                .fillMaxWidth(),
            contentColor = RecomfyTheme.colors.textSecondary,
            backgroundColor = RecomfyTheme.colors.uiBackground,
            shape = RoundedCornerShape(15.dp),
            elevation = 4.dp,
            border = BorderStroke(width = 2.dp, color = RecomfyTheme.colors.uiBorder),
            onClick = { onOpenDetail(itemDataBo) }
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    Modifier
                        .fillMaxWidth(),
                ) {

                    GlideImage(
                        previewPlaceholder = R.mipmap.placeholder,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .width(94.dp)
                            .height(140.dp),
                        imageModel = itemDataBo.bannerImage,
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center
                        )
                    )

                    Column(
                        modifier = Modifier.align(Alignment.CenterEnd)
                            .fillMaxWidth(0.74f)
                            .height(120.dp),

                        ) {
                        Text(
                            textAlign = TextAlign.Left,
                            text = itemDataBo.name,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.subtitle1,
                            color = RecomfyTheme.colors.textPrimary
                        )

                        Text(
                            text = itemDataBo.genre,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.body1,
                            color = RecomfyTheme.colors.textPrimary

                        )

                        RecomfyDivider(modifier = Modifier.height(5.dp), color = Color.Transparent)

                        Text(
                            text = itemDataBo.description,
                            fontSize = 12.sp,
                            lineHeight = 15.sp,
                            fontWeight = FontWeight.Normal,
                            style = MaterialTheme.typography.body1,
                            color = RecomfyTheme.colors.textPrimary

                        )
                    }


                }
            }
        }
    }
}
