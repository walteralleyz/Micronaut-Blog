package com.herokuapp.walteralleyz.article

import com.herokuapp.walteralleyz.person.Person
import com.herokuapp.walteralleyz.tag.Tag
import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "articles")
class Article(
    val title: String,

    @ManyToOne
    val author: Person,
    val text: String,

    val lastUpdated: LocalDateTime,

    @ManyToMany(cascade = [CascadeType.MERGE])
    @LazyCollection(LazyCollectionOption.FALSE)
    val tags: List<Tag>?,
) {
    @Id @GeneratedValue()
    val id: Long? = null;

    fun toResponse(): ArticleResponse {
        return ArticleResponse(
            id!!,
            title,
            author.name,
            lastUpdated,
            text,
            tags
        )
    }
}