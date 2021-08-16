package br.eti.save.magic.configuration

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

}

data class ExceptionMessage(val message: String)
