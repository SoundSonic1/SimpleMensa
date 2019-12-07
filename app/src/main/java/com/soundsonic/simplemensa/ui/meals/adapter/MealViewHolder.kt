package com.soundsonic.simplemensa.ui.meals.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.data.model.Meal
import com.soundsonic.simplemensa.databinding.MealItemBinding
import com.soundsonic.simplemensa.ui.meals.listener.MealHandler

class MealViewHolder(
    parent: ViewGroup,
    mealHandler: MealHandler,
    private val binding: MealItemBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.meal_item, parent, false
        )
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.mealHandler = mealHandler
    }

    fun bind(meal: Meal) {
        binding.meal = meal
    }
}
