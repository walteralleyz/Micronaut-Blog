package com.herokuapp.walteralleyz.secure

import com.herokuapp.walteralleyz.person.Person
import com.herokuapp.walteralleyz.custom.Repository
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.*
import io.reactivex.Flowable
import org.reactivestreams.Publisher
import javax.inject.Singleton

@Singleton
open class CustomAuthProvider(
    private val repository: Repository
): AuthenticationProvider {

    override fun authenticate(
        httpReq: HttpRequest<*>?,
        authReq: AuthenticationRequest<*, *>?
    ): Publisher<AuthenticationResponse> {
        val username: String = authReq?.identity.toString();
        val password: String = authReq?.secret.toString();

        repository.findByEmail<Person>(username).let {
            if(it.password == password) {
                val details = UserDetails(username, listOf("client"))
                return Flowable.just(details)
            }

            return Flowable.just(AuthenticationFailed())
        }
    }
}