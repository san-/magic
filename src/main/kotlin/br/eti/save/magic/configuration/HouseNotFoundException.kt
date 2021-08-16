package br.eti.save.magic.configuration

class HouseNotFoundException(override val message: String = "House not found"): RuntimeException()
