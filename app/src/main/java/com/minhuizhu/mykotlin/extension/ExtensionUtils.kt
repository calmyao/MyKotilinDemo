package com.minhuizhu.mykotlin.extension

import java.text.DateFormat
import java.util.*

/**
 * Created by zhuminh on 2016/12/30.
 */
fun Long.toDateString(dateFormat:Int =DateFormat.MEDIUM):String{
    val df=DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}