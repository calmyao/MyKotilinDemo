package com.minhuizhu.mykotlin.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.minhuizhu.mykotlin.R
import com.minhuizhu.mykotlin.domain.commands.RequestForecastCommand
import com.minhuizhu.mykotlin.extension.DelegatesExt
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(),ToolbarManager {
    override val toolbar  by lazy{find<Toolbar>(R.id.toolbar)}
    val zipCode:Long by DelegatesExt.preference(this,SettingsActivity.ZIP_CODE,SettingsActivity.DEFAULT_ZIP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        forecastList.layoutManager=LinearLayoutManager(this)
        attachToScroll(forecastList)
    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast() =async(){
        val result= RequestForecastCommand(zipCode).execute()
        uiThread {
            val adapter=ForecastListAdapter(result){

            }
            forecastList.adapter=adapter
            toolbarTitle="${result.city}(${result.country})"
        }

    }

}




























