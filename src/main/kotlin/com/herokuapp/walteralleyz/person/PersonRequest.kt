package com.herokuapp.walteralleyz.person

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class PersonRequest(
    @field:NotBlank val name: String,
    @field:NotBlank val description: String,
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank @field:Size(max = 10) val password: String,
    @field:NotBlank val picture: String
) {
    fun toModel(): Person {
        return Person(
            name,
            description,
            email,
            password,
            null,
            picture
        )
    }
}
