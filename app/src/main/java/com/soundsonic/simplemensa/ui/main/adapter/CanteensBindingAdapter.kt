package com.soundsonic.simplemensa.ui.main.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.soundsonic.simplemensa.data.model.Canteen

object CanteensBindingAdapter {

    @JvmStatic
    @BindingAdapter("canteens")
    fun setListAdapter(recyclerView: RecyclerView, canteens: List<Canteen>?) {

        val adapter = recyclerView.adapter
        if (canteens != null && adapter is CanteenListAdapter) {
            adapter.submitList(canteens)
        }
    }
}
