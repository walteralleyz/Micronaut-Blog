package com.herokuapp.walteralleyz.tag

import javax.persistence.*

@Entity
@Table(name = "tags")
class Tag(
    val name: String
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null;
}
