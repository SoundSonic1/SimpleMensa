package com.soundsonic.simplemensa.ui.meals.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.soundsonic.simplemensa.data.model.Meal
import com.soundsonic.simplemensa.databinding.MealItemBinding
import com.soundsonic.simplemensa.ui.meals.viewholder.MealViewHolder

class MealListAdapter(
    private val listener: OnClickListener
) : ListAdapter<Meal, MealViewHolder>(MEAL_DIFF) {

    interface OnClickListener {
        fun onMealClicked(v: View, meal: Meal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding = MealItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = getItem(position)
        holder.bind(meal)
        holder.itemView.setOnClickListener {
            listener.onMealClicked(it, meal)
        }
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
