package com.soundsonic.simplemensa.ui.meals.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.soundsonic.simplemensa.data.model.Meal
import com.soundsonic.simplemensa.util.CurrencyUtil.euroCurrency

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("meals")
    fun setMeals(recyclerView: RecyclerView, meals: List<Meal>?) {

        val adapter = recyclerView.adapter as? MealListAdapter ?: return
        meals?.let {
            adapter.submitList(it)
        }
    }

    @JvmStatic
    @BindingAdapter("prices")
    fun setPrices(textView: TextView, prices: Map<String, Double>) {

        textView.text = prices.values.joinToString(" / ") { price ->
            euroCurrency.format(price)
        }
    }
}
