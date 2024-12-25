package com.example.ucp_2.repository.barang

import com.example.ucp_2.data.dao.BarangDao
import com.example.ucp_2.data.entity.Barang

import kotlinx.coroutines.flow.Flow

class LocalRepositoryBrg(private val barangDao: BarangDao)
    : RepositoryBrg {
    override suspend fun insertBrg(barang: Barang) {
        barangDao.insertBarang(barang)
    }

    override suspend fun deleteBrg(barang: Barang) {
        barangDao.deleteBarang(barang)
    }

    override suspend fun updateBrg(barang: Barang) {
        barangDao.updateBarang(barang)
    }

    override fun getAllBarang(): Flow<List<Barang>> =
        barangDao.getAllBarang()

    override fun getBarang(id: Int): Flow<Barang> =
        barangDao.getBarang(id)


}