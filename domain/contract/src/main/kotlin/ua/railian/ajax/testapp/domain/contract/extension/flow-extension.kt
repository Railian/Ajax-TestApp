package ua.railian.ajax.testapp.domain.contract.extension

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

fun <T> Flow<T>.launchOnCollect(
    action: suspend CoroutineScope.() -> Unit
): Flow<T> = object : Flow<T> {
    override suspend fun collect(collector: FlowCollector<T>) {
        coroutineScope {
            launch { this@launchOnCollect.collect(collector) }
            launch { action.invoke(this) }
        }
    }
}