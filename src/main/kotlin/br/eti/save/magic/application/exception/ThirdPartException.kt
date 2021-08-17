package br.eti.save.magic.application.exception

class ThirdPartException(override val message: String,
                         override val cause: Throwable) : RuntimeException()
