package com.herokuapp.walteralleyz.article

import com.herokuapp.walteralleyz.person.Person
import com.herokuapp.walteralleyz.tag.Tag
import io.micronaut.core.annotation.Introspected
import org.hibernate.validator.constraints.br.CPF
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Introspected
class ArticleRequest(
    @field:NotBlank val title: String,
    @field:NotNull val author_id: Long,
    @field:NotBlank @field:Size(max = 2000) val text: String,
    val tagList: List<Tag>?
) {

    fun toModel(author: Person): Article {
        return Article(
            title,
            author,
            text,
            LocalDateTime.now(),
            tagList
        )
    }
}
