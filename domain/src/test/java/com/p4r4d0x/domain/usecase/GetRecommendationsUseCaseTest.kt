package com.p4r4d0x.domain.usecase

import com.p4r4d0x.domain.CoroutinesTestRule
import com.p4r4d0x.domain.RecommendationType
import com.p4r4d0x.domain.models.BandBo
import com.p4r4d0x.domain.models.ItemDataBo
import com.p4r4d0x.domain.models.MovieBo
import com.p4r4d0x.domain.models.RecommendationsBo
import com.p4r4d0x.domain.repository.BandMetadataRepository
import com.p4r4d0x.domain.repository.MovieMetadataRepository
import com.p4r4d0x.domain.repository.SearchRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetRecommendationsUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @RelaxedMockK
    private lateinit var searchRepository: SearchRepository

    @RelaxedMockK
    private lateinit var bandMetadataRepository: BandMetadataRepository

    @RelaxedMockK
    private lateinit var movieMetadataRepository: MovieMetadataRepository

    private lateinit var getRecommendationsUseCase: GetRecommendationsUseCase

    companion object {
        private const val QUERY_TOPIC = "Nirvana"
        private const val RESULT_NAME_BAND = "Foo Fighters"
        private const val RESULT_NAME_MOVIE = "Pulp Fiction"
        private const val BAND_GENRE = "Grunge"
        private const val MOVIE_GENRE = "Policiaco"
    }

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getRecommendationsUseCase = GetRecommendationsUseCase(
            searchRepository,
            bandMetadataRepository,
            movieMetadataRepository
        )
    }

    @Test
    fun `when the api doesnt find the queried value an empty list is returned`() = runBlocking {
        coEvery { searchRepository.getRecommendations(QUERY_TOPIC) } returns
                mockRecommendationsAnswer(RecommendationsMockedAnswerType.None)

        val recommendations =
            getRecommendationsUseCase.run(params = GetRecommendationsUseCase.Params(QUERY_TOPIC))

        coVerify(exactly = 1) { searchRepository.getRecommendations(QUERY_TOPIC) }
        coVerify(exactly = 0) { movieMetadataRepository.getMovieMetadata(any()) }
        coVerify(exactly = 0) { bandMetadataRepository.getBandMetadata(any()) }
        Assert.assertNotNull(recommendations?.similar)
        Assert.assertTrue(recommendations?.similar?.isEmpty() ?: false)
    }

    @Test
    fun `when the api find the queried value and returns a band recommendation`() = runBlocking {
        coEvery { searchRepository.getRecommendations(QUERY_TOPIC) } returns
                mockRecommendationsAnswer(RecommendationsMockedAnswerType.Band)
        coEvery { bandMetadataRepository.getBandMetadata(RESULT_NAME_BAND) } returns BandBo(
            artistName = RESULT_NAME_BAND,
            genre = BAND_GENRE
        )

        val recommendations =
            getRecommendationsUseCase.run(params = GetRecommendationsUseCase.Params(QUERY_TOPIC))

        val expectedItem = ItemDataBo(
            name = RESULT_NAME_BAND,
            genre = BAND_GENRE,
            type = RecommendationType.music
        )
        coVerify(exactly = 1) { searchRepository.getRecommendations(QUERY_TOPIC) }
        coVerify(exactly = 1) { bandMetadataRepository.getBandMetadata(RESULT_NAME_BAND) }
        coVerify(exactly = 0) { movieMetadataRepository.getMovieMetadata(any()) }
        Assert.assertTrue(recommendations?.similar?.isNotEmpty() ?: false)
        Assert.assertEquals(expectedItem, recommendations?.similar?.firstOrNull())
    }

    @Test
    fun `when the api find the queried value and returns a movie recommendation`() = runBlocking {
        coEvery { searchRepository.getRecommendations(QUERY_TOPIC) } returns
                mockRecommendationsAnswer(RecommendationsMockedAnswerType.Movie)
        coEvery { movieMetadataRepository.getMovieMetadata(RESULT_NAME_MOVIE) } returns MovieBo(
            title = RESULT_NAME_MOVIE,
            genre = MOVIE_GENRE
        )

        val recommendations =
            getRecommendationsUseCase.run(params = GetRecommendationsUseCase.Params(QUERY_TOPIC))

        val expectedItem = ItemDataBo(
            name = RESULT_NAME_MOVIE,
            genre = MOVIE_GENRE,
            type = RecommendationType.movie
        )
        coVerify(exactly = 1) { searchRepository.getRecommendations(QUERY_TOPIC) }
        coVerify(exactly = 1) { movieMetadataRepository.getMovieMetadata(RESULT_NAME_MOVIE) }
        coVerify(exactly = 0) { bandMetadataRepository.getBandMetadata(any()) }
        Assert.assertTrue(recommendations?.similar?.isNotEmpty() ?: false)
        Assert.assertEquals(expectedItem, recommendations?.similar?.firstOrNull())
    }

    @Test
    fun `when the api find the queried value and returns a movie & band recommendations`() =
        runBlocking {
            coEvery { searchRepository.getRecommendations(QUERY_TOPIC) } returns
                    mockRecommendationsAnswer(RecommendationsMockedAnswerType.Both)
            coEvery { bandMetadataRepository.getBandMetadata(RESULT_NAME_BAND) } returns BandBo(
                artistName = RESULT_NAME_BAND,
                genre = BAND_GENRE
            )
            coEvery { movieMetadataRepository.getMovieMetadata(RESULT_NAME_MOVIE) } returns MovieBo(
                title = RESULT_NAME_MOVIE,
                genre = MOVIE_GENRE
            )

            val recommendations =
                getRecommendationsUseCase.run(params = GetRecommendationsUseCase.Params(QUERY_TOPIC))

            val expectedItemBand = ItemDataBo(
                name = RESULT_NAME_BAND,
                genre = BAND_GENRE,
                type = RecommendationType.music
            )
            val expectedItemMovie = ItemDataBo(
                name = RESULT_NAME_MOVIE,
                genre = MOVIE_GENRE,
                type = RecommendationType.movie
            )
            coVerify(exactly = 1) { searchRepository.getRecommendations(QUERY_TOPIC) }
            coVerify(exactly = 1) { movieMetadataRepository.getMovieMetadata(RESULT_NAME_MOVIE) }
            coVerify(exactly = 1) { bandMetadataRepository.getBandMetadata(RESULT_NAME_BAND) }
            Assert.assertTrue(recommendations?.similar?.isNotEmpty() ?: false)
            Assert.assertEquals(expectedItemBand, recommendations?.similar?.get(0))
            Assert.assertEquals(expectedItemMovie, recommendations?.similar?.get(1))

        }

    private fun mockRecommendationsAnswer(
        answerType: RecommendationsMockedAnswerType
    ) =
        when (answerType) {
            RecommendationsMockedAnswerType.None -> RecommendationsBo(
                info = listOf(ItemDataBo(name = QUERY_TOPIC, type = RecommendationType.unknown)),
                similar = emptyList()
            )
            RecommendationsMockedAnswerType.Both -> RecommendationsBo(
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
            RecommendationsMockedAnswerType.Band -> RecommendationsBo(
                info = listOf(ItemDataBo(name = QUERY_TOPIC, type = RecommendationType.music)),
                similar = listOf(
                    ItemDataBo(
                        name = RESULT_NAME_BAND,
                        type = RecommendationType.music
                    )
                )
            )
            RecommendationsMockedAnswerType.Movie -> RecommendationsBo(
                info = listOf(ItemDataBo(name = QUERY_TOPIC, type = RecommendationType.movie)),
                similar = listOf(
                    ItemDataBo(
                        name = RESULT_NAME_MOVIE,
                        type = RecommendationType.movie
                    )
                )
            )
        }

    enum class RecommendationsMockedAnswerType {
        None, Both, Band, Movie
    }
}


