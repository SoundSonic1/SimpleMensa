package com.soundsonic.simplemensa.ui.mealdetail.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import coil.load

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(imageView: AppCompatImageView, image: String?) = image?.let {
        imageView.load(it) {
            crossfade(true)
        }
    }
}
