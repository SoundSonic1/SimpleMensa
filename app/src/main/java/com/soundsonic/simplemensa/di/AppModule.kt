package com.soundsonic.simplemensa.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.soundsonic.simplemensa.data.adapter.MealAdapter
import com.soundsonic.simplemensa.data.api.OpenMensaApi
import com.soundsonic.simplemensa.util.Constants.BASE_URL
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Named
import javax.inject.Singleton
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object AppModule {

    @Provides
    @Singleton
    @Named("AppContext")
    fun appContext(app: Application): Context = app

    @Provides
    @Singleton
    fun provideSharedPreference(app: Application): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(app)

    @Provides
    fun provideMoshi(mealAdapter: MealAdapter): Moshi {
        return Moshi.Builder().add(mealAdapter).build()
    }

    @Provides
    @Singleton
    fun openMensaApi(moshi: Moshi): OpenMensaApi = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()
        .create(OpenMensaApi::class.java)

    @Provides
    fun provideItemAnimator(): RecyclerView.ItemAnimator = SlideInLeftAnimator().apply {
        addDuration = 300
        removeDuration = 300
        moveDuration = 300
        changeDuration = 300
    }

    @Provides
    fun provideSimpleDateFormat() = SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY)
}
