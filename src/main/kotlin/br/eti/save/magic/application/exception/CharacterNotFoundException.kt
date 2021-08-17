package br.eti.save.magic.application.exception

class CharacterNotFoundException(override val message: String = "Character not found"): RuntimeException()
