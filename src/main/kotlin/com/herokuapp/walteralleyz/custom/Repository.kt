package com.herokuapp.walteralleyz.custom

import io.micronaut.data.annotation.Repository
import io.micronaut.transaction.SynchronousTransactionManager
import java.sql.Connection
import javax.persistence.EntityManager

@Repository
open class Repository(
    val manager: EntityManager,
    val transactionManager: SynchronousTransactionManager<Connection>
) {
    inline fun <reified T> findByEmail(email: String): T {
        return transactionManager.executeRead {
            manager
                .createQuery("from ${T::class.simpleName} where email = :email", T::class.java)
                .setParameter("email", email)
                .singleResult
        }
    }
}