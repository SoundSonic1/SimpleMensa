package com.soundsonic.simplemensa.ui.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.soundsonic.simplemensa.data.model.Canteen

class CanteenListAdapter : ListAdapter<Canteen, CanteenViewHolder>(CANTEEN_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CanteenViewHolder {
        return CanteenViewHolder(parent)
    }

    override fun onBindViewHolder(holder: CanteenViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val CANTEEN_DIFF = object : DiffUtil.ItemCallback<Canteen>() {

            override fun areItemsTheSame(oldItem: Canteen, newItem: Canteen): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Canteen, newItem: Canteen): Boolean {
                return oldItem == newItem
            }
        }
    }
}
