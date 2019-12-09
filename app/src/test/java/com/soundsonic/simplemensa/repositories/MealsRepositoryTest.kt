package com.soundsonic.simplemensa.repositories

import com.soundsonic.simplemensa.data.adapter.MealAdapter
import com.soundsonic.simplemensa.data.api.OpenMensaApi
import com.soundsonic.simplemensa.data.model.Canteen
import com.soundsonic.simplemensa.data.repositories.MealsRepositoryImpl
import com.soundsonic.simplemensa.util.Constants.BASE_URL
import com.soundsonic.simplemensa.util.MockMealsInterceptor
import com.squareup.moshi.Moshi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MealsRepositoryTest {

    @Test
    fun `fetch mocked meals`() {
        val canteen = Canteen(1, "", "", "", emptyList(), "", "")
        val client = OkHttpClient.Builder().addInterceptor(MockMealsInterceptor()).build()
        val moshi = Moshi.Builder().add(MealAdapter()).build()
        val api = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .baseUrl(BASE_URL)
            .build()
            .create(OpenMensaApi::class.java)
        val repo = MealsRepositoryImpl(canteen, api)

        runBlocking {
            assertEquals(7, repo.getMeals("2019-01-01").size)
        }
    }
}
