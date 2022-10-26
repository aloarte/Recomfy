package com.p4r4d0x.recomfy.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.p4r4d0x.domain.RecommendationType
import com.p4r4d0x.domain.models.ItemDataBo
import com.p4r4d0x.domain.models.RecommendationsBo
import com.p4r4d0x.domain.usecase.GetBandDataUseCase
import com.p4r4d0x.domain.usecase.GetRecommendationsUseCase
import com.p4r4d0x.recomfy.CoroutinesTestRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*

class MainViewModelTest {

    companion object {
        private const val QUERY_TOPIC = "Nirvana"
        private const val RESULT_NAME_BAND = "Foo Fighters"
        private const val RESULT_NAME_MOVIE = "Pulp Fiction"
        val recommendations = RecommendationsBo(
            info = listOf(ItemDataBo(name = QUERY_TOPIC, type = RecommendationType.music)),
            similar = listOf(
                ItemDataBo(
                    name = RESULT_NAME_BAND,
                    type = RecommendationType.music
                ),
                ItemDataBo(
                    name = RESULT_NAME_MOVIE,
                    type = RecommendationType.movie
                )
            )
        )
    }

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var getRecommendationsUseCase: GetRecommendationsUseCase

    @MockK
    private lateinit var getBandDataUseCase: GetBandDataUseCase

    private lateinit var viewModel: MainViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun onStart() {

        MockKAnnotations.init(this)
        viewModel = MainViewModel(getRecommendationsUseCase, getBandDataUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `call search by topic and the result returned is null`() =
        coroutinesTestRule.runBlockingTest {
            val recommendations = slot<(RecommendationsBo?) -> Unit>()
            coEvery {
                getRecommendationsUseCase.invoke(
                    scope = any(),
                    resultCallback = capture(recommendations),
                    params = GetRecommendationsUseCase.Params(queryTopics = QUERY_TOPIC)
                )
            } answers {
                recommendations.captured(null)
            }


            viewModel.searchByTopic(QUERY_TOPIC)

            Assert.assertNull(viewModel.recommendations.value)
        }

    @Test
    fun `call search by topic and the result returned is a valid answer`() {
        coEvery { getRecommendationsUseCase.run(GetRecommendationsUseCase.Params(queryTopics = QUERY_TOPIC)) } returns recommendations

        viewModel.searchByTopic(QUERY_TOPIC)

        Assert.assertEquals(recommendations, viewModel.recommendations.value)
    }

    @Test
    fun fetchArtistData() {
    }
}