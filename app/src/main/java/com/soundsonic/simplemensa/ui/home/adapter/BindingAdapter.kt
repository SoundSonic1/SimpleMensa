package com.soundsonic.simplemensa.ui.home.adapter

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.soundsonic.simplemensa.data.model.Canteen

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("canteens", "favourites")
    fun setCanteens(recyclerView: RecyclerView, canteens: List<Canteen>?, favourites: Set<Int>?) {

        val adapter: CanteenListAdapter = recyclerView.adapter as? CanteenListAdapter ?: return

        canteens?.let {
            adapter.submitList(canteens)
        }
        favourites?.let {
            adapter.setFavourites(it)
        }
    }

    @JvmStatic
    @BindingAdapter("isSelected")
    fun setSelected(view: View, isSelected: Boolean) {
        view.isSelected = isSelected
    }

    @JvmStatic
    @BindingAdapter("canteenImage")
    fun setCanteenImage(imageView: AppCompatImageView, url: String) {
        imageView.load(url) {
            crossfade(true)
        }
    }
}
