package com.mxz.test.testgif

import android.content.Context
import android.os.Build
import coil.Coil
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.util.DebugLogger

/**
 * @Author: mengfl
 * @Date: 2021/9/26 9:00 下午
 */
object TestImageLoader {

    @JvmStatic
    fun initImageLoader(context: Context) {
        val imageLoader = ImageLoader.Builder(context)
            .componentRegistry {
                // Android 9 (Android P = 28) 开始，系统提供了性能更好的 ImageDecoderDecoder
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    add(ImageDecoderDecoder(context))
                } else {
                    add(GifDecoder())
                }
            }.logger(DebugLogger())
            .build()
        Coil.setImageLoader(imageLoader)
    }
}
