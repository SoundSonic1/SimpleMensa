package com.soundsonic.simplemensa.ui.meals.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.soundsonic.simplemensa.data.model.Meal
import com.soundsonic.simplemensa.databinding.MealItemBinding

class MealViewHolder(
    private val binding: MealItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(meal: Meal) {
        binding.meal = meal
    }
}
