package com.example.ucp_2.repository.suplier

import com.example.ucp_2.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

interface RepositorySup {
    suspend fun insertSuplier(suplier: Suplier)

    fun getAllSuplier(): Flow<List<Suplier>>

    fun getSuplierNama(): Flow<List<String>>

}