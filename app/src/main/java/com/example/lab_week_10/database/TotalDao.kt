package com.example.lab_week_10.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao // Declare a Dao
interface TotalDao {

    @Insert
    fun insert(total: Total)

    @Update
    fun update(total: Total)

    // Query untuk mengambil data berdasarkan ID (kita hanya akan mengambil ID 1)
    @Query("SELECT * FROM total WHERE id = :id")
    fun getTotal(id: Long): List<Total>
}