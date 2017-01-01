package com.minhuizhu.mykotlin.extension

import android.content.Context
import android.support.v4.content.ContextCompat

/**
 * Created by zhuminh on 2016/12/30.
 */
fun Context.color(res:Int):Int=ContextCompat.getColor(this,res)