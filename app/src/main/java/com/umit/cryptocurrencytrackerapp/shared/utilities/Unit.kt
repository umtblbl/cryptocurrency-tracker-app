package com.umit.cryptocurrencytrackerapp.shared.utilities

class Unit : Comparable<Unit> {
    override fun compareTo(other: Unit): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        return other is Unit
    }

    override fun hashCode(): Int {
        return 0
    }

    companion object {
        private val default = Unit()
        fun getDefault() = default
    }
}
