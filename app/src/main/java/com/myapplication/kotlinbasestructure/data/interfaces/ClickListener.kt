package com.myapplication.kotlinbasestructure.data.interfaces

import android.view.View


interface ClickListener {
    fun onItemClick(
        position: Int,
        view:View?
    );

}