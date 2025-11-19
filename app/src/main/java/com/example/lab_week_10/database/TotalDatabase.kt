package com.example.lab_week_10.database

import androidx.room.Database
import androidx.room.RoomDatabase

// RoomDatabase, menentukan Entity dan versi schema
@Database(entities = [Total::class], version = 1)
abstract class TotalDatabase : RoomDatabase() {

    // Deklarasi DAO
    abstract fun totalDao(): TotalDao
}