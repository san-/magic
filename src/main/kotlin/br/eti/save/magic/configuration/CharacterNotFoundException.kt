package br.eti.save.magic.configuration

class CharacterNotFoundException(override val message: String = "Character not found"): RuntimeException()
