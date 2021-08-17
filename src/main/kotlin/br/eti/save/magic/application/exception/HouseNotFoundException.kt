package br.eti.save.magic.application.exception

class HouseNotFoundException(override val message: String = "House not found"): RuntimeException()
