package com.zenith.projectdelta.utils

sealed class Response<T>(var data: T? = null, var errorMessage: String? = null) {
    class Loading<T> : Response<T>()
    class Success<T>(data: T? = null) : Response<T>(data = data)
    class Error<T>(errorMessage: String?) : Response<T>(errorMessage = errorMessage)
}
