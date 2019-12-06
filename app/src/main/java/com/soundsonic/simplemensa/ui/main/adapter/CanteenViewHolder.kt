package com.soundsonic.simplemensa.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.databinding.CanteenItemBinding

class CanteenViewHolder(
    parent: ViewGroup,
    private val binding: CanteenItemBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.canteen_item, parent, false
        )
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(canteen: Canteen) {
        binding.canteen = canteen
    }
}
