package br.eti.save.magic.model

data class CharacterCreateRequest(
    val name: String,
    val role: String,
    val school: String,
    val house: String,
    val patronus: String
)
