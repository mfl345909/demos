package com.mxz.test.testgif

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import androidx.annotation.Px
import coil.annotation.ExperimentalCoilApi
import coil.transform.AnimatedTransformation
import coil.transform.PixelOpacity

@ExperimentalCoilApi
fun roundCornerGifTransformation(@Px radius: Float = 0f) = RoundCornerGifTransformation(radius, radius, radius, radius)

@ExperimentalCoilApi
class RoundCornerGifTransformation(
    @Px private val topLeft: Float = 0f,
    @Px private val topRight: Float = 0f,
    @Px private val bottomLeft: Float = 0f,
    @Px private val bottomRight: Float = 0f
) : AnimatedTransformation {

    private val paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.TRANSPARENT
            xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        }
    }

    private var radiusArray: FloatArray = floatArrayOf(
        topLeft, topLeft, topRight, topRight, bottomRight, bottomRight, bottomLeft, bottomLeft
    )

    override fun transform(canvas: Canvas): PixelOpacity {
        val rect = RectF(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat())
        val path = Path().apply {
            fillType = Path.FillType.INVERSE_EVEN_ODD
            addRoundRect(rect, radiusArray, Path.Direction.CW)
        }
        canvas.drawPath(path, paint)
        return PixelOpacity.TRANSLUCENT
    }
}
