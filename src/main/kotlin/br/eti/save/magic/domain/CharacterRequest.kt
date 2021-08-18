package br.eti.save.magic.domain

import io.swagger.annotations.ApiModelProperty
import lombok.NoArgsConstructor

@NoArgsConstructor
data class CharacterRequest(
    @ApiModelProperty(value = "Character name", example = "Harry Potter")
    val name: String = "",
    @ApiModelProperty(value = "Character role", example = "student")
    val role: String = "",
    @ApiModelProperty(value = "School name", example = "Hogwarts School of Witchcraft and Wizardry")
    val school: String = "",
    @ApiModelProperty(value = "House identifier", example = "1760529f-6d51-4cb1-bcb1-25087fce5bde")
    val house: String = "",
    @ApiModelProperty(value = "Patronous", example = "stag")
    val patronus: String = ""
)
