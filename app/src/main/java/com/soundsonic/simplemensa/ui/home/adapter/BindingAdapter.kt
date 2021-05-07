package com.soundsonic.simplemensa.ui.home.adapter

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
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
}
