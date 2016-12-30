package com.minhuizhu.mykotlin.domain.datasources

import com.minhuizhu.mykotlin.domain.model.Forecast
import com.minhuizhu.mykotlin.domain.model.ForecastList

/**
 * Created by minhui.zhu on 2016/12/30.
 */
interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
    fun requestDayForecast(id: Long): Forecast?
}