package com.example.ucp_2.data.dao

import androidx.room.Insert
import androidx.room.Query
import com.example.ucp_2.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

interface SuplierDao {
    @Insert
    suspend fun insertSuplier(suplier: Suplier)

    @Query
        ("SELECT * FROM suplier ORDER BY nama ASC")
    fun getAllSuplier() : Flow<List<Suplier>>

    @Query
        ("SELECT * FROM barang WHERE nama = :nama")
    fun getBarang (nama: String) : Flow<Suplier>
}