package com.example.ucp_2.repository

import com.example.ucp_2.data.dao.BarangDao
import com.example.ucp_2.data.entity.Barang
import kotlinx.coroutines.flow.Flow

class LocalRepositoryBrg {
    private val barangDao: BarangDao
    ) : RepositoryBrg {
        override suspend fun insertBrg(barang: Barang) {
            barangDao.insertBrg(barang)
        }

        override suspend fun deleteBrg(barang: Barang) {
            barangDao.deleteBrg(barang)
        }

        override fun getAllBrg(): Flow<List<Barang>> =
            barangDao.getAllBarang()

        override fun getBrg(nama: String): Flow<Barang> =
            barangDao.getBarang(nama)

        override suspend fun updateBrg(barang: Barang) {
            barangDao.updateBarang(barang)
        }
    }
}