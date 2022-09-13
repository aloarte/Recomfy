package com.p4r4d0x.recomfy.di

import com.p4r4d0x.data.api.RecommendationsApi
import com.p4r4d0x.data.repositories.SearchRepositoryImpl
import com.p4r4d0x.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

//    @Singleton
//    @Provides
//    fun provideRetrofit(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl("")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideRecommendationsApiClient(retrofit: Retrofit):RecommendationsApi {
//        return retrofit.create(RecommendationsApi::class.java)
//    }

}