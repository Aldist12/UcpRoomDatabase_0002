package com.example.ucp_2.depedenciesinjection

import android.content.Context
import com.example.ucp_2.data.database.StoreDatabase
import com.example.ucp_2.repository.LocalRepositoryBrg
import com.example.ucp_2.repository.LocalRepositorySup
import com.example.ucp_2.repository.RepositoryBrg
import com.example.ucp_2.repository.RepositorySup

interface InterfaceContainerApp {

    val repositoryBrg: RepositoryBrg
    val repositorySup: RepositorySup
}

class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val repositoryBrg: RepositoryBrg by lazy {
        LocalRepositoryBrg(StoreDatabase.getDatabase(context).barangDao())
    }
    override val repositorySup: RepositorySup by lazy {
        LocalRepositorySup(StoreDatabase.getDatabase(context).suplierDao())

    }
}