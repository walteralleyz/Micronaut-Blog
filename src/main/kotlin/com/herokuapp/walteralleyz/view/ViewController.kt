package com.herokuapp.walteralleyz.view

import com.herokuapp.walteralleyz.article.Article
import com.herokuapp.walteralleyz.article.ArticleResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.views.ModelAndView
import io.micronaut.views.View
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.constraints.NotBlank

@Controller("/")
open class ViewController(private val manager: EntityManager) {

    @Get
    @View("index")
    fun index(): HttpResponse<Void> {
        return HttpResponse.ok()
    }

    @Get("/post/{id}")
    @Transactional
    open fun post(@PathVariable id: Long): ModelAndView<ArticleResponse?> {
        return try {
            ModelAndView("post", manager.find(Article::class.java, id).toResponse());
        } catch (e: Exception) { ModelAndView("notfound", null); }
    }
}