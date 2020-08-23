package com.soundsonic.simplemensa.ui.home.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.soundsonic.simplemensa.data.model.Canteen

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("canteens")
    fun setCanteens(recyclerView: RecyclerView, canteens: List<Canteen>?) {

        if (canteens == null) return

        val canteenListAdapter = recyclerView.adapter as? CanteenListAdapter
        canteenListAdapter?.submitList(canteens)
    }

    @JvmStatic
    @BindingAdapter("canteenImage")
    fun setCanteenImage(imageView: AppCompatImageView, url: String) {
        imageView.load(url) {
            crossfade(true)
        }
    }
}
