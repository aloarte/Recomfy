package com.p4r4d0x.recomfy.di

import com.p4r4d0x.data.datasources.BandMetadataDatasource
import com.p4r4d0x.data.datasources.MovieMetadataDatasource
import com.p4r4d0x.data.datasources.SearchDatasource
import com.p4r4d0x.data.datasources.impl.BandMetadataDatasourceImpl
import com.p4r4d0x.data.datasources.impl.MovieMetadataDatasourceImpl
import com.p4r4d0x.data.datasources.impl.SearchDatasourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindSearchDatasource(impl: SearchDatasourceImpl): SearchDatasource

    @Binds
    abstract fun bindBandMetadataDatasource(impl: BandMetadataDatasourceImpl): BandMetadataDatasource

    @Binds
    abstract fun bindMovieMetadataDatasource(impl: MovieMetadataDatasourceImpl): MovieMetadataDatasource


}
