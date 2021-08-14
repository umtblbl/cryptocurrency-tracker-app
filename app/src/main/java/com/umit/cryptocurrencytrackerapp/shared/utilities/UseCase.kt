package com.umit.cryptocurrencytrackerapp.shared.utilities

abstract class UseCase<out Type : Any, in Params> {
    abstract operator fun invoke(params: Params? = null): Type

    object None {
        override fun toString() = "UseCase.None"
    }
}
