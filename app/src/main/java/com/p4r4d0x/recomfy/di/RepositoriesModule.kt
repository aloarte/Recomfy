package com.p4r4d0x.recomfy.di

import com.p4r4d0x.data.repositories.SearchRepositoryImpl
import com.p4r4d0x.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindSearchRepository(impl: SearchRepositoryImpl): SearchRepository
}