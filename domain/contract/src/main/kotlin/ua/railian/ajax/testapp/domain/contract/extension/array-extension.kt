package ua.railian.ajax.testapp.domain.contract.extension

fun <T> Array<T>.getOrNull(index: Int): T? = when (index) {
    in indices -> get(index)
    else -> null
}
