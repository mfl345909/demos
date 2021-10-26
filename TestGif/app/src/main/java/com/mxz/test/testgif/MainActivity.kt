package com.mxz.test.testgif

import android.app.Activity
import android.os.Bundle
import androidx.core.view.doOnLayout
import androidx.core.view.updateLayoutParams
import coil.annotation.ExperimentalCoilApi
import coil.load
import coil.request.ImageRequest
import coil.request.animatedTransformation
import coil.transform.RoundedCornersTransformation
import com.mxz.test.testgif.databinding.ActivityMainBinding

class MainActivity : Activity() {

    private val Float.dp: Float
        inline get() = this * resources.displayMetrics.density

    private val heightWidthRatio = 0.5f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.doOnLayout {
            binding.gifImageView1.updateLayoutParams {
                height = (heightWidthRatio * binding.gifImageView1.measuredWidth).toInt()
            }

            binding.gifImageView2.updateLayoutParams {
                height = (heightWidthRatio * binding.gifImageView2.measuredWidth).toInt()
            }

            binding.gifImageView3.updateLayoutParams {
                height = (heightWidthRatio * binding.gifImageView3.measuredWidth).toInt()
            }
        }

        binding.gifImageView1.load(R.drawable.test_large) {
            cornerRadius(8f.dp)
        }
        binding.gifImageView2.load(R.drawable.test_normal) {
            cornerRadius(8f.dp)
        }
        binding.gifImageView3.load(R.drawable.test_small) {
            cornerRadius(8f.dp)
        }
    }

    /**
     * 支持同时为 Gif 和 普通图片配置圆角（Gif 图会通过 roundCornerGifTransformation，普通图通过 RoundedCornersTransformation）
     */
    @ExperimentalCoilApi
    fun ImageRequest.Builder.cornerRadius(cornerRadius: Float) {
        if (cornerRadius > 0) {
            allowConversionToBitmap(false)
            // 因为配置了 allowConversionToBitmap 为 false，所以非 BitmapDrawable 的 result 会忽略此配置
            transformations(RoundedCornersTransformation(cornerRadius, cornerRadius, cornerRadius, cornerRadius))
            // 只有 Gif 才会采用此种方式添加圆角，普通图片会忽略此配置
            animatedTransformation(roundCornerGifTransformation(cornerRadius))
        }
    }
}