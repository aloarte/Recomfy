package com.p4r4d0x.recomfy.search

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.p4r4d0x.domain.models.ItemDataBo
import com.p4r4d0x.recomfy.main.MainViewModel
import com.p4r4d0x.recomfy.main.compose.RecomfyDivider
import com.p4r4d0x.recomfy.main.compose.RecomfySurface
import com.p4r4d0x.recomfy.theme.RecomfyTheme


@Composable
fun SearchScreen(
    viewModel: MainViewModel,
    onOpenDetail: (ItemDataBo) -> Unit,
    modifier: Modifier = Modifier,
    state: SearchState = rememberSearchState()
) {

    Column {
        Spacer(modifier = Modifier.statusBarsPadding())
        SearchBar(
            query = state.query,
            onSearchFocusChange = { state.inputFulfilled = it },
            onQueryChange = { state.query = it },
            onClearQuery = { state.query = TextFieldValue("") },
            searching = state.searching,
            inputFulfilled = state.inputFulfilled
        )

        Button(
            enabled = true, onClick = {
                state.searching = true
                Log.d("ALRALR", "onSearchQuery ${state.query.text}")
                viewModel.searchByTopic(state.query.text)
            }) {
            Text(text = "Search"/*stringResource(R.string.btn_notification_clear)*/)
        }

        RecomfyDivider()

        state.searchResults = viewModel.recommendations.value


        when (state.searchDisplay) {
            SearchDisplay.Input -> SearchSuggestions(
//                suggestions = state.suggestions,
//                onSuggestionSelect = { suggestion -> state.query = TextFieldValue(suggestion) }
            )
            SearchDisplay.Results -> SearchResults(

                recommendations = state.searchResults,
                onOpenDetail = onOpenDetail
            )
            SearchDisplay.NoResults -> NoResults(state.query.text)
            SearchDisplay.Searching -> TODO()
        }
    }

}

@Composable
fun SearchSuggestions() {

}

@Composable
private fun SearchHint() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    ) {
        Icon(
            imageVector = Icons.Outlined.Search,
            tint = RecomfyTheme.colors.brand,
            contentDescription = ""/*stringResource(R.string.label_search)*/
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = "Escribe un artista o película"/*stringResource(R.string.search_)*/,
            color = RecomfyTheme.colors.textHelp
        )
    }
}


@Composable
private fun SearchBar(
    query: TextFieldValue,
    onQueryChange: (TextFieldValue) -> Unit,
    inputFulfilled: Boolean,
    onSearchFocusChange: (Boolean) -> Unit,
    onClearQuery: () -> Unit,
    searching: Boolean,
    modifier: Modifier = Modifier
) {
    RecomfySurface(
        color = RecomfyTheme.colors.uiFloated,
        contentColor = RecomfyTheme.colors.textSecondary,
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Box(Modifier.fillMaxSize()) {
            if (query.text.isEmpty() && !inputFulfilled) {
                SearchHint()
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
            ) {
                if (inputFulfilled) {
                    IconButton(onClick = onClearQuery) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            tint = RecomfyTheme.colors.iconPrimary,
                            contentDescription = "stringResource(R.string.label_back)"
                        )
                    }
                }
                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    modifier = Modifier
                        .weight(1f)
                        .onFocusChanged {
                            onSearchFocusChange(it.isFocused)
                        }
                )
                if (searching) {
                    CircularProgressIndicator(
                        color = RecomfyTheme.colors.iconPrimary,
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                            .size(36.dp)
                    )
                } else {
                    Spacer(Modifier.width(48.dp))
                }
            }
        }
    }
}


