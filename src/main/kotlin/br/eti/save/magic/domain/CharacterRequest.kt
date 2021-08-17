package br.eti.save.magic.domain

import lombok.NoArgsConstructor

@NoArgsConstructor
data class CharacterRequest(
    val name: String = "",
    val role: String = "",
    val school: String = "",
    val house: String = "",
    val patronus: String = ""
)
