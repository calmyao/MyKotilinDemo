package com.minhuizhu.mykotlin.data.server

import com.minhuizhu.mykotlin.domain.model.ForecastList
import com.minhuizhu.mykotlin.domain.model.Forecast as ModelForecast
/**
 * Created by minhui.zhu on 2016/12/30.
 */
class ServerDataMapper {
    fun convertToDomain(zipCode:Long,forecast:ForecastResult)=with(forecast){
        ForecastList(zipCode,city.name,city.country,convertForecastListToDomain(list))
    }
    private fun convertForecastListToDomain(list:List<Forecast>):List<ModelForecast>{
        return list.map{convertForecastItemToDomain(it)}
    }
    private fun convertForecastItemToDomain(forecast:Forecast)=with(forecast){
        ModelForecast(-1,dt*1000,weather[0].description,temp.max.toInt(),temp.min.toInt(),
                generateIconUrl(weather[0].icon))
    }

    private fun  generateIconUrl(iconCode: String)="http://openweathermap.org/img/w/$iconCode.png"


}