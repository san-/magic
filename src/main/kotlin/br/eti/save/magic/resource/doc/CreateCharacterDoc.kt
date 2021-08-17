package br.eti.save.magic.resource.doc

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@Target(allowedTargets = [AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS])
@Retention(AnnotationRetention.RUNTIME)
@ApiOperation(
    value = "Create a new character",
    notes = "Validates the house code and creates the character",
    code = 201
)
@ApiResponses(
    value = [
        ApiResponse(
            code = 201,
            message = "Character created"
        ),
        ApiResponse(code = 400, message = "Invalid house code")
    ]
)
@ResponseStatus(HttpStatus.CREATED)
annotation class CreateCharacterDoc
