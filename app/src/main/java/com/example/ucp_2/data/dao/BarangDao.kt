package com.example.ucp_2.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp_2.data.entity.Barang
import kotlinx.coroutines.flow.Flow

@Dao
interface BarangDao {
    @Insert
    suspend fun insertBarang(barang: Barang)

    @Query
        ("SELECT * FROM barang ORDER BY namaBarang ASC")
    fun getAllBarang() : Flow<List<Barang>>

    @Query
        ("SELECT * FROM barang WHERE id = :id")
    fun getBarang (id: Int) : Flow<Barang>

    @Delete
    suspend fun deleteBarang(barang: Barang)

    @Update
    suspend fun updateBarang(barang: Barang)
}