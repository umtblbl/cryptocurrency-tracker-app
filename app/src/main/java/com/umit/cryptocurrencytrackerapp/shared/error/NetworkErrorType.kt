package com.umit.cryptocurrencytrackerapp.shared.error

sealed class NetworkErrorType : Throwable() {
    class RequestError(override val message: String = "Something went wrong") : NetworkErrorType()
    class Unknown(override val message: String = "Unknown error") : NetworkErrorType()
    class IncorrectDataReturned(override val message: String = "Incorrect JSON format") : NetworkErrorType()
    class NotReachedServer(override val message: String = "Server not found") : NetworkErrorType()
    class IOError(override val message: String = "Resource parse error") : NetworkErrorType()
    class Cancelled(override val message: String = "Cancelled") : NetworkErrorType()
}
