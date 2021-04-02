package com.herokuapp.walteralleyz.article

import com.herokuapp.walteralleyz.tag.Tag
import java.time.LocalDateTime

data class ArticleResponse(
    val id: Long,
    val title: String,
    val authorName: String,
    var lastUpdated: LocalDateTime,
    val text: String,
    val tags: List<Tag>?
)