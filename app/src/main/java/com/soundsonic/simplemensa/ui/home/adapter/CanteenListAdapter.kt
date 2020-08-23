package com.soundsonic.simplemensa.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.ui.home.viewholder.CanteenViewHolder

class CanteenListAdapter(
    private val listener: CanteenListener
) : ListAdapter<Canteen, CanteenViewHolder>(CANTEEN_DIFF) {

    interface CanteenListener {
        fun onCanteenClicked(canteen: Canteen)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CanteenViewHolder {
        return CanteenViewHolder(parent)
    }

    override fun onBindViewHolder(holder: CanteenViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            listener.onCanteenClicked(getItem(position))
        }
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
