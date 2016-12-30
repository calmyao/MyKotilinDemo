package com.minhuizhu.mykotlin.data.server

import com.google.gson.Gson
import java.net.URL

/**
 * Created by minhui.zhu on 2016/12/30.
 */
class ForecastByZipCodeRequest(val zipCode:Long,val gson:Gson= Gson()) {
    companion object{
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$BASE_URL&APPID=$APP_ID&q="
    }

    fun execute():ForecastResult{
        val forecastJsonStr= URL(COMPLETE_URL+zipCode).readText()
        return gson.fromJson(forecastJsonStr,ForecastResult::class.java)
    }
}