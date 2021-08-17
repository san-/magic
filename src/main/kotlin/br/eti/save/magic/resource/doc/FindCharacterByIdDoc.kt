package br.eti.save.magic.resource.doc

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@Target(allowedTargets = [AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS])
@Retention(AnnotationRetention.RUNTIME)
@ApiOperation(
    value = "Find a character by id",
    notes = "Find the character by id em return its data if exists",
    code = 200
)
@ApiResponses(
    value = [
        ApiResponse(
            code = 200,
            message = "Character found, data returned in response body"
        ),
        ApiResponse(code = 404, message = "Character not found")
    ]
)
@ResponseStatus(HttpStatus.OK)
annotation class FindCharacterByIdDoc
