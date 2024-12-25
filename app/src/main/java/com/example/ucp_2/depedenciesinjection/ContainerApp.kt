package com.example.ucp_2.depedenciesinjection

import android.content.Context
import com.example.ucp_2.data.database.StoreDatabase
import com.example.ucp_2.repository.barang.LocalRepositoryBrg
import com.example.ucp_2.repository.suplier.LocalRepositorySup
import com.example.ucp_2.repository.barang.RepositoryBrg
import com.example.ucp_2.repository.suplier.RepositorySup

interface InterfaceContainerApp {
    val repositorySup : RepositorySup
    val repositoryBrg : RepositoryBrg
}

class ContainerApp (private val context : Context) :
    InterfaceContainerApp {
    override val repositoryBrg: RepositoryBrg by lazy {
        LocalRepositoryBrg(StoreDatabase.getDatabase(context).barangDao())
    }
    override val repositorySup: RepositorySup by lazy {
        LocalRepositorySup(StoreDatabase.getDatabase(context).suplierDao())
    }
}