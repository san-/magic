package br.eti.save.magic.resource.doc

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@Target(allowedTargets = [AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS])
@Retention(AnnotationRetention.RUNTIME)
@ApiOperation(
    value = "Clean the houses cacha",
    notes = "Endpoint for cache clean to be called from external house's domain",
    code = 202
)
@ApiResponses(
    value = [
        ApiResponse(
            code = 202,
            message = "Cache cleared successfully"
        )
    ]
)
@ResponseStatus(HttpStatus.ACCEPTED)
annotation class CleanCacheDoc
