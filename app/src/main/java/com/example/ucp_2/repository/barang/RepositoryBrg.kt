package com.example.ucp_2.repository.barang

import com.example.ucp_2.data.entity.Barang
import kotlinx.coroutines.flow.Flow

interface RepositoryBrg {
                suspend fun insertBrg(barang: Barang)

                suspend fun deleteBrg(barang: Barang)

                suspend fun updateBrg(barang: Barang)

                fun getAllBarang(): Flow<List<Barang>>

                fun getBarang(id: Int): Flow<Barang>

        }