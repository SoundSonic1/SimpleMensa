package com.soundsonic.simplemensa.ui.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.databinding.CanteenItemBinding

class CanteenViewHolder(
    private val binding: CanteenItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(canteen: Canteen, isFavourite: Boolean) {
        binding.canteen = canteen
        binding.isFavourite = isFavourite
    }
}
