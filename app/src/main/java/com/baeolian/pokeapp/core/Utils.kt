package com.baeolian.pokeapp.core

import android.content.Context
import android.graphics.Color
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

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

    // Obtain LiveData in testing environments
    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS,
        afterObserve: () -> Unit = {}
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(o: T?) {
                data = o
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }
        this.observeForever(observer)
        try {
            afterObserve.invoke()
            if (!latch.await(time, timeUnit)) {
                throw TimeoutException("LiveData value was never set.")
            }
        } finally {
            this.removeObserver(observer)
        }
        @Suppress("UNCHECKED_CAST")
        return data as T
    }
}