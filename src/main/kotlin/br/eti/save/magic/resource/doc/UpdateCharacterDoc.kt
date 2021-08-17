package br.eti.save.magic.resource.doc

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@Target(allowedTargets = [AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS])
@Retention(AnnotationRetention.RUNTIME)
@ApiOperation(
    value = "Update a character",
    notes = "Update a character if exists with the id",
    code = 200
)
@ApiResponses(
    value = [
        ApiResponse(
            code = 200,
            message = "Character updated successfully"
        ),
        ApiResponse(code = 404, message = "Character not found for update")
    ]
)
@ResponseStatus(HttpStatus.OK)
annotation class UpdateCharacterDoc
