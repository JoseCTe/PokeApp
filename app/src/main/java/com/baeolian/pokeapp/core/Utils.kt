package com.baeolian.pokeapp.core

import android.content.Context
import android.graphics.Color
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

object Utils {

    // Formatting check for Nidoran m/f and Deoxys
    fun checkSpecialChar(str: String): String {
        var res = str
        if(str.endsWith("-m")){
            res = str.replace("-m", " ♂")
        } else if(str.endsWith("-f")) {
            res = str.replace("-f", " ♀")
        } else if(str.endsWith("-normal")){
            res = str.replace("-normal", "")
        }
        return res
    }

    // Loading circle for Glide
    fun newLoadingCircle(context: Context, size : Float) : CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(context)
        var sw = 20f
        if (size < 31f)
            sw = 10f
        circularProgressDrawable.strokeWidth = sw
        circularProgressDrawable.centerRadius = size
        circularProgressDrawable.setColorSchemeColors(Color.parseColor("#F9AA33"))
        circularProgressDrawable.start()
        return circularProgressDrawable
    }
}