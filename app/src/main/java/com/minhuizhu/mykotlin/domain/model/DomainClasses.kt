package com.minhuizhu.mykotlin.domain.model

/**
 * Created by minhui.zhu on 2016/12/30.
 */
data class Forecast(val id: Long, val date: Long, val description: String, val high: Int, val low: Int, val iconUrl: String)

data class ForecastList(val id: Long, val city: String, val country: String, val dailyForecast: List<Forecast>) {
    operator fun get(position: Int) = dailyForecast[position]
    fun size() = dailyForecast.size
}