package com.minhuizhu.mykotlin.ui.activities

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.minhuizhu.mykotlin.R
import com.minhuizhu.mykotlin.extension.ctx
import com.minhuizhu.mykotlin.extension.slideEnter
import com.minhuizhu.mykotlin.extension.slideExit
import com.minhuizhu.mykotlin.ui.App
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by minhui.zhu on 2016/12/29.
 */
interface ToolbarManager {
    val toolbar: Toolbar
    var toolbarTitle:String
        get()=toolbar.title.toString()
        set(value){
            toolbar.title=value
        }

    fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> toolbar.ctx.startActivity<SettingsActivity>()
                else -> App.instance.toast("Unknown option")
            }
            true
        }
    }

    fun attachToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) toolbar.slideExit() else toolbar.slideEnter()
            }
        })
    }
    fun enableHomeAsUp(up:()->Unit){
        toolbar.navigationIcon=createUpDrawable()
        toolbar.setNavigationOnClickListener { up() }

    }
    private fun createUpDrawable()=DrawerArrowDrawable(toolbar.ctx).apply { progress=1f }



}





















