package com.herokuapp.walteralleyz.person

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import io.micronaut.transaction.SynchronousTransactionManager
import io.micronaut.validation.Validated
import java.sql.Connection
import javax.persistence.EntityManager
import javax.validation.Valid

@Validated
@Controller("/api/person")
class PersonController(
    private val manager: EntityManager,
    private val transactionalManager: SynchronousTransactionManager<Connection>
) {

    @Post
    @Secured(SecurityRule.IS_ANONYMOUS)
    fun create(@Body @Valid request: PersonRequest): HttpResponse<PersonResponse> {
        return transactionalManager.executeWrite {
            manager.merge(request.toModel())
                .let { HttpResponse.ok(it.toResponse()) }
        }
    }

    @Get
    @Secured(SecurityRule.IS_ANONYMOUS)
    fun getAll(): HttpResponse<List<PersonResponse>> {
       return transactionalManager.executeRead {
           manager.createQuery("from Person", Person::class.java)
               .resultList.map { it.toResponse() }
               .let { HttpResponse.ok(it) }
       }
    }
}