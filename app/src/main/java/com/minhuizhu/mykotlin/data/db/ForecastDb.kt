package com.minhuizhu.mykotlin.data.db

import com.minhuizhu.mykotlin.domain.datasources.ForecastDataSource
import com.minhuizhu.mykotlin.domain.model.ForecastList
import com.minhuizhu.mykotlin.extension.*
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.insert
import java.util.*

/**
 * Created by minhui.zhu on 2016/12/30.
 */
class ForecastDb(val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                 val dataMapper: DbDataMapper = DbDataMapper()) : ForecastDataSource {
    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList { DayForecast(HashMap(it)) }

        var city = select(CityForecastTable.NAME).
                whereSimple("${CityForecastTable.ID} = ?", zipCode.toString()).
                parseOpt { CityForecast(HashMap(it), dailyForecast) }
        city?.let { dataMapper.convertToDomain(it) }
    }

    override fun requestDayForecast(id: Long) = forecastDbHelper.use {
/*        val forecast = select(DayForecastTable.NAME).byId(id).
                parseOpt( DayForecast (HashMap(it)))
        forecast?.let(dataMapper.convertDayToDomain(it))*/
        val forecast = select(DayForecastTable.NAME).byId(id).
                parseOpt { DayForecast(HashMap(it)) }

        forecast?.let { dataMapper.convertDayToDomain(it) }
    }
    fun saveForecast(forecast:ForecastList)=forecastDbHelper.use {
        clear (CityForecastTable.NAME)
        clear(DayForecastTable.NAME)
        with(dataMapper.convertFromDomain(forecast)){
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach { insert(DayForecastTable.NAME,*it.map.toVarargArray()) }
        }
    }
}