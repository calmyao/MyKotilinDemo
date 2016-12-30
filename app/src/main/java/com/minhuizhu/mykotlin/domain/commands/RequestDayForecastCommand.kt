package com.minhuizhu.mykotlin.domain.commands

import com.minhuizhu.mykotlin.domain.datasources.ForecastProvider
import com.minhuizhu.mykotlin.domain.model.Forecast

/**
 * Created by minhui.zhu on 2016/12/29.
 */
class RequestDayForecastCommand (
        val id:Long,
        val forecastProvider: ForecastProvider =ForecastProvider() ):
       Command<Forecast>{
    override fun execute() = forecastProvider.requestForecast(id)
}