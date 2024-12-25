package com.example.ucp_2.ui.navigation

interface HalamanController {
    val route: String
}

object DestinasiHome : HalamanController {
    override val route = "home"
}

object DestinasiHomeSpl : HalamanController {
    override val route = "supplier"
}

object DestinasiInsertSpl : HalamanController {
    override val route = "supplier/add"
}


object DestinasiHomeBrg : HalamanController {
    override val route = "barang"
}

object DestinasiInsertBrg : HalamanController {
    override val route = "barang/add"
}

object DestinasiDetailBrg : HalamanController {
    override val route = "barang"
    const val idBrg = "id"
    val routesWithArg = "$route/{$idBrg}"
}

object DestinasiUpdateBrg : HalamanController {
    override val route = "updateBrg"
    const val idBrg = "id"
    val routesWithArg = "$route/{$idBrg}"
}