package br.eti.save.magic.application.configuration

import br.eti.save.magic.application.exception.CharacterNotFoundException
import br.eti.save.magic.application.exception.HouseNotFoundException
import br.eti.save.magic.application.exception.ThirdPartException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest


@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(HouseNotFoundException::class)
    protected fun handleInvalidHouse(ex: HouseNotFoundException, request: WebRequest?): ResponseEntity<Any?>? {
        return ResponseEntity.badRequest().body(ExceptionMessage(ex.message))
    }

    @ExceptionHandler(CharacterNotFoundException::class)
    protected fun handleCharacterNotFound(ex: CharacterNotFoundException, request: WebRequest?): ResponseEntity<Any?>? {
        return ResponseEntity.notFound().build()
    }


    @ExceptionHandler(ThirdPartException::class)
    protected fun handleThirdPartException(ex: ThirdPartException, request: WebRequest?): ResponseEntity<Any?>? {
        return ResponseEntity.badRequest().body(ExceptionMessage("${ex.message}: ${ex.cause.javaClass.name}"))
    }
}

data class ExceptionMessage(val message: String)
