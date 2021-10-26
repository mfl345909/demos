package com.mxz.test.testgif

import android.app.Application

/**
 * @Author: mengfl
 * @Date: 2021/10/25 9:34 下午
 */
class TestGifApp : Application() {

    override fun onCreate() {
        super.onCreate()
        TestImageLoader.initImageLoader(this)
    }
}
