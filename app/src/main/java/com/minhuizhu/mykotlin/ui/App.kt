package com.minhuizhu.mykotlin.ui

import android.app.Application
import com.minhuizhu.mykotlin.extension.DelegatesExt

/**
 * Created by minhui.zhu on 2016/12/29.
 */
class App : Application() {
    companion object{
        var instance: App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance =this
    }
}