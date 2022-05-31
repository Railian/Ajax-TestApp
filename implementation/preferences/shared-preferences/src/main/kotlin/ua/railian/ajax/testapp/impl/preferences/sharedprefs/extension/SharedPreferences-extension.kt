package ua.railian.ajax.testapp.impl.preferences.sharedprefs.extension

import android.content.SharedPreferences

inline fun <reified T : Enum<*>> SharedPreferences.getEnum(key: String): T? =
    T::class.java.enumConstants?.getOrNull(getInt("$key:ordinal", -1))

inline fun <reified T : Enum<*>> SharedPreferences.getEnum(key: String, defValue: T): T =
    getEnum(key) ?: defValue

inline fun <reified T : Enum<*>> SharedPreferences.Editor.putEnum(key: String, value: T) {
    putInt("$key:ordinal", value.ordinal)
}