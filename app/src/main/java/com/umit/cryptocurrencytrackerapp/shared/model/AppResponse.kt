package com.umit.cryptocurrencytrackerapp.shared.model

class AppResponse<T> (
    var result: T?,
    var error: Error?
)
