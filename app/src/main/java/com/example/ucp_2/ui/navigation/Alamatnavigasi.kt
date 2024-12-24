package com.example.ucp_2.ui.navigation

interface Alamatnavigasi {
    val route: String
}

object DestinasiHome : Alamatnavigasi {
    override val route = "home"
}

object DestinasiHomeSpl : Alamatnavigasi {
    override val route = "supplier"
}

object DestinasiInsertSpl : Alamatnavigasi {
    override val route = "supplier/add"
}


object DestinasiHomeBrg : Alamatnavigasi {
    override val route = "barang"
}

object DestinasiInsertBrg : Alamatnavigasi {
    override val route = "barang/add"
}

object DestinasiDetailBrg : Alamatnavigasi {
    override val route = "barang"
    const val idBrg = "id"
    val routesWithArg = "$route/{$idBrg}"
}

object DestinasiUpdateBrg : Alamatnavigasi {
    override val route = "updateBrg"
    const val idBrg = "id"
    val routesWithArg = "$route/{$idBrg}"
}