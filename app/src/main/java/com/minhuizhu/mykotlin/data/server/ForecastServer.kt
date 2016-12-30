package com.minhuizhu.mykotlin.data.server

import com.minhuizhu.mykotlin.data.db.ForecastDb
import com.minhuizhu.mykotlin.domain.datasources.ForecastDataSource
import com.minhuizhu.mykotlin.domain.model.ForecastList

/**
 * Created by minhui.zhu on 2016/12/30.
 */
class ForecastServer(val dataMapper: ServerDataMapper = ServerDataMapper(),
                     val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {
    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

    override fun requestDayForecast(id: Long) =
            throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
}