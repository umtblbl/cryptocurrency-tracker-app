package com.umit.cryptocurrencytrackerapp.shared.model

data class Optional<T>(val value: T? = null)
fun <T> T?.toOptional() = Optional(this)
