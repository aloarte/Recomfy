package com.p4r4d0x.recomfy.main.compose

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.p4r4d0x.recomfy.main.MainViewModel
import com.p4r4d0x.recomfy.theme.RecomfyTheme


@Composable
fun RecomfySurface(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    color: Color = RecomfyTheme.colors.uiBackground,
    contentColor: Color = RecomfyTheme.colors.textSecondary,
    border: BorderStroke? = null,
    elevation: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .shadow(elevation = elevation, shape = shape, clip = false)
            .zIndex(elevation.value)
            .then(if (border != null) Modifier.border(border, shape) else Modifier)
            .background(
                color = color,
                shape = shape
            )
            .clip(shape)
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor, content = content)
    }
}


@Composable
fun RecomfyDivider(
    modifier: Modifier = Modifier,
    color: Color = RecomfyTheme.colors.uiBorder,
    thickness: Dp = 1.dp,
    startIndent: Dp = 0.dp
) {
    Divider(
        modifier = modifier,
        color = color,
        thickness = thickness,
        startIndent = startIndent
    )
}

@Composable
fun DetailScreen(viewModel: MainViewModel, itemDataName: String, onNavigateUp: () -> Unit) {
    val bandDetail = viewModel.bandData.value
    val errorReceived = viewModel.errorReceived.value
    if (bandDetail == null && !errorReceived) {
        viewModel.fetchArtistData(itemDataName)
    }

    if(errorReceived){
        Text(
            "Error",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h3,
            modifier = Modifier
                .padding(vertical = 15.dp, horizontal = 10.dp)
        )
    }else{
        bandDetail?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
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


}

