package com.soundsonic.simplemensa.viewmodel

import com.soundsonic.simplemensa.data.adapter.MealAdapter
import com.soundsonic.simplemensa.data.api.OpenMensaApi
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.data.repositories.MealsRepositoryImpl
import com.soundsonic.simplemensa.ui.meals.viewmodel.MealsViewModel
import com.soundsonic.simplemensa.util.Constants
import com.soundsonic.simplemensa.util.InstantExecutorExtension
import com.soundsonic.simplemensa.util.MockMealsInterceptor
import com.soundsonic.simplemensa.util.getOrAwaitValue
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.setMain
import okhttp3.OkHttpClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class MealsViewModelTest {

    init {
        Dispatchers.setMain(newSingleThreadContext("UI thread"))
    }

    @Test
    fun `load meals on instantiation`() {
        val canteen = Canteen(1, "", "", "", emptyList(), "", "")
        val client = OkHttpClient.Builder().addInterceptor(MockMealsInterceptor()).build()
        val moshi = Moshi.Builder().add(MealAdapter()).build()
        val api = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(OpenMensaApi::class.java)
        val repo = MealsRepositoryImpl(canteen, api)
        val vm = MealsViewModel(repo, "")

        assertEquals(7, vm.meals.getOrAwaitValue().size)
    }
}
