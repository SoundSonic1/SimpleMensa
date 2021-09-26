package com.soundsonic.simplemensa.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.databinding.CanteenItemBinding
import com.soundsonic.simplemensa.ui.home.viewholder.CanteenViewHolder

class CanteenListAdapter(
    private val listener: CanteenListener
) : ListAdapter<Canteen, CanteenViewHolder>(CANTEEN_DIFF) {

    private var favourites: Set<Int> = setOf()

    interface CanteenListener {
        fun onCanteenClicked(canteen: Canteen)
        fun onFavouriteClicked(v: View, canteen: Canteen)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CanteenViewHolder {
        val canteenItemBinding = CanteenItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CanteenViewHolder(canteenItemBinding)
    }

    override fun onBindViewHolder(holder: CanteenViewHolder, position: Int) {
        val canteen = getItem(position)

        holder.bind(canteen, favourites.contains(canteen.id))

        holder.binding.canteenLayout.setOnClickListener {
            listener.onCanteenClicked(canteen)
        }
        holder.binding.favouriteCanteenImage.setOnClickListener {
            listener.onFavouriteClicked(it, canteen)
        }
    }

    fun setFavourites(favourites: Set<Int>) {
        this.favourites = favourites
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
