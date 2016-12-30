package com.minhuizhu.mykotlin.extension

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


/**
 * Created by minhui.zhu on 2016/12/29.
 */
object DelegatesExt {
    fun <T> notNullSingleValue(): ReadWriteProperty<Any?, T> = NotNullSingleValueVar()
    fun preference(context: Context, name: String, default: Long) = Preference(context, name, default)

}

private class NotNullSingleValueVar<T>() : ReadWriteProperty<Any?, T> {
    private var value: T? = null
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException("${property.name} not initialized")
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else throw IllegalStateException("${property.name} already initialized")
    }

}

class Preference<T>(val context: Context, val name: String, val default: T) : ReadWriteProperty<Any?, T> {
    val prefs by lazy { context.getSharedPreferences("default", Context.MODE_PRIVATE) }
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(name, default)
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> findPreference(name: String, default: T): T = with(prefs) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            else -> throw IllegalArgumentException("this type can be saved into Preference")
        }
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}














































































