package com.p4r4d0x.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [MovieMetadataEntity::class], version = 1)
abstract class MetadataDatabase : RoomDatabase() {

    abstract fun movieMetadataDao(): MovieMetadataDao

    abstract fun bandMetadataDao(): BandMetadataDao

}
