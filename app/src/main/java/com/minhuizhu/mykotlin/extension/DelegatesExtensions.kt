package com.minhuizhu.mykotlin.extension

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


/**
 * Created by minhui.zhu on 2016/12/29.
 */
object DelegatesExt {
    fun preference(context: Context, name: String, default: Long) = Preference(context, name, default)
    fun <T> notNullSingleValue(): ReadWriteProperty<Any?, T> = NotNullSingleValueVar()

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

class Preference(context: Context, name: String, default: Long) {

}





















