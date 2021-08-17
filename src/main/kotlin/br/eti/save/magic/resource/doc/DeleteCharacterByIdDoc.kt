package br.eti.save.magic.resource.doc

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@Target(allowedTargets = [AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS])
@Retention(AnnotationRetention.RUNTIME)
@ApiOperation(
    value = "Delete a character by id",
    notes = "Delete a character if exists with the id",
    code = 202
)
@ApiResponses(
    value = [
        ApiResponse(
            code = 202,
            message = "Character deleted successfully"
        ),
        ApiResponse(code = 404, message = "Character not found for delete")
    ]
)
@ResponseStatus(HttpStatus.ACCEPTED)
annotation class DeleteCharacterByIdDoc
