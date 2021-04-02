package com.herokuapp.walteralleyz.article

import com.herokuapp.walteralleyz.person.Person
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/api/article")
class ArticleController(val entityManager: EntityManager) {

    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    fun create(@Body @Valid request: ArticleRequest): HttpResponse<ArticleResponse> {
        val author: Person = entityManager.find(Person::class.java, request.author_id);

        return entityManager.merge(request.toModel(author))
            .let { HttpResponse.created(it.toResponse()) }
    }

    @Get
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    fun getAll(pageable: Pageable): HttpResponse<Page<ArticleResponse>> {
        return entityManager
            .createQuery("from Article", Article::class.java)
            .resultList
            .map { it.toResponse() }
            .let {
                HttpResponse.ok(Page.of(it, pageable, it.size.toLong()))
            }
    }

    @Get("/{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    fun getById(@PathVariable id: Long): HttpResponse<ArticleResponse> {
        return entityManager
            .find(Article::class.java, id)
            .let { HttpResponse.ok(it.toResponse()) }
    }
}