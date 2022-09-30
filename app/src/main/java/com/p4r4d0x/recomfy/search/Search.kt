package com.p4r4d0x.recomfy.search

import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import com.p4r4d0x.domain.models.RecommendationsBo

@Composable
fun rememberSearchState(
    query: TextFieldValue = TextFieldValue(""),
    searching: Boolean = false,
    inputFulfilled: Boolean = false,
    searchResults: RecommendationsBo? = null
): SearchState {
    return remember {
        SearchState(
            query = query,
            searching = searching,
            inputFulfilled = inputFulfilled,
            searchResults = searchResults
        )
    }
}

@Stable
class SearchState(
    query: TextFieldValue,
    searching: Boolean,
    inputFulfilled: Boolean,
    searchResults: RecommendationsBo?
) {
    var query by mutableStateOf(query)
    var searching by mutableStateOf(searching)
    var inputFulfilled by mutableStateOf(inputFulfilled)
    var searchResults by mutableStateOf(searchResults)
    val searchDisplay: SearchDisplay
        get() = when {
            !searching -> SearchDisplay.Input
            searching && searchResults == null -> SearchDisplay.NoResults
            else -> SearchDisplay.Results
        }
}


enum class SearchDisplay {
    Input, Results, NoResults, Searching
}