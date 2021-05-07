package com.soundsonic.simplemensa.ui.mealdetail

import androidx.core.text.HtmlCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.soundsonic.simplemensa.data.model.Meal
import com.soundsonic.simplemensa.util.CurrencyUtil.euroCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel @Inject constructor() : ViewModel() {
    private val _meal: MutableLiveData<Meal> = MutableLiveData()
    val meal: LiveData<Meal> get() = _meal
    val mealNotes: LiveData<CharSequence> = Transformations.map(meal) {
        if (it.notes == null) {
            ""
        } else {
            val bulletListText = it.notes.joinToString("<br/>") { note ->
                "&#8226; $note"
            }
            HtmlCompat.fromHtml(bulletListText, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }
    val prices: LiveData<String> = Transformations.map(meal) {
        it.prices.values.joinToString(" / ") { price ->
            euroCurrency.format(price)
        }
    }
    val imageUrl: LiveData<String> = Transformations.map(meal) {
        "https:${it.image}"
    }

    fun setMeal(meal: Meal) {
        _meal.value = meal
    }
}
