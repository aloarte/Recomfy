package com.p4r4d0x.data

sealed class BackendResult<T> {
    data class Success<T>(val data: T) : BackendResult<T>()
    data class Error<T>(val code: Int, val error: String? = "", val exception: Exception? = null) :
        BackendResult<T>()
}