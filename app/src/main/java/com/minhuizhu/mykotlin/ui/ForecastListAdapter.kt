package com.minhuizhu.mykotlin.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.minhuizhu.mykotlin.R
import com.minhuizhu.mykotlin.domain.model.Forecast
import com.minhuizhu.mykotlin.domain.model.ForecastList
import com.minhuizhu.mykotlin.extension.ctx
import com.minhuizhu.mykotlin.extension.toDateString
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*

/**
 * Created by zhuminh on 2016/12/30.
 */
class ForecastListAdapter(val weekForecast: ForecastList, val itemClick:(Forecast)->Unit):
        RecyclerView.Adapter<ForecastListAdapter.ViewHolder>(){
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount()=   weekForecast.size()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val view=LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast,parent,false)
        return ViewHolder(view,itemClick)
    }

    class ViewHolder(view: View, val itemClick:(Forecast)->Unit):RecyclerView.ViewHolder(view){
        fun bindForecast(forecast:Forecast){
            with(forecast){
                Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.date.text=date.toDateString()
                itemView.description.text=description
                itemView.maxTemperature.text="${high.toString()}"
                itemView.minTemperature.text="${low.toString()}"
                itemView.setOnClickListener { itemClick(this) }
            }

        }

    }

}




























