package com.example.ucp_2.repository.suplier

import com.example.ucp_2.data.entity.Suplier
import com.example.ucp_2.data.dao.SuplierDao

import kotlinx.coroutines.flow.Flow

class LocalRepositorySup (
    private val supplierDao: SuplierDao
) : RepositorySup {

    override suspend fun insertSuplier(suplier: Suplier) {
        supplierDao.insertSuplier(suplier)
    }

    override fun getAllSuplier(): Flow<List<Suplier>>  =
        supplierDao.getAllSuplier()

    override fun getSuplierNama(): Flow<List<String>> =
        supplierDao.getSuplierNama()
}