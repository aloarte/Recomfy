package com.p4r4d0x.recomfy.di

import android.app.Application
import androidx.room.Room
import com.p4r4d0x.data.room.BandMetadataDao
import com.p4r4d0x.data.room.MetadataDatabase
import com.p4r4d0x.data.room.MovieMetadataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(application: Application): MetadataDatabase {
        return Room.databaseBuilder(application, MetadataDatabase::class.java, "metadata_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieMetadataDao(dataBase: MetadataDatabase): MovieMetadataDao {
        return dataBase.movieMetadataDao()
    }

    @Singleton
    @Provides
    fun provideBandMetadataDao(dataBase: MetadataDatabase): BandMetadataDao {
        return dataBase.bandMetadataDao()
    }
}