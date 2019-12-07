package com.soundsonic.simplemensa.ui.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.data.model.Meal
import com.soundsonic.simplemensa.ui.main.adapter.CanteenListAdapter
import com.soundsonic.simplemensa.ui.meals.adapter.MealsListAdapter

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("canteens")
    fun setCanteens(recyclerView: RecyclerView, canteens: List<Canteen>?) {

        val adapter = recyclerView.adapter
        if (canteens != null && adapter is CanteenListAdapter) {
            adapter.submitList(canteens)
        }
    }

    @JvmStatic
    @BindingAdapter("meals")
    fun setMeals(recyclerView: RecyclerView, meals: List<Meal>?) {

        val adapter = recyclerView.adapter
        if (meals != null && adapter is MealsListAdapter) {
            adapter.submitList(meals)
        }
    }

    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(imageView: ImageView, image: String?) = image?.let {
        imageView.load(it)
    }
}
