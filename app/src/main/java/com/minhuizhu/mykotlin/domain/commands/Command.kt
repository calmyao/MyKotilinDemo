package com.minhuizhu.mykotlin.domain.commands

/**
 * Created by minhui.zhu on 2016/12/29.
 */
interface Command<T> {
    fun execute(): T
}