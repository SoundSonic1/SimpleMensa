package com.soundsonic.simplemensa.ui.meals

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.soundsonic.simplemensa.data.model.Meal
import com.soundsonic.simplemensa.ui.meals.adapter.MealViewHolder
import javax.inject.Inject

class MealsListAdapter @Inject constructor() : ListAdapter<Meal, MealViewHolder>(MEAL_DIFF) {

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        return MealViewHolder(parent)
    }

    companion object {
        val MEAL_DIFF = object : DiffUtil.ItemCallback<Meal>() {

            override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
