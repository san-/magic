package br.eti.save.magic.configuration

class ThirdPartException(override val message: String,
                         override val cause: Throwable) : RuntimeException()
