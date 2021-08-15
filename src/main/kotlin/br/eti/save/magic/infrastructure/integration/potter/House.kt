package br.eti.save.magic.infrastructure.integration.potter

import java.util.*

data class House (
    val id: String = "",
    val name : String = ""
)

data class HouseWrapper (
    val  houses: List<House> = Collections.emptyList()
)
