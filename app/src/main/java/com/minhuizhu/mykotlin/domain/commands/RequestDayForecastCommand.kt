package com.minhuizhu.mykotlin.domain.commands

/**
 * Created by minhui.zhu on 2016/12/29.
 */
class RequestDayForecastCommand (
        val id:Long,
        val forecastProvider:ForecastProvider =ForecastProvider() ):
       Command<Forecast>{
    override fun execute(): = forecastProvider.requestForecast(id)
}