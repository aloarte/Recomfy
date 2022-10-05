package com.p4r4d0x.data.room

import androidx.room.*

@Dao
interface MovieMetadataDao {

    @Query("SELECT * FROM movies_metadata_table ORDER BY title DESC")
    suspend fun getAllMetadata(): List<MovieMetadataEntity>

    @Query("DELETE FROM movies_metadata_table")
    suspend fun removeAllMetadata()

    @Query("SELECT * FROM movies_metadata_table WHERE title = :givenTitle")
    suspend fun getMetadataFrom(givenTitle: String): MovieMetadataEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMetadata(metadata: MovieMetadataEntity): Long

    @Delete
    suspend fun delete(user: MovieMetadataEntity)

}