package com.soundsonic.simplemensa.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.databinding.CanteenItemBinding
import com.soundsonic.simplemensa.ui.main.listener.CanteenHandler
import kotlinx.android.synthetic.main.canteen_item.view.*

class CanteenViewHolder(
    parent: ViewGroup,
    canteenHandler: CanteenHandler,
    private val binding: CanteenItemBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.canteen_item, parent, false
        )
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.canteenHandler = canteenHandler
    }

    fun bind(canteen: Canteen) {
        binding.canteen = canteen

        val htmlName = canteen.url.substringAfter("details-").substringBefore(".html")
        itemView.canteenImageView.load("$CANTEEN_OVERVIEW_URL$htmlName.jpg") {
            crossfade(true)
        }
    }

    companion object {
        private const val CANTEEN_OVERVIEW_URL =
            "https://static.studentenwerk-dresden.de/mensen/mensen-cafeterien-aussenansichten/"
    }
}
