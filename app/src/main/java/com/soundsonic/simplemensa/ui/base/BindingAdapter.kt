package com.soundsonic.simplemensa.ui.base

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.soundsonic.simplemensa.R
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.data.model.Meal
import com.soundsonic.simplemensa.ui.home.adapter.CanteenListAdapter
import com.soundsonic.simplemensa.ui.meals.adapter.MealListAdapter
import com.soundsonic.simplemensa.util.CurrencyUtil

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("canteens", "favourites")
    fun setCanteens(recyclerView: RecyclerView, canteens: List<Canteen>?, favourites: Set<Int>?) {

        val adapter: CanteenListAdapter = recyclerView.adapter as? CanteenListAdapter ?: return

        canteens?.let {
            adapter.submitList(canteens)
        }
        favourites?.let {
            adapter.setFavourites(it)
        }
    }

    @JvmStatic
    @BindingAdapter("isSelected")
    fun setSelected(view: View, isSelected: Boolean) {
        view.isSelected = isSelected
    }

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
    fun setPrices(textView: TextView, prices: Map<String, Double?>) {
        textView.text = prices.values.joinToString(" / ") { price ->
            if (price == null) {
                textView.context.getString(R.string.unknown)
            } else {
                CurrencyUtil.euroCurrency.format(price)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(imageView: AppCompatImageView, image: String?) = image?.let {
        imageView.load(it) {
            crossfade(true)
        }
    }
}
