package com.andyshon.flickrapi.utils.extensions

import android.graphics.drawable.Drawable
import android.os.Handler
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.andyshon.flickrapi.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

fun ImageView.loadImage(
    @DrawableRes resId: Int,
    radius: Int = context.resources.getDimensionPixelSize(R.dimen.radius_10),
    margin: Int = 0,
    cornerType: RoundedCornersTransformation.CornerType = RoundedCornersTransformation.CornerType.ALL
) {
    val transform = RequestOptions().centerCrop()
        .transforms(CenterCrop(), RoundedCornersTransformation(radius, margin, cornerType))
    Glide.with(this).load(resId).apply(transform).into(this)
    this.show()
}

fun ImageView.loadImage(
    url: String,
    radius: Int = context.resources.getDimensionPixelSize(R.dimen.radius_10),
    margin: Int = 0,
    cornerType: RoundedCornersTransformation.CornerType = RoundedCornersTransformation.CornerType.ALL
) {
    val transform = RequestOptions().centerCrop()
        .transforms(CenterCrop(), RoundedCornersTransformation(radius, margin, cornerType))
    Glide.with(this.context)
        .load(url)
        .apply(transform)
        .listener(object: RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                Handler().post {
                    this@loadImage.loadImage(R.drawable.ic_image_placeholder)
                }
                return true
            }
            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                this@loadImage.setImageDrawable(resource)
                this@loadImage.show()
                return true
            }
        })
        .into(this)
}