package com.minhuizhu.mykotlin.domain.commands

import com.minhuizhu.mykotlin.domain.datasources.ForecastProvider
import com.minhuizhu.mykotlin.domain.model.ForecastList

/**
 * Created by minhui.zhu on 2016/12/30.
 */
class RequestForecastCommand(
        val zipCode: Long,
        val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<ForecastList> {
    companion object {
        val DAYS = 7
    }

    override fun execute() = forecastProvider.requestByZipCode(zipCode, DAYS)

}