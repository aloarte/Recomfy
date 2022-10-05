package com.p4r4d0x.recomfy.di

import com.p4r4d0x.data.api.TheAudioDbApi
import com.p4r4d0x.data.api.OmdbApi
import com.p4r4d0x.data.api.TasteDiveApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @Named("TasteDive")
    fun provideRetrofitTasteDive(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://tastedive.com")
            .client(getRetrofitClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Named("TheAudioDb")
    fun provideRetrofitTheAudioDb(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.theaudiodb.com")
            .client(getRetrofitClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    @Named("OMDB")
    fun provideRetrofitOMDB(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(" https://www.omdbapi.com")
            .client(getRetrofitClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getRetrofitClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()
    }

    @Singleton
    @Provides
    fun provideRecommendationsApiClient(@Named("TasteDive") retrofit: Retrofit): TasteDiveApi {
        return retrofit.create(TasteDiveApi::class.java)
    }

    @Singleton
    @Provides
    fun provideBandMetadataApiClient(@Named("TheAudioDb") retrofit: Retrofit): TheAudioDbApi {
        return retrofit.create(TheAudioDbApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieMetadataApiClient(@Named("OMDB") retrofit: Retrofit): OmdbApi {
        return retrofit.create(OmdbApi::class.java)
    }
}