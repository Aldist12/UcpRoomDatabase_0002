package com.example.ucp_2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp_2.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

@Dao
interface SuplierDao {

    @Insert
    suspend fun insertSuplier(suplier: Suplier)

    @Query("SELECT * FROM tblSuplier ORDER BY id")
    fun getAllSuplier(): Flow<List<Suplier>>

    @Query("SELECT nama FROM tblSuplier")
    fun getSuplierNama(): Flow<List<String>>

}