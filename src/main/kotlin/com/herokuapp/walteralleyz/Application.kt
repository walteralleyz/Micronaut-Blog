package com.herokuapp.walteralleyz

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("com.herokuapp.walteralleyz")
		.start()
}

