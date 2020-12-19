package com.example.spacedeliveryman.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE SpaceStationTable ADD COLUMN IsCompleted INTEGER NOT NULL DEFAULT 0")
    }
}


val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE SpaceStationTable ADD COLUMN IsActive INTEGER NOT NULL DEFAULT 0")
    }
}