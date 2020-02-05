package com.soundsonic.simplemensa.ui.base

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.data.model.Meal
import com.soundsonic.simplemensa.ui.main.adapter.CanteenListAdapter
import com.soundsonic.simplemensa.ui.meals.adapter.MealsListAdapter
import com.soundsonic.simplemensa.util.Formatter.euroCurrency

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
        imageView.load(it) {
            crossfade(true)
        }
    }

    @JvmStatic
    @BindingAdapter("mealNotes")
    fun setNotes(textView: TextView, notes: List<String>?) {
        if (notes == null) {
            textView.visibility = View.GONE
            return
        }
        val bulletListText = notes.joinToString("<br/>") { note ->
            "&#8226; $note"
        }
        textView.text = HtmlCompat.fromHtml(bulletListText, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    @JvmStatic
    @BindingAdapter("setPrices")
    fun setPrices(textView: TextView, prices: Map<String, Double>) {

        textView.text = prices.values.joinToString(" / ") { price ->
            euroCurrency.format(price)
        }
    }
}
