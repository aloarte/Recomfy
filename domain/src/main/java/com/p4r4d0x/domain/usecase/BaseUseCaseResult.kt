package com.p4r4d0x.domain.usecase

import kotlinx.coroutines.*

abstract class BaseUseCaseParamsResult<Params, Result> {
    abstract suspend fun run(params: Params): Result

    fun invoke(
        scope: CoroutineScope = GlobalScope,
        dispatcher: CoroutineDispatcher = Dispatchers.Default,
        params: Params,
        resultCallback: (Result) -> Unit = {}
    ) {
        val job = scope.async(dispatcher) { run(params) }
        scope.launch(Dispatchers.Main) { resultCallback(job.await()) }
    }
}



