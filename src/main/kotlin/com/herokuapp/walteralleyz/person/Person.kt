package com.herokuapp.walteralleyz.person

import javax.persistence.*

@Entity
@Table(name = "people")
class Person(
    val name: String,
    val description: String,
    val email: String,

    val password: String,

    val role: String? = "client",
    val picture: String
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null;

    fun toResponse(): PersonResponse = PersonResponse(name, description, email)
}
