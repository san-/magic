package br.eti.save.magic.domain

import lombok.NoArgsConstructor

@NoArgsConstructor
data class CharacterResponse(
    val id: String = "",
    val name: String = "",
    val role: String = "",
    val school: String = "",
    val house: String = "",
    val patronus: String = ""
)
